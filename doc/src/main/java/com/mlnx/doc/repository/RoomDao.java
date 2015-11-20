package com.mlnx.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mlnx.doc.entity.Room;

public interface RoomDao extends JpaRepository<Room, Integer>,
JpaSpecificationExecutor<Room> {

}
