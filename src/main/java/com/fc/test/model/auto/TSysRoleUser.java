package com.fc.test.model.auto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "t_sys_role_user")
public class TSysRoleUser implements Serializable {
    @Id
    private String id;

    private String sysUserId;

    private String sysRoleId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


	public TSysRoleUser() {
		super();
	}

	public TSysRoleUser(String id, String sysUserId, String sysRoleId) {
		super();
		this.id = id;
		this.sysUserId = sysUserId;
		this.sysRoleId = sysRoleId;
	}
    
    
}