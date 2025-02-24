package com.grim.paint.model.dto;


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
public class DeletePaintDTO {
	
	private long picBoardNo;
	private long userNo;
	private String status;
}
