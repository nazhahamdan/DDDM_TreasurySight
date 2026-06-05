package com.example.demo.Service;

import lombok.RequiredArgsConstructor;
import com.example.demo.dto.RawTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MindeeService {

    @Value("${mindee.api.key}")
    private String API_KEY;

    private final String ENDPOINT = "https://api.mindee.net/v1/products/mindee/invoice/v4/predict";

    public RawTransaction parseFinancialDocument(MultipartFile file) throws IOException {
        String boundary = "----Boundary" + System.currentTimeMillis();

        HttpURLConnection conn = (HttpURLConnection) new URL(ENDPOINT).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Token " + API_KEY);
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String header = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"document\"; filename=\""
                    + file.getOriginalFilename() + "\"\r\n"
                    + "Content-Type: " + file.getContentType() + "\r\n\r\n";
            os.write(header.getBytes());
            os.write(file.getBytes());
            os.write(("\r\n--" + boundary + "--\r\n").getBytes());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(conn.getInputStream());
        JsonNode prediction = json.at("/document/inference/prediction");

        RawTransaction raw = new RawTransaction();
        raw.setAmount(prediction.at("/total_amount/value").asDouble());
        raw.setDescription(prediction.at("/supplier_name/value").asText());

        String dateStr = prediction.at("/date/value").asText();
        if (dateStr != null && !dateStr.isBlank()) {
            raw.setDate(LocalDate.parse(dateStr));
        }

        return raw;
    }
}