package com.fc.test.service;

import com.fc.test.common.base.UserService;
import com.fc.test.mapper.dao.TsysRoleRepository;
import com.fc.test.mapper.dao.TsysRoleUserRepository;
import com.fc.test.mapper.dao.TsysUserRepository;
import com.fc.test.model.auto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService的实现类
 * @author 杜光磊
 * @date 2019年10月27日
 *
 */
@Service
public class SysUserServiceImpl implements UserService{

	@Autowired
	TsysUserRepository tsysUserRepo;
	@Autowired
	TsysRoleUserRepository tsysRoleUserRepo;
	@Autowired
	TsysRoleRepository tsysRoleRepo;

	/**
	 * 通过id查找用户
	 * @param id
	 * @return
	 */
	@Override
	public TsysUser findById(String id) {
		TsysUser tsysUser = tsysUserRepo.findById(id);
		return tsysUser;
	}

	//更新用户角色信息
	@Override
	public int updateUserRole(TsysUser tsysUser, List<String> roles) {
		//查询用户id用户角色信息
		List<TSysRoleUser> urList = tsysRoleUserRepo.findBySysUserId(tsysUser.getId());
		//更新用户角色信息
		if (roles.size()>0){
			for (String role : roles) {
				tsysRoleUserRepo.updateRoleUser(role,tsysUser.getId());
			}
		}else{
			tsysRoleUserRepo.updateRoleUser("2",tsysUser.getId());
		}
		//更新用户信息
		tsysUserRepo.updateUser(tsysUser.getName(),tsysUser.getAddress(),tsysUser.getId());

		return 1;
	}
}
