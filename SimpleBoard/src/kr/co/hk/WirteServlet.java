package kr.co.hk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.hk.BoardDAO;
import co.hk.BoardVO;
import co.hk.Utils;

@WebServlet("/write")
public class WirteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====write [get]=====");
		
		int maxNo = BoardDAO.getMaxNo();
		
		request.setAttribute("maxNo", maxNo);
		Utils.dispatcher("write", "write", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====write [post]=====");
		
		request.setCharacterEncoding("UTF-8");
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		String regdate = request.getParameter("regdate");
		
		System.out.printf("%d %s %s %s\n", board_no, board_title, board_content, regdate);
		
		BoardVO vo = new BoardVO();
		vo.setBoard_no(board_no);
		vo.setBoard_title(board_title);
		vo.setBoard_content(board_content);
		vo.setRegdate(regdate);
		vo.setCnt(0);
		
		BoardDAO.insertBoard(vo);
		
		response.sendRedirect("list");
	}

}
