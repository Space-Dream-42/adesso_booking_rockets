package com.example.spacedream.Repository;

import com.example.spacedream.Entities.Mission;
import com.example.spacedream.Entities.Spacemanager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpacemanagerRepository extends JpaRepository<Spacemanager, String> {
}
