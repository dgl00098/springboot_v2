package com.fc.test.mapper.dao;

import com.fc.test.model.auto.TSysRoleUser;
import com.fc.test.model.auto.TsysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface TsysRoleRepository extends JpaRepository<TsysRole, Serializable> {

    TsysRole findById(String id);

    TsysRole findByName(String name);
}
