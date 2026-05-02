package com.example.hysacam.Dto;

import com.example.hysacam.Entity.Ville.Poubelle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AlerteDTO {

    private Integer id ;
    private String url ;
    private String status ;
    private Double score ;
    private Boolean etat ;
    private Double latitude ;
    private Double longitude ;

    private Integer poubelle ;
}
