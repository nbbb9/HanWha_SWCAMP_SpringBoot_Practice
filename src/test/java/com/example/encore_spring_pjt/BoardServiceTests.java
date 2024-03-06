package com.example.encore_spring_pjt;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;

import jakarta.annotation.Resource;

// DI = AutoWired, Resource
@SpringBootTest
public class BoardServiceTests {

    @Resource(name = "board")//post or board
    private BoardService service;

    @Test
    public void serviceSaveTest(){
        System.out.println("debug Test Save >>> " + service);
        //Builder를 사용하지 않는 레거시 방식
        // BoardRequest request = new BoardRequest();
        // request.setTitle("service title");
        // request.setContent("service content");
        // request.setWriter("service writer");
        // request.setNoticeYn(true);
        // request.setSecretYn(true);
        BoardRequest request = BoardRequest.builder()
                                            .title("service")
                                            .content("mapper")
                                            .writer("encore")
                                            .noticeYn(true)
                                            .secretYn(true)
                                            .build();
        System.out.println("debug >>> before save " + request);
        Integer idx = service.saveBoard(request);
        System.out.println("입력한 데이터의 키 값을 출력 : " + idx);
    }
    @Test
    public void serviceFindTest() throws Exception{
        //BoardRequest request = new BoardRequest();
        //request.setIdx(1);
        BoardRequest request = BoardRequest.builder()
                                            .idx(1)
                                            .build();
        BoardResponse response = service.findBoard(request);
        System.out.println("debug find result >>> ");
        System.out.println(response);
    }
    @Test
    public void serviceUpdateTest(){
        System.out.println("debug Test Update >>> " + service);
        // BoardRequest request = new BoardRequest();
        // request.setIdx(1);
        // request.setTitle("update  title");
        // request.setContent("update  content");
        // request.setWriter("update  writer");
        BoardRequest request = BoardRequest.builder()
                                            .idx(1)
                                            .title("update2 title")
                                            .content("update2 content")
                                            .writer("update2 writer")
                                            .build();
        Integer idx = service.updateBoard(request);
        System.out.println( idx + " 번지의 데이터를 수정");
    }
    @Test
    public void serviceDeleteTest(){
        // BoardRequest request = new BoardRequest();
        // request.setIdx(1);
        BoardRequest request = BoardRequest.builder()
                                            .idx(1)
                                            .build();
        Integer idx = service.deleteBoard(request);
        System.out.println("debug >>> delete success" + idx + "번째 데이터 삭제 완료");
    }
    @Test
    public void servicelistTest(){
        System.out.println("debug findAll >>> ");
        List<BoardResponse> responseList = service.listBoard();
        System.out.println("debug findAll result >>> ");
        System.out.println(responseList);
    }
    // @Test
    // public void serviceCountTest(){

    // }

}
