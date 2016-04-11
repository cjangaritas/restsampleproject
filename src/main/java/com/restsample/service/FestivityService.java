package com.restsample.service;

import com.restsample.data.model.Festivity;

import java.util.List;

public interface FestivityService {

    List<Festivity> findAll();
    Festivity create(Festivity festivity );
    Festivity findOne(String id);
    Festivity update(Festivity festivity);
}
