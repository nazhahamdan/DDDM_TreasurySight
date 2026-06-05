package com.example.demo.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MacroService {

    private final RestTemplate restTemplate;

    @Value("${fred.api.key}")
    private String apiKey;

    private final String FRED_URL = "https://api.stlouisfed.org/fred/series/observations";

    // Récupérer la dernière valeur d'un indicateur FRED
    private Map<String, Object> getLastValue(String seriesId, String nom, String unite) {
        String url = FRED_URL +
                "?series_id=" + seriesId +
                "&api_key=" + apiKey +
                "&file_type=json" +
                "&sort_order=desc" +
                "&limit=2";

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            List<Map<String, Object>> observations =
                    (List<Map<String, Object>>) response.get("observations");

            double valeurActuelle  = Double.parseDouble(
                    (String) observations.get(0).get("value"));
            double valeurPrecedente = Double.parseDouble(
                    (String) observations.get(1).get("value"));

            double variation = valeurActuelle - valeurPrecedente;
            boolean hausse   = variation >= 0;

            Map<String, Object> result = new HashMap<>();
            result.put("nom",       nom);
            result.put("seriesId",  seriesId);
            result.put("valeur",    valeurActuelle);
            result.put("precedent", valeurPrecedente);
            result.put("variation", variation);
            result.put("hausse",    hausse);
            result.put("unite",     unite);
            return result;

        } catch (Exception e) {
            Map<String, Object> erreur = new HashMap<>();
            erreur.put("nom",     nom);
            erreur.put("valeur",  0.0);
            erreur.put("erreur",  true);
            return erreur;
        }
    }

    public List<Map<String, Object>> getAllIndicateurs() {
        List<Map<String, Object>> indicateurs = new ArrayList<>();

        // ─── Vos 14 indicateurs exacts ───
        indicateurs.add(getLastValue("UNRATE",    "Unemployment Rate",          "%"));
        indicateurs.add(getLastValue("CSCICP03USM665S", "Consumer Confidence Index", "Index"));
        indicateurs.add(getLastValue("WPUSI012011",     "PPI Construction Materials","Index"));
        indicateurs.add(getLastValue("CPIAUCSL",        "CPI All Items",             "Index"));
        indicateurs.add(getLastValue("FPCPITOTLZGUSA",  "Inflation",                 "%"));
        indicateurs.add(getLastValue("MORTGAGE30US",    "Mortgage Interest Rate",    "%"));
        indicateurs.add(getLastValue("MEHOINUSA672N",   "Median Household Income",   "$"));
        indicateurs.add(getLastValue("BAMLC0A0CM",      "Corp. Bond Yield",          "%"));
        indicateurs.add(getLastValue("MSACSR",          "Monthly Home Supply",       "Months"));
        indicateurs.add(getLastValue("EMRATIO",         "Working Population Share",  "%"));
        indicateurs.add(getLastValue("A939RX0Q048SBEA", "GDP Per Capita",            "$"));
        indicateurs.add(getLastValue("GDPC1",           "Quarterly Real GDP",        "B$"));
        indicateurs.add(getLastValue("A191RL1Q225SBEA", "GDP Growth Rate",           "%"));
        indicateurs.add(getLastValue("CSUSHPISA",       "Home Price Index (Case-Shiller)", "Index"));

        return indicateurs;
    }
}