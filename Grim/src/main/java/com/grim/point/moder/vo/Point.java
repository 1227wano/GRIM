package com.grim.point.moder.vo;

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
public class Point {
	
	private Long userNo;
	private int point;
	private String reason;
	private Date reasonDate;

}
