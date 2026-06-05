from flask import Flask, request, jsonify
from flask_cors import CORS
import numpy as np
import tensorflow as tf
import pickle

app = Flask(__name__)
CORS(app)

model = tf.keras.models.load_model('meilleur_modele_lstm.keras')
with open('scaler.pkl', 'rb') as f:
    scaler = pickle.load(f)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    features = list(data.values())
    X_input = np.array([features])
    X_scaled = scaler.transform(X_input)
    X_seq = np.repeat(X_scaled[:, np.newaxis, :], 3, axis=1)
    prob = float(model.predict(X_seq)[0][0])

    return jsonify({
        'probabilite': prob,
        'risque': 'ÉLEVÉ' if prob > 0.6 else 'MODÉRÉ' if prob > 0.3 else 'FAIBLE',
        'scenarios': {
            'optimiste' : {'j30': prob*0.3, 'j60': prob*0.4, 'j90': prob*0.6},
            'realiste'  : {'j30': prob*0.6, 'j60': prob*0.8, 'j90': prob},
            'pessimiste': {'j30': prob*0.8, 'j60': prob*0.95, 'j90': min(prob*1.2, 1.0)}
        }
    })

if __name__ == '__main__':
    app.run(port=5000, debug=True)