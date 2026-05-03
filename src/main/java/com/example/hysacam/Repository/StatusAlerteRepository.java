package com.example.hysacam.Repository;

import com.example.hysacam.Entity.Alerte.StatutAlerte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusAlerteRepository extends JpaRepository<StatutAlerte,Integer> {
}
