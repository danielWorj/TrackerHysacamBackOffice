package com.example.hysacam.Repository;

import com.example.hysacam.Entity.Ville.Poubelle;
import com.example.hysacam.Entity.Ville.Quartier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoubelleRepository extends JpaRepository<Poubelle,Integer> {
    List<Poubelle> findByQuartier(Quartier quartier);
}
