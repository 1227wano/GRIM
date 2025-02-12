package com.grim.member.model.dto;

import com.grim.point.moder.dto.PointDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberInfoResponseDTO {
	
	private MemberDTO member;
	private PointDTO point;
	
	

}
