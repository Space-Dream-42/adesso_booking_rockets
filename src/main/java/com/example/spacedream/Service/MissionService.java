// diese packet dient als Schnittstelle zwischen der Datenbank und Enteties
// Hier werden auch die Objekte erzeugt
package com.example.spacedream.Service;

import com.example.spacedream.Entities.Mission;
import com.example.spacedream.Entities.Spacemanager;
import com.example.spacedream.Repository.MissionRepository;
import com.example.spacedream.Repository.SpacemanagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
public class MissionService {

    private MissionRepository missionRepository;

    @Autowired
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }


    public void createMission(Mission mission) {
        missionRepository.save(mission);
    }


    public void updateMission(Mission mission) throws EntityNotFoundException {
        if (missionRepository.existsById(mission.getId())) {
            missionRepository.save(mission);
        } else {
            throw new EntityNotFoundException("This instance doesn't exits!");
        }
    }


    public List<Mission> getMissionsBySpacemanagerUserName(String spacemanager_userName) {
        return missionRepository.findBySpacemanager_UserName(spacemanager_userName);
    }


    public List<Mission> getMissionsBySearchQuery(String startPlanet, String targetPlanet) {
        return missionRepository.findByStartPlanetAndTargetPlanet(startPlanet, targetPlanet);
    }


    public Mission findMissionById(int id) {
        return missionRepository.findById(id);
    }
}
