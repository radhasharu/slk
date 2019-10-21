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
public class Message {
	
	
	private Integer id;
	private String msgFrom;
	private String msgTo;
	private String cc;
	private String subject;
	private String body;
	private Date sendAt;
	

}
