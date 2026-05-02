package com.example.hysacam.Controller.Ville;

import com.example.hysacam.Dto.QuartierDTO;
import com.example.hysacam.Entity.Server.ServerResponse;
import com.example.hysacam.Entity.Ville.Quartier;
import com.example.hysacam.Entity.Ville.Ville;
import com.example.hysacam.Repository.PoubelleRepository;
import com.example.hysacam.Repository.QuartierRepository;
import com.example.hysacam.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
public class VilleControllerImpl implements VilleControllerInt {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private QuartierRepository quartierRepository;
    @Autowired
    private PoubelleRepository poubelleRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<List<Ville>> getAllVille() {
        return ResponseEntity.ok(this.villeRepository.findAll());
    }

    @Override
    public ResponseEntity<ServerResponse> createVille(String ville) {
        Ville villeDb = this.objectMapper.readValue(ville, Ville.class);
        this.villeRepository.save(villeDb);
        return ResponseEntity.ok(new ServerResponse("Ville created successfully",true));
    }

    @Override
    public ResponseEntity<ServerResponse> updateVille(String ville) {
        return null;
    }

    @Override
    public ResponseEntity<Ville> getVilleById(Integer id) {
        return ResponseEntity.ok(this.villeRepository.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<ServerResponse> deleteVille(Integer id) {
        this.villeRepository.deleteById(id);
        return ResponseEntity.ok(new ServerResponse("Ville deleted successfully",true));
    }

    @Override
    public ResponseEntity<List<Quartier>> getAllQuartier() {
        return ResponseEntity.ok(this.quartierRepository.findAll());
    }

    @Override
    public ResponseEntity<ServerResponse> createQuartier(String quartier) {
        QuartierDTO quartierDTO = this.objectMapper.readValue(quartier, QuartierDTO.class);

        Quartier quartierDb = new Quartier();
        quartierDb.setNom(quartierDTO.getNom());
        quartierDb.setVille(this.villeRepository.findById(quartierDTO.getVille().getId()).orElse(null));

        this.quartierRepository.save(quartierDb);

        return ResponseEntity.ok(new ServerResponse("Quartier created successfully", true));
    }

    @Override
    public ResponseEntity<ServerResponse> updateQuartier(String quartier) {
        return null;
    }

    @Override
    public ResponseEntity<Quartier> getQuartierById(Integer id) {
        return ResponseEntity.ok(this.quartierRepository.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<ServerResponse> deleteQuartier(Integer id) {
        return null;
    }
}
