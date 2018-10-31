package kr.co.hk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.hk.BoardDAO;
import co.hk.BoardVO;
import co.hk.CommentVO;
import co.hk.Utils;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====detail [get]=====");
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		System.out.println("board_no" + board_no);
		
		//데이터 들고오기
		BoardVO vo = BoardDAO.getBoardDetail(board_no);
		request.setAttribute("vo", vo);
		
		List<CommentVO> cmtList = BoardDAO.getCommentList(board_no);
		request.setAttribute("cmtList", cmtList);
		
		Utils.dispatcher("detail", "detail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====detail [post]=====");
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		//조회수 +1
		BoardDAO.updateCNT(board_no);
		
		response.sendRedirect("detail?board_no=" + board_no);
	}

}
