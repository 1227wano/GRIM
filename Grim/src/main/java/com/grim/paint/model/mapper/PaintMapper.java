package com.grim.paint.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.grim.paint.model.dto.PaintDTO;
import com.grim.paint.model.dto.PaintPicDTO;
import com.grim.paint.model.dto.SearchPaintDTO;

@Mapper
public interface PaintMapper {

	@Insert("INSERT INTO TB_PAINT_BOARD VALUES(SEQ_PBNO.NEXTVAL, #{picTitle}, #{picContent}, SYSDATE, #{count}, 'Y', #{userNo})")
	void savePaintBoard(PaintDTO board);

	@Insert("INSERT INTO TB_PAINT VALUES(SEQ_PNO.NEXTVAL, #{picName}, SYSDATE, 'Y', SEQ_PBNO.CURRVAL)")
	void savePaint(PaintPicDTO paintPicDto);
	
	
	List<SearchPaintDTO> findAll(RowBounds rowBounds);
	
	
	
	
}
