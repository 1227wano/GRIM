package com.grim.member.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	
	@NotBlank(message="❌ 아이디는 비어있을 수 없습니다.")
	@Size(min = 6, max = 12, message="❌ 아이디는 6글자 이상 12글자 이하만 사용할 수 있습니다.")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="❌ 아이디는 영어/숫자만 사용할 수 있습니다.")
	private String userId;
	
	@NotBlank(message="❌ 비밀번호 값은 비어있을 수 없습니다.")
	@Size(min = 8, max = 16, message="❌ 비밀번호는 8글자 이상 12글자 이하만 사용할 수 있습니다.")
	@Pattern(regexp="^[a-zA-Z0-9!@#$%^&*?]*$", message="❌ 비밀번호는 영어/숫자(!, @, #, $, %, ^, &, *, ?)\n   만 사용할 수 있습니다.")
	private String userPwd;
	
	
	@NotBlank(message="❌ 주소는 비어있을 수 없습니다.")
	private String userAddress;
	
	@NotBlank(message="❌ 별명은 비어있을 수 없습니다.")
	@Size(min = 2, max = 5, message="❌ 별명은 2글자 이상 5글자 이하만 사용할 수 있습니다.")
	@Pattern(regexp="^[a-zA-Z가-힣]*$", message="❌ 별명은 한글/영어만 사용할 수 있습니다.")
	private String userName;
	
	@NotBlank(message="❌ 이메일은 비어있을 수 없습니다.")
	@Pattern(regexp="^[a-zA-Z0-1@]*$", message="❌ 잘못된 이메일 입니다.")
	private String userEmail;
	
	
	
	private String role;
	private String userState;
	private Date userDate;

}
