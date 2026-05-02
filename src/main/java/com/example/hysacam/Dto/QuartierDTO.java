package com.example.hysacam.Dto;

import com.example.hysacam.Entity.Ville.Quartier;
import com.example.hysacam.Entity.Ville.Ville;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
@Data
public class QuartierDTO {
    private Integer id ;
    private String nom ;
    private Ville ville ;
}
