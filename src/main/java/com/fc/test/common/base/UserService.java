package com.fc.test.common.base;

import com.fc.test.model.auto.TsysUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserController接口
 *
 * @author 杜光磊
 * @date 2019年10月27日
 */
@Transactional
public interface UserService{

    TsysUser findById(String id);

    int updateUserRole(TsysUser tsysUser, List<String> roles);


}
