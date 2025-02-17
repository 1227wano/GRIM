package com.grim.museum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class MuseumDTO {
	
	private String museumName;
	private String museumSidoName;
	private String museumOpen;
	private String museumClose;

}
