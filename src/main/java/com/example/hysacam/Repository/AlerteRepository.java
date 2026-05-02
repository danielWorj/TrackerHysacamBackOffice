package com.example.hysacam.Repository;

import com.example.hysacam.Entity.Alerte.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlerteRepository extends JpaRepository<Alerte,Integer> {
    List<Alerte> findAllOrderByScoreDesc() ;

}
