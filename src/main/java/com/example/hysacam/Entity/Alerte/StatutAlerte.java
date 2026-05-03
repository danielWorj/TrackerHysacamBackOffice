package com.example.hysacam.Entity.Alerte;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class StatutAlerte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String nom ;
    //1- URGENT
    //2- NORMAL
}
