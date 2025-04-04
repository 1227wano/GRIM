package com.grim.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	private Long userNo;
	private String userId;
	private String userPwd;
	private String userAddress;
	private String userName;
	private String userEmail;
	private String role;
	private String userState;
	private Date userDate;
	private String userFileUrl;
	
}
