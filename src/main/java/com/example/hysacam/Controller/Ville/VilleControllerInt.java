package com.example.hysacam.Controller.Ville;

import com.example.hysacam.Entity.Info.Info;
import com.example.hysacam.Entity.Info.TypeInfo;
import com.example.hysacam.Entity.Server.ServerResponse;
import com.example.hysacam.Entity.Ville.Quartier;
import com.example.hysacam.Entity.Ville.Ville;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/hysacam/ville")
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

    //Info
    @GetMapping("/info/all")
    ResponseEntity<List<Info>> getAllInfo() ;
    @PostMapping("/info/create")
    ResponseEntity<ServerResponse> createInfo(@RequestParam("info") String info) ;
    @PostMapping("/info/update")
    ResponseEntity<ServerResponse> updateInfo(@RequestParam("info") String info) ;
    @GetMapping("/info/get/{id}")
    ResponseEntity<Info> getInfoById(@PathVariable Integer id) ;
    @DeleteMapping("/info/delete/{id}")
    ResponseEntity<ServerResponse> deleteInfo(@PathVariable Integer id) ;

    //tYpeInfo
    @GetMapping("/typeinfo/all")
    ResponseEntity<List<TypeInfo>> getAllTypeInfo() ;
    @PostMapping("/typeinfo/create")
    ResponseEntity<ServerResponse> createTypeInfo(@RequestParam("typeinfo") String typeInfo) ;
    @PostMapping("/typeinfo/update")
    ResponseEntity<ServerResponse> updateTypeInfo(@RequestParam("typeinfo") String typeInfo) ;
    @GetMapping("/typeinfo/get/{id}")
    ResponseEntity<TypeInfo> getTypeInfoById(@PathVariable Integer id) ;
}
