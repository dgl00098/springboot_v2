package com.fc.test.mapper.dao;

import com.fc.test.model.auto.TSysRoleUser;
import com.fc.test.model.auto.TsysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface TsysRoleUserRepository extends JpaRepository<TSysRoleUser, Serializable> {

    List<TSysRoleUser> findBySysRoleId(String id);

    List<TSysRoleUser> findBySysUserId(String id);

    TSysRoleUser findById(String id);

    @Modifying
    @Query(value = "update t_sys_role_user sru set sru.sys_role_id=?1 where sru.sys_user_id=?2",nativeQuery = true)
    void updateRoleUser(String roleId,String userId);
}
