package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter@Getter
public class Employee extends BaseDomain{
	private String name;
	private String password;
	private int age;
	private String email;
	private boolean admin=false;//是否是管理员
	private Department dept;
	private List<Role> roles=new ArrayList<>();//员工的所有角色

	public String getRolesName(){
		String rolesName="";
		if(isAdmin()){
			return "超级管理员";
		}
		if(roles.size()==0){
			return "暂无角色";
		}
		for (Role role : roles) {
			rolesName+=role.getName()+" ";
		}
		return rolesName;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", password='" + password + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				", admin=" + admin +
				'}';
	}
}
