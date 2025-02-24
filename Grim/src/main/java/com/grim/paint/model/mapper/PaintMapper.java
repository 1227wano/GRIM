package com.grim.paint.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	
	@Update("UPDATE TB_PAINT_BOARD SET PIC_TITLE = #{picTitle}, PIC_CONTENT = #{picContent} WHERE PIC_BOARD_NO = #{picBoardNo}")
	void updatePaintBoard(PaintDTO board);
		
	@Select("SELECT USER_NO userNo FROM TB_PAINT_BOARD WHERE PIC_BOARD_NO = #{id}")
	PaintDTO findById(long id);
	
	List<SearchPaintDTO> findAll(RowBounds rowBounds);
	
 
    
	
}
