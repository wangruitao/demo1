package org.template.com.model;

import lombok.Data;

@Data
public class Users {


	private Long id;
	private String userName;
	private String logName;
	private String userPassword;
	private Integer userLevel;
	private Integer userLoc;

}