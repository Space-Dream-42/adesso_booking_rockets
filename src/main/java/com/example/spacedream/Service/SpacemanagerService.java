package com.example.spacedream.Service;

import com.example.spacedream.Entities.Spacemanager;
import com.example.spacedream.Repository.SpacemanagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SpacemanagerService {

    private SpacemanagerRepository spacemanagerRepository;

    @Autowired
    public SpacemanagerService(SpacemanagerRepository spacemanagerRepository) {
        this.spacemanagerRepository = spacemanagerRepository;
    }


    public void createSpacemanager(Spacemanager spacemanager) {
        spacemanagerRepository.save(spacemanager);
    }


    public Optional<Spacemanager> getSpacemanager(String userName) {
        return spacemanagerRepository.findById(userName);
    }


    public Boolean validSpacemanagerAndPassword(String userName, String password) {
        Optional<Spacemanager> spacemanager = this.getSpacemanager(userName);
        return spacemanager.isPresent() && spacemanager.get().getPassword().equals(password);
    }
}