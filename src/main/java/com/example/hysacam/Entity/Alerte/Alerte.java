package com.example.hysacam.Entity.Alerte;

import com.example.hysacam.Entity.Ville.Poubelle;
import com.example.hysacam.Entity.Ville.Ville;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Alerte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String url ;
    private String status ;
    private Double score ;
    private Boolean etat ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Poubelle poubelle ;
}
