package com.grim.point.moder.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class PointDTO {
	
	private Long userNo;
	private int point;
	private String reason;
	private Date reasonDate;

}
