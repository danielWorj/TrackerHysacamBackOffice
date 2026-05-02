package com.example.hysacam.Controller.Ville;

import com.example.hysacam.Entity.Server.ServerResponse;
import com.example.hysacam.Entity.Ville.Quartier;
import com.example.hysacam.Entity.Ville.Ville;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("hysacam/api/ville")
@CrossOrigin("*")
public interface VilleControllerInt {
    //Ville
    @GetMapping("/all")
    ResponseEntity<List<Ville>> getAllVille() ;
    @PostMapping("/create")
    ResponseEntity<ServerResponse> createVille(@RequestParam("ville") String ville) ;
    @PostMapping("/update")
    ResponseEntity<ServerResponse> updateVille(@RequestParam("ville") String ville) ;
    @GetMapping("/get/{id}")
    ResponseEntity<Ville> getVilleById(@PathVariable Integer id) ;
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ServerResponse> deleteVille(@PathVariable Integer id) ;


    //Quartier
    @GetMapping("/quartier/all")
    ResponseEntity<List<Quartier>> getAllQuartier() ;
    @PostMapping("/quartier/create")
    ResponseEntity<ServerResponse> createQuartier(@RequestParam("quartier") String quartier) ;
    @PostMapping("/quartier/update")
    ResponseEntity<ServerResponse> updateQuartier(@RequestParam("quartier") String quartier) ;
    @GetMapping("/quartier/get/{id}")
    ResponseEntity<Quartier> getQuartierById(@PathVariable Integer id) ;
    @DeleteMapping("/quartier/delete/{id}")
    ResponseEntity<ServerResponse> deleteQuartier(@PathVariable Integer id) ;

}
