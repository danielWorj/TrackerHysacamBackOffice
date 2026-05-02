package com.example.hysacam.Dto;

import com.example.hysacam.Entity.Ville.Quartier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PassageDTO {
    private Integer id ;
    private LocalDate datePassage ;
    private Integer status ;
    private String periode ;
    private Integer quartier ;
}
