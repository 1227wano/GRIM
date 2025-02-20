package com.grim.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class MemberUpdateDTO {
	
	private Long userNo;
	
	private String userName;
	
	private String userAddress;
	
	private String userEmail;
	
	private String userFileUrl;

}
