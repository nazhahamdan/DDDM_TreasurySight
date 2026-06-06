from flask import Flask, jsonify
from flask_cors import CORS
import numpy as np
import tensorflow as tf
import pandas as pd
import pickle


app = Flask(__name__)
CORS(app)


model = tf.keras.models.load_model('meilleur_modele_lstm (1).keras')
with open('scaler.pkl', 'rb') as f:
    scaler = pickle.load(f)


@app.route('/predict', methods=['GET'])
def predict():
    try:
        # ─── 1. Lire le CSV ───
        df = pd.read_csv('donnees_entreprise.csv')


        # ─── 2. Garder les colonnes du modèle ───
        feature_cols = [col for col in df.columns
                        if col.startswith('X') or col.startswith('Div_')]


        # ─── 3. Prendre les 3 dernières années ───
        df_recent = df[feature_cols].tail(3).values


        # ─── 4. Normaliser ───
        X_scaled = scaler.transform(df_recent)


        # ─── 5. Créer séquence LSTM (1, 3, nb_features) ───
        X_seq = X_scaled.reshape(1, 3, X_scaled.shape[1])


        # ─── 6. Prédire ───
        prob = float(model.predict(X_seq)[0][0])


        return jsonify({
            'probabilite': prob,
            'risque': 'ÉLEVÉ' if prob > 0.6 else 'MODÉRÉ' if prob > 0.3 else 'FAIBLE',
            'scenarios': {
                'optimiste' : {'j30': prob*0.3, 'j60': prob*0.4, 'j90': prob*0.6},
                'realiste'  : {'j30': prob*0.6, 'j60': prob*0.8, 'j90': prob},
                'pessimiste': {'j30': prob*0.8, 'j60': prob*0.95,
                               'j90': min(prob*1.2, 1.0)}
            }
        })


    except Exception as e:
        return jsonify({'erreur': str(e)}), 500


if __name__ == '__main__':
    app.run(port=5000, debug=True)

