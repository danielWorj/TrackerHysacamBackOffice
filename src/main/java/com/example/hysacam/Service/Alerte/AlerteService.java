package com.example.hysacam.Service.Alerte;

import com.example.hysacam.Entity.IA.Signalement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class AlerteService {

    // URL de l'API Flask — configurable dans application.properties
    // ia.api.url=http://localhost:5000/detect
    @Value("${ia.api.url}")
    private String iaApiUrl;
    private final RestTemplate restTemplate = new RestTemplate(); //  Direct
    public Signalement analyse(MultipartFile file) {

        try {
            // ── 1. Construction du header multipart/form-data ──
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // ── 2. Encapsulation du fichier image dans la requête ──
            ByteArrayResource imageResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    // Nom original du fichier conservé
                    return file.getOriginalFilename();
                }
            };

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", imageResource);

            HttpEntity<MultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<>(body, headers);

            // ── 3. Appel de l'API Flask ──
            ResponseEntity<Map> response = restTemplate.exchange(
                    iaApiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

            // ── 4. Mapping de la réponse vers l'entité Signalement ──
            Map<String, Object> responseBody = response.getBody();

            if (responseBody == null) {
                throw new RuntimeException("Réponse vide de l'API IA.");
            }

            Signalement signalement = new Signalement();
            signalement.setStatus((Boolean) responseBody.get("waste_detected"));
            signalement.setScore(((Number) responseBody.get("score")).doubleValue());

            // latitude / longitude peuvent être null si EXIF absent
            Object lat = responseBody.get("latitude");
            Object lon = responseBody.get("longitude");
            signalement.setLatitude(lat != null ? ((Number) lat).doubleValue() : null);
            signalement.setLongitude(lon != null ? ((Number) lon).doubleValue() : null);

            return signalement;

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'appel à l'API IA : " + e.getMessage(), e);
        }
    }
}