package com.example.hysacam.Controller.Alerte;

import com.example.hysacam.Entity.Alerte.Alerte;
import com.example.hysacam.Entity.Server.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/hysacam/alerte")
@CrossOrigin("*")
public interface AlerteControllerInt {
    @GetMapping("/all/bypriorite")
    ResponseEntity<List<Alerte>> getAllAlerteByPriority() ;
    @PostMapping("/create")
    ResponseEntity<ServerResponse> createAlerte(@RequestParam("file")MultipartFile file) ;
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ServerResponse> deleteAlerte(@PathVariable Integer id) ;

}
