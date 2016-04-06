package com.restsample.repository;

import com.restsample.data.model.Festivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FestivityRepository extends MongoRepository<Festivity, String> {
    public List<Festivity> findAll();
}
