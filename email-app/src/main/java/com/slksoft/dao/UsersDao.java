package com.slksoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.slksoft.entity.Message;
import com.slksoft.entity.User;

public interface UsersDao {

	@Insert("insert into users(name,gender,email,phone,password,created_at) values(#{name},#{gender},#{email},#{phone},md5(#{password}),now())")
	public void addNewUser(User user);

	@Select("select * from users where email = #{e} and password = md5(#{pass})")
	public User loginUser(@Param("e") String email, @Param("pass") String password);

//	@Update("update users set phone = #{phone} ,city = #{city}, country= #{country} , state = #{state} where email= #{email} ")
//	public void updateDetails(User user);

	@Insert("insert into message(msg_from,msg_to,msg_cc,subject,body,send_at) values(#{msgFrom},#{msgTo},#{cc},#{subject},#{body},#{sendAt})")
	public void addNewMessage(Message msg);

	@Select("select * from Message where msg_to=#{email} or FIND_IN_SET(#{email}, msg_cc)")
	@Results({ @Result(column = "msg_from", property = "msgFrom"), @Result(column = "msg_to", property = "msgTo"),
			@Result(column = "msg_cc", property = "cc"), @Result(column = "send_at", property = "sendAt") })
	public List<Message> getMsgByEmail(String email);
	
	
	@Select("select * from Message where id=#{id}")
	@Results({ @Result(column = "msg_from", property = "msgFrom"), @Result(column = "msg_to", property = "msgTo"),
			@Result(column = "msg_cc", property = "cc"), @Result(column = "send_at", property = "sendAt") })
	public Message getMsgById(Integer id);

	@Select("select * from Message where msg_from=#{email}")
	@Results({ @Result(column = "msg_from", property = "msgFrom"), @Result(column = "msg_to", property = "msgTo"),
			@Result(column = "msg_cc", property = "cc"), @Result(column = "send_at", property = "sendAt") })
	public List<Message> getSentMsgByEmail(String email);
}
