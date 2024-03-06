package com.example.encore_spring_pjt;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.mapper.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class MybatisORMTests {

    @Autowired
    private BoardMapper boardMapper;
    @Test
    public void ormSave(){
        // System.out.println("debug >>> " + boardMapper);
        // BoardRequest request = new BoardRequest();
        // request.setTitle("ORM 수업 title");
        // request.setContent("Mapping content");
        // request.setWriter("encore writer");
        // request.setNoticeYn(true);
        // request.setSecretYn(true);
        BoardRequest request = BoardRequest.builder()
                                            .title("service")
                                            .content("mapper")
                                            .writer("encore")
                                            .noticeYn(true)
                                            .secretYn(true)
                                            .build();
        boardMapper.save(request);
        System.out.println("debug >>> save success");
    }
    @Test
    public void ormFind() throws Exception{
        System.out.println("debug finder >>> ");
        // BoardRequest request = new BoardRequest();
        // request.setIdx(1);
        BoardRequest request = BoardRequest.builder()
                                            .idx(1)
                                            .build();
        BoardResponse response = boardMapper.findByIdx(request);
        System.out.println("debug find result >>> ");
        System.out.println(response);
        System.out.println(">>>>>>>> json");
        String boardJson = new ObjectMapper().
                                registerModule(new JavaTimeModule()).
                                writeValueAsString(response);
        System.out.println(boardJson);
    }
    @Test
    public void ormUpdate(){
        System.out.println("debug update >>> ");
        // BoardRequest request = new BoardRequest();
        // request.setIdx(1);
        // request.setTitle("변경된 title");
        // request.setContent("변경된 content");
        // request.setWriter("변경된 writer");
        BoardRequest request = BoardRequest.builder()
                                            .idx(1)
                                            .title("update2 title")
                                            .content("update2 content")
                                            .writer("update2 writer")
                                            .build();
        boardMapper.updateByIdx(request);
        System.out.println("debug >>> update success");
    }
    @Test
    public void ormCount(){
        System.out.println("debug count >>> ");
        int cnt = boardMapper.count();
        System.out.println("debug >>> count success " + cnt);
    }
    @Test
    public void ormDelete(){
        System.out.println("debug delete >>> ");
        // BoardRequest request = new BoardRequest();
        // request.setIdx(1);
        BoardRequest request = BoardRequest.builder()
                                            .idx(1)
                                            .build();
        boardMapper.deleteByIdx(request);
        System.out.println("debug >>> delete success");
    }
    @Test
    public void ormFindAll() throws Exception{
        System.out.println("debug findAll >>> ");
        List<BoardResponse> responseList = boardMapper.findAll();
        System.out.println("debug find result >>> ");
        System.out.println(responseList);
    }
}
