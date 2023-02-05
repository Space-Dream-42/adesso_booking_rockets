package com.example.spacedream.Service;

import com.example.spacedream.Entities.Customer;
import com.example.spacedream.Entities.Mission;
import com.example.spacedream.Entities.Spacemanager;
import com.example.spacedream.Exceptions.StillLoggedInException;
import com.example.spacedream.Repository.MissionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class MissionServiceTest {

    private MissionService missionService;
    @MockBean
    private MissionRepository missionRepositoryMock;
    private Mission missionToTest;
    private Spacemanager spacemanagerToTest;


    @Before
    public void setUp() {
        this.missionToTest = new Mission();
        this.spacemanagerToTest = new Spacemanager();
        this.missionService = new MissionService(missionRepositoryMock);
        this.spacemanagerToTest.setUserName("Armstrong");
        this.missionToTest.setSpacemanager(spacemanagerToTest);
        this.missionToTest.setSpacemanager(this.spacemanagerToTest);
        when(missionRepositoryMock.existsById(missionToTest.getId())).thenReturn(true);
    }


    @Test
    public void testCreateMission() {
        // given

        // when
        missionService.createMission(missionToTest);

        // then
        verify(missionRepositoryMock).save(missionToTest);
    }


    @Test
    public void testGetMissionsBySpacemanagerUserName() {
        // given

        // when
        missionService.getMissionsBySpacemanagerUserName("Armstrong");

        // then
        verify(missionRepositoryMock).findBySpacemanager_UserName("Armstrong");
    }


    @Test
    public void testGetMissionsBySearchQuery() {
        // given

        // when
        missionService.getMissionsBySearchQuery("Earth", "Mars");

        // then
        verify(missionRepositoryMock).findByStartPlanetAndTargetPlanet("Earth", "Mars");
    }


    @Test
    public void testFindMissionById() {
        // given

        // when
        missionService.findMissionById(1);

        // then
        verify(missionRepositoryMock).findById(1);
    }


    @Test
    public void testUpdateMission_missionIsSaved() {
        // given

        // when
        missionService.updateMission(missionToTest);

        // then
        verify(missionRepositoryMock).save(missionToTest);
    }

    @Test
    public void testUpdateMission_missionIsNotSaved() {
        // given
        Mission notSavedMission = new Mission();
        String expectedMessage = "This instance doesn't exits!";
        when(missionRepositoryMock.existsById(notSavedMission.getId())).thenReturn(false);

        // when
        Throwable thrown = catchThrowable(() -> {
            missionService.updateMission(notSavedMission);
        });

        // then
        verify(missionRepositoryMock, times(0)).save(missionToTest);
        assertThat(thrown).isInstanceOf(EntityNotFoundException.class).hasMessageContaining(expectedMessage);
    }
}
