package com.babobird.Toto.repository;

import com.babobird.Toto.entity.Toto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotoRepository extends JpaRepository<Toto, Long> {
}

