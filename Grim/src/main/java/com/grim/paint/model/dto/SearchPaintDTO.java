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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SearchPaintDTO {
	private long picBoardNo;
	private String picTitle;
	private String picContent;
	private Date writeDate;
	private long count;
	private String status;
	private long userNo;
	private Long picNo;
	private String picName;
	private Date picDate;
	private String userName;
	private Long like;
}
