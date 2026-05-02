package com.example.hysacam.Controller.Passage;

import com.example.hysacam.Dto.PassageDTO;
import com.example.hysacam.Entity.Server.ServerResponse;
import com.example.hysacam.Entity.Ville.Passage;
import com.example.hysacam.Entity.Ville.Quartier;
import com.example.hysacam.Repository.PassageRepository;
import com.example.hysacam.Repository.QuartierRepository;
import com.example.hysacam.Repository.StatusPassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
public class PassageControllerImpl implements PassageControllerInt{
    @Autowired
    private PassageRepository passageRepository;
    @Autowired
    private StatusPassageRepository statusPassageRepository;
    @Autowired
    private QuartierRepository quartierRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<List<Passage>> findaLLPassage() {
        return ResponseEntity.ok(this.passageRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Passage>> findaLLPassageByQuartier(Integer id) {
        return ResponseEntity.ok(this.passageRepository.findAll().stream().filter(passage -> passage.getQuartier().getId().equals(id)).toList());
    }

    @Override
    public ResponseEntity<ServerResponse> createPassage(String passage) {
        PassageDTO passageDTO = this.objectMapper.readValue(passage, PassageDTO.class);

        Passage passageDB = new Passage();

        passageDB.setDatePassage(passageDTO.getDatePassage());
        passageDB.setStatus(this.statusPassageRepository.findById(passageDTO.getStatus()).orElse(null));
        passageDB.setPeriode(passageDTO.getPeriode());
        passageDB.setQuartier(this.quartierRepository.findById(passageDTO.getQuartier()).orElse(null));
        this.passageRepository.save(passageDB);
        return ResponseEntity.ok(new ServerResponse("Passage créé avec succès", true));
    }

    @Override
    public ResponseEntity<ServerResponse> updatePassage(String passage) {
        return null;
    }

    @Override
    public ResponseEntity<Passage> getPassageById(Integer id) {
        return ResponseEntity.ok(this.passageRepository.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<ServerResponse> deletePassage(Integer id) {
        this.passageRepository.deleteById(id);
        return ResponseEntity.ok(new ServerResponse("Passage supprimé avec succès", true));
    }
}
