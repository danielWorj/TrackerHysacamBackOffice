package com.example.hysacam.Entity.Ville;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class StatusPassage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String nom ;
}
