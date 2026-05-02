package com.example.hysacam.Controller.Passage;

import com.example.hysacam.Entity.Server.ServerResponse;
import com.example.hysacam.Entity.Ville.Passage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/hysacam/passage")
@CrossOrigin("*")
public interface PassageControllerInt {
    //Passage
    @GetMapping("/all")
    ResponseEntity<List<Passage>> findaLLPassage();
    @GetMapping("/all/byquartier/{id}")
    ResponseEntity<List<Passage>> findaLLPassageByQuartier(@PathVariable Integer id);
    @PostMapping("/create")
    ResponseEntity<ServerResponse> createPassage(@RequestParam("passage") String passage) ;
    @PostMapping("/update")
    ResponseEntity<ServerResponse> updatePassage(@RequestParam("passage") String passage) ;
    @GetMapping("/get/{id}")
    ResponseEntity<Passage> getPassageById(@RequestParam Integer id) ;
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ServerResponse> deletePassage(@RequestParam Integer id) ;

}
