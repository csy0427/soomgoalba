package com.mycompany.myapp06.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myapp06.board.model.service.BoardService;
import com.mycompany.myapp06.board.model.service.BoardServiceImpl;
import com.mycompany.myapp06.board.model.vo.Board;
import com.mycompany.myapp06.member.model.vo.Member;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;    // List = Arraylist 처럼 BoardService 에 Impl 클래스 넣는 형태로 이렇게 작성함.
	
	@RequestMapping(value = "/boardlist", method = RequestMethod.GET)
	public ModelAndView getBoardList(ModelAndView mv) throws Exception{
//		logger.debug();
//		logger.info();
//		logger.error();
	    String viewpage = "";
		Board vo = new Board();
		vo.setBoardNum(111);
		vo.setBoardTitle("aaaa");
		List<Board> volist = new ArrayList();
		logger.debug("debug모드");
		logger.info("info모드");
		try {
			volist = boardService.getBoardList(vo);
			viewpage = "board/boardlist";
			mv.addObject("volist", volist);
		} catch (Exception e) {
			viewpage = "error/commonError";
			mv.addObject("msg", "게시글 읽기에 문제가 발생하였습니다. 페이지를 다시 열어주세요.");
			mv.addObject("url", "boardlist");
			e.printStackTrace();
		}
		if(volist.size()==0) {
			throw new Exception("volist is null");
		}
		mv.setViewName(viewpage);
		return mv;
	}
	@RequestMapping(value = "/boardlistPagin", method = RequestMethod.GET)
	public String getBoardListPaging(Model m){
		System.out.println("Controller BoardController getBoardListPaging");
		return "board/boardlist";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Model m){

		return "board/";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model m){

		return "board/";
	}
	
	
	
	
	@RequestMapping(value = "/boardwrite", method = RequestMethod.GET)
	public ModelAndView write(ModelAndView mv){
		mv.setViewName("board/boardwrite");
		return mv;
	}
	
	
	@RequestMapping(value = "/boardwrite", method = RequestMethod.POST)
	public ModelAndView doWrite(ModelAndView mv, Board bvo, Member mvo){
//		Board vo = new Board();
//		vo.setBoardTitle(req.getParameter("boardTitle"));
//		vo.setBoardContent(req.getParameter("boardContent"));
//		System.out.println("req vo: " + vo);
		System.out.println("bvo: " + bvo);

		try {
			boardService.insertBoard(bvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@ExceptionHandler
	public String boardControllerExceptionHandler(Exception ex,Model model) {
		System.out.println("exception~");
		System.out.println(ex.getMessage());
		logger.error(ex.getMessage());
		return "home";
	}
}

