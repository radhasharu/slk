package com.slksoft.entity;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
	
	
	private Integer id;
	private String name;
	private String gender;
	private String email;
	private String phone;
	private String password;
	private Date createdAt;
	

}
