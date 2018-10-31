package kr.co.hk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.hk.BoardDAO;
import co.hk.CommentVO;

@WebServlet("/cmtDelete")
public class CmtDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====cmtDelete [get]=====");
		
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		System.out.println("comment_no: " + comment_no);
		System.out.println("board_no: " + board_no);
		
		CommentVO vo = new CommentVO();
		vo.setBoard_no(board_no);
		vo.setComment_no(comment_no);
		
		BoardDAO.deleteComment(vo);
		
		response.sendRedirect("detail?board_no="+board_no);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

