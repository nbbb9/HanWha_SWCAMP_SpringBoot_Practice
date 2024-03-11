package com.example.encore_spring_pjt.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;

//encore_board_tbl과 crud작업을 위한 추상메서드 선언
//Mapper는 기존의 DAO와 동일한 작업을 진행하는 것.
@Mapper
public interface BoardMapper {
    //insert
    public void save(BoardRequest params);
    //select(single finder)
    public Optional<BoardResponse> findByIdx(BoardRequest params);
    //update(title, content, writer) - idx
    public void updateByIdx(BoardRequest params);
    //레코드의 건수를 카운트
    public int count();
    //delete - idx를 기준으로 삭제 (delete yn & delete time)
    public void deleteByIdx(BoardRequest params);
    //multifinder
    public List<BoardResponse> findAll();
    //조회수 등가 메서드
    public void updateByCnt(BoardRequest params); 
}
