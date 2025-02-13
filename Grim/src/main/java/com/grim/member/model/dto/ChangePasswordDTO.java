package com.grim.member.model.dto;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Password")
public class ChangePasswordDTO {
	
	
	@NotBlank(message="❌ 비밀번호는 비어있을 수 없습니다.")
	@Size(min = 8, max = 16, message="❌ 비밀번호는 8글자 이상 12글자 이하만 입력할 수 있습니다.")
	@Pattern(regexp="^[a-zA-Z0-9!@#$%^&*?]*$", message="❌ 비밀번호는 영어/숫자(!, @, #, $, %, ^, &, *, ?)\n   만 입력할 수 있습니다.")
	private String currentPassword;
	
	@NotBlank(message="❌ 비밀번호는 비어있을 수 없습니다.")
	@Size(min = 8, max = 16, message="❌ 비밀번호는 8글자 이상 12글자 이하만 입력할 수 있습니다.")
	@Pattern(regexp="^[a-zA-Z0-9!@#$%^&*?]*$", message="❌ 비밀번호는 영어/숫자(!, @, #, $, %, ^, &, *, ?)\n   만 입력할 수 있습니다.")
	private String newPassword;
	
	@NotBlank(message="❌ 비밀번호는 비어있을 수 없습니다.")
	@Size(min = 8, max = 16, message="❌ 비밀번호는 8글자 이상 12글자 이하만 입력할 수 있습니다.")
	@Pattern(regexp="^[a-zA-Z0-9!@#$%^&*?]*$", message="❌ 비밀번호는 영어/숫자(!, @, #, $, %, ^, &, *, ?)\n   만 입력할 수 있습니다.")
	private String newPasswordCheck;
}
 