package com.restsample.service;

import com.restsample.data.model.Festivity;

import java.util.List;

public interface FestivityService {

    List<Festivity> findAll();
    Festivity create(Festivity festivity );
    Festivity update(Festivity festivity);
}
