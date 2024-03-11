package com.example.encore_spring_pjt.ctrl.board;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.encore_spring_pjt.domain.BoardRequest;
import com.example.encore_spring_pjt.domain.BoardResponse;
import com.example.encore_spring_pjt.service.BoardService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/board") // -------> http:// serverip : port / board
//@RequiredArgsConstructor -> 지금 상황에서 이걸쓰게되면 모호성이 발생되어 부적절
public class BoardController {
    
    @Resource(name = "board")
    private  BoardService boardService;

    @RequestMapping("/list.hanwha") // http:// serverip : port / board/list.hanwha
    //@ResponseBody
    public String list(Model model){
        System.out.println("debug BoardController client path /board/list.hanwha");

        /* 
        BoardServiceImpl listBoard() 메서드 호출하여 결과를 반환받고 
        반환받은 결과를 Model(requestScope)에 심고 이 데이터를 
        forward되는 페이지에서 출력.
        */ 

        List<BoardResponse> lst = boardService.listBoard();
        for(BoardResponse response : lst){
            System.out.println(response);
        }

        model.addAttribute("lst", lst);

        return "list";
    }

    //@GetMapping("/view.hanwha/{idx}")
    //public String view(@PathVariable("idx")Integer idx){
    @GetMapping("/view.hanwha")
    public String view(BoardRequest params, Model model){
        System.out.println("debug BoardController client path /board/view.hanwha");
        System.out.println("path value : " + params.getIdx());

        //view.jsp로부터 키(idx)값을 전달 받고 객체로 바인딩되서 service에 전달
        //response 객체를 반환받고 해당 response 객체 Model에 심어서 View 페이지로 전달 과정

        Optional<BoardResponse> response = boardService.findBoard(params);

        model.addAttribute("response", response);

        return "view";
    }
    @GetMapping("/write.hanwha")
    public String writeForm(BoardRequest params, Model model) {
        System.out.println("debug BoardController client path GET /board/write.hanwha");
        System.out.println("debug >>> " + params);
        if(params.getIdx() != null){
            System.out.println("debug >>> update");
            Optional<BoardResponse> response = boardService.findBoard(params);
            model.addAttribute("response", response);
        }
        return "write";
    }
    @PostMapping("/write.hanwha")
    public String write(BoardRequest params) {
        System.out.println("debug BoardController client path /board/write.hanwha");
        System.out.println("debug >>> params value " + params);
        params.setSecretYn(false);
        Integer idx = boardService.saveBoard(params);
        System.out.println("debug result >> " + idx + "번 게시글 입력" );
    
        return "redirect:/board/list.hanwha";
    }
    @GetMapping("/delete.hanwha")
    public String delete(BoardRequest params) {
        System.out.println("debug BoardController client path /board/delete.hanwha");
        System.out.println("debug >>> params value " + params);
        
        params.setSecretYn(false);
        boardService.deleteBoard(params);
        return "redirect:/board/list.hanwha";
    }
    @PostMapping("/update.hanwha")
    public String update(BoardRequest params) {
        System.out.println("debug BoardController client path /board/update.hanwha");
        System.out.println("debug >>> params value " + params);
        Integer idx = boardService.updateBoard(params);
        System.out.println("debug result >> " + idx + "번 게시글 수정" );

        return "redirect:/board/list.hanwha";
    }
}