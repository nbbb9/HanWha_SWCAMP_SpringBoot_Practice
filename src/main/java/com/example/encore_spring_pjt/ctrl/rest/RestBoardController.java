package com.example.encore_spring_pjt.ctrl.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("/board_rest")
public class RestBoardController {
    //BoardServiceImpl에 대한 DI
    @Resource(name = "board")
    //BoardService를 상속받는건 Board와 Post가 있다.
    //name =" "안에 어떤것을 넣느냐에 따라 달라짐
    private BoardService service;
    //전체 조회
    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BoardResponse>> list() {
        System.out.println("debug RestBoardController client path /board_rest/list");
        List<BoardResponse> list = service.listBoard();
        return new ResponseEntity<List<BoardResponse>>(list, HttpStatus.OK);
    }
    //게시글 삭제 - PathVariable
    @DeleteMapping(value = "/delete/{idx}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> delete(@PathVariable("idx") Integer idx){
        System.out.println("debug RestBoardController client path /board_rest/idx");
        BoardRequest request = BoardRequest.builder()
                                            .idx(idx)
                                            .build();
        Integer result = service.deleteBoard(request);
        System.out.println("debug >>> " + result + "번 게시글 삭제 완료");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    //게시물 번호로 조회
    @GetMapping(value = "/view/{idx}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<BoardResponse>> view(@PathVariable("idx") Integer idx){
        System.out.println("debug RestBoardController client path /board_rest/list");
        BoardRequest request = BoardRequest.builder()
                                            .idx(idx)
                                            .build();
        Optional<BoardResponse> response = service.findBoard(request);
        System.out.println("debug >>> isPresent() " + response.isPresent());
        System.out.println("debug >>> view result , " + response);
        return new ResponseEntity <Optional<BoardResponse>>(response, HttpStatus.OK);
        //return null;
    }
    //게시글 번호로 수정 방법1 _ pathvalue방식(객체화 불가능)
    @PutMapping(value = "/update/{idx}/{title}/{content}/{writer}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update(@PathVariable("idx")     Integer idx,
                                       @PathVariable("title")   String title,
                                       @PathVariable("content") String content,
                                       @PathVariable("writer")  String writer) {
        System.out.println("debug RestBoardController client path /board_rest/update");
        BoardRequest request = BoardRequest .builder()
                                            .idx(idx)
                                            .title(title)
                                            .content(content)
                                            .writer(writer)
                                            .build();
        Integer result = service.updateBoard(request);
        System.out.println("debug >>> " + result + "번 게시글 수정 완료");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    //게시글 번호로 수정 방법2 _ 객체방식으로 데이터 입력
    @PutMapping(value = "/update2", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> update2(BoardRequest params) {
        System.out.println("debug RestBoardController client path /board_rest/update2");
        System.out.println("debug >>> params , " + params);
        Integer result = service.updateBoard(params);
        System.out.println("debug >>> " + result + "번 게시글 수정 완료");
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    //입력
    @PostMapping(value = "/write" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> write(BoardRequest params){
        System.out.println("debug >>> RestBoardController client path /board_rest/write");
        System.out.println("params >>>>>>" + params);
        Integer result = service.saveBoard(params);
        Map<String, String> map = new HashMap<>();
        map.put("msg", result + "번 게시글 입력 완료") ; 

        return new ResponseEntity<Map<String, String>>(HttpStatus.OK);
    }

}