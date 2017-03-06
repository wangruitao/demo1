package org.template.com.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.template.com.model.Users;

public interface TestMapper {

	public static final String colunm = "id,user_name,log_name,user_password,user_level,user_loc";
	
	@Insert("insert into users(user_name,log_name,user_password,user_level,user_loc) "
			+ "values(#{userName},'city','provi',1,1)")
	public int insert(@Param("userName") String userName);
	
	@Select(" select " + colunm + " from users where id=#{id}")
	@Results({
		@Result(property = "userName",  column = "user_name"),
		@Result(property = "logName",  column = "log_name"),
		@Result(property = "userPassword",  column = "user_password"),
		@Result(property = "userLevel",  column = "user_level"),
		@Result(property = "userLoc",  column = "user_loc")
	})
	public Users queryEntry(@Param("id") Long id);
	
	public Users getOne(Long id);
}
