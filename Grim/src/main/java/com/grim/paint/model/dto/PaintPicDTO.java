package com.grim.paint.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PaintPicDTO {
	
	private Long picNo;
	private String picName;
	private Date picDate;
	private String status;
	private Long picBoardNo;
	
}
