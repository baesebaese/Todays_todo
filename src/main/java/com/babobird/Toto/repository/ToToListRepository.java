package com.babobird.Toto.repository;

import com.babobird.Toto.domain.To_to_list;

import java.util.List;
import java.util.Optional;

public interface ToToListRepository {

    To_to_list save(To_to_list to_to_list);
    Optional<To_to_list> findById(int id);
    Optional<To_to_list> findByName(String name);
    List<To_to_list> findAll();

}