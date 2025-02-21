package com.grim.museum.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class MuseumDTO {
	
	private Long userNo;
	
	@NotBlank(message="미술관명을 입력해주세요.")
	@Size(min = 1, max = 10, message="미술관명은 1-10글자만 입력할 수 있습니다.")
	@Pattern(regexp="^[0-9a-zA-Z가-힣]+$", message="한글, 영어, 숫자만 사용할 수 있습니다.")
	private String museumName;
	
	private String museumSidoName;
	private String museumOpen;
	private String museumClose;

}
