package com.example.spacedream.Repository;

import com.example.spacedream.Entities.Mission;
import com.example.spacedream.Entities.Spacemanager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MissionRepositoryTest {

    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private SpacemanagerRepository spacemanagerRepository;

    @Test
    public void testFindBySpacemanager_UserName() {
        // given
        String userNameOfSpacemanagerToTest = "Armstrong";
        String userNameOfSpacemanagerNotToTest = "Artemis";
        Spacemanager spacemanagerToTest = new Spacemanager(userNameOfSpacemanagerToTest, "saturn5", "NASA", new ArrayList<Mission>());
        Spacemanager spacemanagerNotToTest = new Spacemanager(userNameOfSpacemanagerNotToTest, "saturn5", "NASA", new ArrayList<Mission>());
        Mission testMission1 = new Mission();
        Mission testMission2 = new Mission();
        Mission testMission3 = new Mission();
        testMission2.setSpacemanager(spacemanagerToTest);
        testMission2.setSpacemanager(spacemanagerToTest);
        testMission3.setSpacemanager(spacemanagerNotToTest);
        spacemanagerRepository.save(spacemanagerToTest);
        spacemanagerRepository.save(spacemanagerNotToTest);
        testMission1.setSpacemanager(spacemanagerToTest);
        testMission2.setSpacemanager(spacemanagerToTest);
        testMission3.setSpacemanager(spacemanagerNotToTest);
        missionRepository.save(testMission1);
        missionRepository.save(testMission2);
        missionRepository.save(testMission3);

        // when
        List<Mission> resultList = missionRepository.findBySpacemanager_UserName(userNameOfSpacemanagerToTest);

        // then
        assertThat(resultList).containsExactlyInAnyOrder(testMission1, testMission2);

    }

    @Test
    public void testFindByStartPlanetAndTargetPlanet() {
        // given
        Mission testMission1 = new Mission();
        Mission testMission2 = new Mission();
        Mission testMission3 = new Mission();
        String startPlanet1And2 = "Earth";
        String targetPlanet1And2 = "Mars";
        String startPlanet3 = "Mars";
        String targetPlanet3 = "Earth";
        Spacemanager spacemanagerToTest = new Spacemanager("Armstrong", "saturn5", "NASA", new ArrayList<Mission>());
        spacemanagerRepository.save(spacemanagerToTest);
        testMission1.setStartPlanet(startPlanet1And2);
        testMission1.setTargetPlanet(targetPlanet1And2);
        testMission2.setStartPlanet(startPlanet1And2);
        testMission2.setTargetPlanet(targetPlanet1And2);
        testMission3.setStartPlanet(startPlanet3);
        testMission3.setTargetPlanet(targetPlanet3);
        testMission1.setSpacemanager(spacemanagerToTest);
        testMission2.setSpacemanager(spacemanagerToTest);
        testMission3.setSpacemanager(spacemanagerToTest);
        List<Mission> expectedList = Arrays.asList(testMission1, testMission2);
        missionRepository.save(testMission1);
        missionRepository.save(testMission2);
        missionRepository.save(testMission3);

        // when
        List<Mission> resultList = missionRepository.findByStartPlanetAndTargetPlanet(startPlanet1And2, targetPlanet1And2);

        // then
        assertThat(resultList).containsExactlyInAnyOrder(testMission1, testMission2);
    }

}
