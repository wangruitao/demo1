package org.template.com.vo;

import lombok.Data;

@Data
public class UserVo {

	private Long id;
	private String name;
	private String logName;
	private String userPassword;
	private Integer userLevel;
	private Integer userLoc;
}
