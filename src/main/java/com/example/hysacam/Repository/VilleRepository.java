package com.example.hysacam.Repository;

import com.example.hysacam.Entity.Ville.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville,Integer> {

}
