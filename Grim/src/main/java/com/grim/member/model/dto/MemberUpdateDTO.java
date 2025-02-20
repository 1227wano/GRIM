package com.grim.member.model.dto;

import jakarta.validation.constraints.Email;
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
public class MemberUpdateDTO {
	
	private Long userNo;
	
	@NotBlank(message="❌ 별명은 비어있을 수 없습니다.")
	@Size(min = 2, max = 5, message="❌ 별명은 2글자 이상 5글자 이하만 입력할 수 있습니다.")
	@Pattern(regexp="^[a-zA-Z가-힣]*$", message="❌ 별명은 한글/영어만 입력할 수 있습니다.")
	private String userName;
	
	@NotBlank(message="❌ 주소는 비어있을 수 없습니다.")
	private String userAddress;
	
	@NotBlank(message="❌ 이메일은 비어있을 수 없습니다.")
	@Pattern(regexp="^[a-zA-Z0-9@.]*$", message="❌ 올바른 형식의 이메일 주소여야 합니다.")
	@Email(message="❌ 올바른 형식의 이메일 주소여야 합니다.")
	private String userEmail;
	
	private String userFileUrl;

}
