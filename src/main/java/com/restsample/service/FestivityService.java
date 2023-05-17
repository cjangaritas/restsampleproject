package com.restsample.service;

import com.restsample.data.model.Festivity;

import java.util.List;
import java.util.Optional;

public interface FestivityService {

    List<Festivity> findAll();
    Festivity create(Festivity festivity );
    Optional<Festivity> findOne(String id);
    Festivity update(Festivity festivity);
}
