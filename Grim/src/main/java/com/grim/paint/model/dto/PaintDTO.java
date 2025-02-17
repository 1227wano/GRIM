package com.grim.paint.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class PaintDTO {

	private long picBoardNo;
	
	@NotBlank(message="❌ 제목은 비워둘 수 없습니다.")
	@Size(min = 1, max = 30, message="❌ 제목은 1글자 이상 30글자 이하만 사용할 수 있습니다.")
	@Pattern(regexp="^[0-9a-zA-Zㄱ-힣!@#$%^&*?\s]+$", message="❌ 내용에 한글, 영어, 특수문자는(!, @, #, $, %, ^, &, *, ?)\n   만 사용할 수 있습니다.")
	private String picTitle;
	
	
	@NotBlank(message="❌ 내용은 비워둘 수 없습니다.")
	@Size(min = 1, max = 30, message="❌ 내용은 1글자 이상 600글자 이하만 사용할 수 있습니다.")
	@Pattern(regexp="^[0-9a-zA-Zㄱ-힣!@#$%^&*?\s]+$", message="❌ 내용에 한글, 영어, 특수문자는(!, @, #, $, %, ^, &, *, ?)\n   만 사용할 수 있습니다.")
	private String picContent;
	
	private Date writeDate;
	private long count;
	private String status;
	private long userNo;
	
	
}
