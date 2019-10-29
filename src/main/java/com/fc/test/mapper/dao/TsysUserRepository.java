package com.fc.test.mapper.dao;

import com.fc.test.model.auto.TsysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface TsysUserRepository extends JpaRepository<TsysUser, Serializable> {

    TsysUser findById(String id);

    TsysUser findByUsername(String userName);

    @Modifying
    @Query(value = "update t_sys_user su set su.name=?1 ,su.address=?2 where su.id=?3",nativeQuery = true)
    void updateUser(String name,String address,String id);
}
