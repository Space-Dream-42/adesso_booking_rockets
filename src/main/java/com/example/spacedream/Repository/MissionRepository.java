package com.example.spacedream.Repository;

import com.example.spacedream.Entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {
    List<Mission> findBySpacemanager_UserName(String spacemanager_userName);

    List<Mission> findByStartPlanetAndTargetPlanet(String startPlanet, String targetPlanet);

    Mission findById(int id);
}
