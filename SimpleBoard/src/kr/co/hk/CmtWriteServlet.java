package kr.co.hk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.hk.BoardDAO;
import co.hk.CommentVO;

@WebServlet("/cmt")
public class CmtWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====cmtWrite [post]=====");
		
		request.setCharacterEncoding("UTF-8");
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String comment_content = request.getParameter("comment_content");
		
		System.out.println("board_no : " + board_no);
		System.out.println("comment_content : " + comment_content);
		
		CommentVO vo = new CommentVO();
		vo.setBoard_no(board_no);
		vo.setComment_content(comment_content);
		
		BoardDAO.insertComment(vo);
		
		response.sendRedirect("detail?board_no=" + board_no);
	}

}
