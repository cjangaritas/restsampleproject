package com.restsample.service.impl;

import com.restsample.data.model.Festivity;
import com.restsample.repository.FestivityRepository;
import com.restsample.service.FestivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
@Service
@Transactional
public class FestivityServiceImpl implements FestivityService{
    @Inject
    FestivityRepository festivityRepository;

    @Override
    public List<Festivity> findAll() {
        return festivityRepository.findAll();
    }

    @Override
    public Festivity create(Festivity festivity) {
        return festivityRepository.save(festivity);
    }

    @Override
    public Festivity findOne(String id) {
        return festivityRepository.findOne(id);
    }

    @Override
    public Festivity update(Festivity festivity) {
        return festivityRepository.save(festivity);
    }
}
