package com.grim.member.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class MemberDTO {
	
	private Long userNo;
	private String userId;
	private String userPwd;
	private String userAddress;
	private String userName;
	private String userEmail;
	private String role;
	private String userState;
	private Date userDate;

}
