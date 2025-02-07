package com.grim.member.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Password")
public class ChangePasswordDTO {
	
	private String currentPassowrd;
	
	private String newPassword;
}
 