package com.example.demo.Service;

import lombok.RequiredArgsConstructor;
import com.example.demo.dto.RawTransaction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MindeeService {

    private final String API_KEY = "YOUR_MINDEE_API_KEY";
    private final String ENDPOINT = "https://api.mindee.net/v1/products/mindee/invoice/v1/predict";

    public RawTransaction parseFinancialDocument(MultipartFile file) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(ENDPOINT).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Token " + API_KEY);
        conn.setDoOutput(true);

        try(OutputStream os = conn.getOutputStream()) {
            os.write(file.getBytes());
        }

        InputStream response = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response);

        // Map Mindee output to RawTransaction
        RawTransaction raw = new RawTransaction();
        raw.setAmount(json.at("/document/invoice/total_incl_tax/value").asDouble());
        raw.setDate(json.at("/document/invoice/date").asText());
        raw.setDescription(json.at("/document/invoice/supplier_name").asText());

        return raw;
    }
}