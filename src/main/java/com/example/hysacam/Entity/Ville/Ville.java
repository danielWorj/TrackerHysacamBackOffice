package com.example.hysacam.Entity.Ville;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String nom ;
    private String description ;
}
