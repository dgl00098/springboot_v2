package com.fc.test.model.auto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_sys_role")
@Data
public class TsysRole implements Serializable {
    @Id
    private String id;

    private String name;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public TsysRole(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public TsysRole() {
		super();
	}
    
}