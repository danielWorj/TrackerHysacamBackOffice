package com.example.hysacam.Controller.Alerte;

import com.example.hysacam.Entity.Alerte.Alerte;
import com.example.hysacam.Entity.IA.Signalement;
import com.example.hysacam.Entity.Server.ServerResponse;
import com.example.hysacam.Entity.Ville.Poubelle;
import com.example.hysacam.Repository.AlerteRepository;
import com.example.hysacam.Repository.PoubelleRepository;
import com.example.hysacam.Service.Alerte.AlerteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AlerteControllerImpl implements AlerteControllerInt {

    @Autowired
    private AlerteRepository alerteRepository;

    @Autowired
    private PoubelleRepository poubelleRepository;

    @Autowired
    private AlerteService alerteService;


    // GET /hysacam/api/alerte/all
    @Override
    public ResponseEntity<Alerte> getAllAlerte() {
        try {
            return ResponseEntity.ok()
                    .body((Alerte) alerteRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // POST /hysacam/api/alerte/create

    @Override
    public ResponseEntity<ServerResponse> createAlerte(MultipartFile file) {
        ServerResponse response = new ServerResponse();
        try {
            // ── 1. Appel API Flask via AlerteService ──
            Signalement signalement = alerteService.analyse(file);

            // ── 2. Recherche de la poubelle la plus proche via Haversine ──
            Poubelle poubelleLaPlusProche = null;
            if (signalement.getLatitude() != null && signalement.getLongitude() != null) {
                poubelleLaPlusProche = trouverPoubelleLaPlusProche(
                        signalement.getLatitude(),
                        signalement.getLongitude()
                );
            }

            // ── 3. Mapping Signalement → Alerte ──
            Alerte alerte = new Alerte();
            alerte.setEtat(signalement.getStatus());
            alerte.setScore(signalement.getScore());

            alerte.setUrl(file.getOriginalFilename());
            alerte.setStatus(
                    Boolean.TRUE.equals(signalement.getStatus()) ? "URGENT" : "NORMAL"
            );
            alerte.setPoubelle(poubelleLaPlusProche); // peut être null si aucune en base

            // ── 4. Sauvegarde en base ──
            alerteRepository.save(alerte);

            response.setStatus(true);
            response.setMessage("Alerte créée avec succès.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Erreur lors de la création de l'alerte : " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }


    // DELETE /hysacam/api/alerte/delete/{id}

    @Override
    public ResponseEntity<ServerResponse> deleteAlerte(Integer id) {
        ServerResponse response = new ServerResponse();
        try {
            // ── 1. Vérification existence ──
            if (!alerteRepository.existsById(id)) {
                response.setStatus(false);
                response.setMessage("Alerte introuvable avec l'id : " + id);
                return ResponseEntity.status(404).body(response);
            }

            // ── 2. Suppression ──
            alerteRepository.deleteById(id);

            response.setStatus(true);
            response.setMessage("Alerte supprimée avec succès.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    // ──────────────────────────────────────────────
    // Formule de Haversine
    // Retourne la distance en kilomètres entre deux points GPS
    // ──────────────────────────────────────────────
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // Rayon de la Terre en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // ──────────────────────────────────────────────
    // Recherche de la Poubelle la plus proche
    // ──────────────────────────────────────────────
    private Poubelle trouverPoubelleLaPlusProche(double latitude, double longitude) {
        List<Poubelle> poubelles = poubelleRepository.findAll();

        if (poubelles.isEmpty()) return null;

        Poubelle laPlusProche = null;
        double distanceMin = Double.MAX_VALUE;

        for (Poubelle poubelle : poubelles) {
            double distance = haversine(
                    latitude, longitude,
                    poubelle.getLatitude(), poubelle.getLongitude()
            );
            if (distance < distanceMin) {
                distanceMin = distance;
                laPlusProche = poubelle;
            }
        }

        return laPlusProche;
    }

}