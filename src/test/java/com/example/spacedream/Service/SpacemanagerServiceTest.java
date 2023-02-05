package com.example.spacedream.Service;

import com.example.spacedream.Entities.Mission;
import com.example.spacedream.Entities.Spacemanager;
import com.example.spacedream.Repository.SpacemanagerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class SpacemanagerServiceTest {

    private SpacemanagerService spacemanagerService;
    @MockBean
    private SpacemanagerRepository spacemanagerRepositoryMock;
    private Spacemanager spacemanagerToTest;
    private String userName;


    @Before
    public void setUp() {
        spacemanagerToTest = new Spacemanager();
        userName = "Armstrong";
        this.spacemanagerService = new SpacemanagerService(spacemanagerRepositoryMock);
        when(spacemanagerRepositoryMock.findById(userName)).thenReturn(Optional.of(spacemanagerToTest));
    }


    @Test
    public void testCreateSpacemanager() {
        // given

        // when
        spacemanagerService.createSpacemanager(spacemanagerToTest);

        // then
        verify(spacemanagerRepositoryMock).save(spacemanagerToTest);
    }


    @Test
    public void testGetSpacemanager() {
        // given

        // when
        spacemanagerService.getSpacemanager("Armstrong");

        // then
        verify(spacemanagerRepositoryMock).findById("Armstrong");
    }


    @Test
    public void testValidSpacemanagerAndPassword_rightPassword() {
        // given
        String password = "Artemis1";
        spacemanagerToTest.setUserName(userName);
        spacemanagerToTest.setPassword(password);

        // when
        boolean resultRightInput = spacemanagerService.validSpacemanagerAndPassword(userName, password);

        // then
        assertThat(resultRightInput).isTrue();
    }

    @Test
    public void testValidSpacemanagerAndPassword_falsePassword() {
        // given
        String password = "Artemis1";
        spacemanagerToTest.setUserName(userName);
        spacemanagerToTest.setPassword(password);

        // when
        boolean resultFalseInput = spacemanagerService.validSpacemanagerAndPassword(userName, "123");

        // then
        assertThat(resultFalseInput).isFalse();
    }
}
