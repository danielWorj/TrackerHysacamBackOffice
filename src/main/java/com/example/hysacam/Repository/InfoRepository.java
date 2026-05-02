package com.example.hysacam.Repository;

import com.example.hysacam.Entity.Info.Info;
import com.example.hysacam.Entity.Info.TypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info,Integer> {
    List<Info> findAllByTypeInfo(TypeInfo typeInfo) ;
}
