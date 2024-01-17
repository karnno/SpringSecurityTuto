package com.myapp.tuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.tuto.model.DBUser;

@Repository
public interface DBUserRepository extends JpaRepository<DBUser, Integer>{

	
	public DBUser findByUsername(String username);
}
