package co.hk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	public static int getMaxNo() {
		System.out.println("=====getMaxNo=====");
		
		int maxNo = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select nvl(max(board_no),100000) + 1  as no"
					+" from s_board ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				maxNo = rs.getInt("no");
				
				System.out.println("maxNo: " +maxNo);
			}
			
		} catch (Exception e) {
			System.out.println("getMaxNo 에러!");
			e.printStackTrace();
		} finally{
			DBConn.close(con, ps, rs);
		}

		System.out.println("============================");
		
		return maxNo;
	}

	public static void insertBoard(BoardVO vo) {
		System.out.println("=====insertBoard=====");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into s_board"
					+" (board_no, board_title, board_content, regdate, cnt) "
					+" values "
					+" (?, ?, ?, ?, ?) ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getBoard_no());
			ps.setString(2, vo.getBoard_title());
			ps.setString(3, vo.getBoard_content());
			ps.setString(4, vo.getRegdate());
			ps.setInt(5, vo.getCnt());
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("insertBoard 에러!");
			e.printStackTrace();
		} finally{
			DBConn.close(con, ps, null);
		}
		
		System.out.println("============================");
	}

	public static List<BoardVO> getList() {
		System.out.println("=====getList=====");
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		/*String sql = " select  "
						+ " board_no, board_title"
						+ " , to_char(regdate,'YYYY-MM-DD') as regdate "
						+ " , cnt "
					+" from s_board order by board_no desc";*/
		
		String sql = " select "
					+"      a.board_no as board_no "
					+"    , a.board_title as board_title "
					+"    , to_char(a.regdate,'yyyy-mm-dd') as regdate "
					+"    , a.cnt as cnt "
					+"    , count(b.comment_no) as comment_cnt "
					+" from s_board a "
					+" left join s_comment b "
					+" on a.board_no = b.board_no "
					+" group by a.board_no, a.board_title, a.regdate, a.cnt "
					+" order by board_no desc ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BoardVO vo = new BoardVO();
				
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String regdate = rs.getString("regdate");
				int cnt =  rs.getInt("cnt");
				int comment_cnt = rs.getInt("comment_cnt");
				
				vo.setBoard_no(board_no);
				vo.setBoard_title(board_title);
				vo.setRegdate(regdate);
				vo.setCnt(cnt);
				vo.setComment_cnt(comment_cnt);
				
				list.add(vo);
				
				System.out.printf("%d %s %s %d %d\n",
						board_no, board_title, regdate, cnt, comment_cnt);
			}
			
		} catch (Exception e) {
			System.out.println("getList 에러!");
			e.printStackTrace();
		} finally{
			DBConn.close(con, ps, rs);
		}
		
		System.out.println("============================");
		
		return list;
	}

	public static void updateCNT(int board_no) {
		System.out.println("=====updateCNT=====");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update s_board "
					+" set cnt= cnt+1 "
					+" where board_no = ?  ";
		
		try {
			con =DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("updateCNT 에러!");
			e.printStackTrace();
		} finally{
			DBConn.close(con, ps, null);
		}
		
		System.out.println("============================");
	}

	public static BoardVO getBoardDetail(int board_no) {
		System.out.println("=====getBoardDetail=====");
		
		BoardVO vo =new BoardVO();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select board_no, board_title"
				+ " , board_content "
				+ " , to_char(regdate,'YYYY-MM-DD') as regdate "
				+ " , cnt "
				+ " from s_board "
				+ " where board_no = ? ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				String date = rs.getString("regdate");
				int cnt = rs.getInt("cnt");
				
				vo.setBoard_no(no);
				vo.setBoard_title(title);
				vo.setBoard_content(content);
				vo.setRegdate(date);
				vo.setCnt(cnt);
				
				System.out.printf("%d %s %s %s %d\n"
						, board_no, title, content, date, cnt);
			}
			
		} catch (Exception e) {
			System.out.println("getBoardDetail 에러!");
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		System.out.println("============================");
		
		return vo;
	}

	public static void insertComment(CommentVO vo) {
		System.out.println("=====insertComment=====");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into s_comment "
				+ " (board_no, comment_no, comment_content, regdate) "
				+ " values "
				+ " (? "
				+ " , (select nvl(max(comment_no),0)+1 "
						+ " from s_comment where board_no = ? ) "
				+ ", ?, sysdate ) ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getBoard_no());
			ps.setInt(2, vo.getBoard_no());
			ps.setString(3, vo.getComment_content());
			
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("insertComment 에러!");
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
		
		System.out.println("============================");
	}

	public static List<CommentVO> getCommentList(int board_no) {
		System.out.println("=====insertComment=====");
		
		List<CommentVO> list = new ArrayList<CommentVO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select "
						+ "   board_no "
						+ " , comment_no "
						+ " , comment_content "
						+ " , to_char(regdate , 'YYYY-MM-DD') as regdate " 
					+ " from s_comment "
					+ " where board_no = ? " 
					+ " order by comment_no desc";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			rs = ps.executeQuery();
			
			while(rs.next()){
				int board_num = rs.getInt("board_no");
				int comment_no = rs.getInt("comment_no");
				String comment_content = rs.getString("comment_content");
				String regdate = rs.getString("regdate");
				
				CommentVO vo = new CommentVO();
				vo.setBoard_no(board_num);
				vo.setComment_no(comment_no);
				vo.setComment_content(comment_content);
				vo.setRegdate(regdate);
				
				list.add(vo);
				
				System.out.printf("%d %d %s %s \n"
						, board_num, comment_no, comment_content, regdate);
			}
			
		} catch (Exception e) {
			System.out.println("getCommentList 에러!");
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		System.out.println("============================");
		return list;
	}

	public static void deleteComment(CommentVO vo) {
		System.out.println("=====deleteComment=====");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " delete from s_comment  "
					+" where comment_no= ? and board_no = ? ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getComment_no());
			ps.setInt(2, vo.getBoard_no());
			
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("deleteComment 에러");
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
		
		System.out.println("============================");
	}

	public static void deleteBoard(int board_no) {
		System.out.println("=====deleteBoard=====");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " delete from s_board "
					+" where board_no=? ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("deleteBoard 에러!");
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
		System.out.println("============================");
		
	}

	public static void updateBoard(BoardVO vo) {
		System.out.println("=====updateBoard=====");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update s_board "
					+" set 	 board_no = ? "
					+"		,board_title = ? "
					+"		,board_content = ? "
					+"		,regdate = ? "
					+" where board_no = ? ";
		
		try {
			con = DBConn.getConnection();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getBoard_no());
			ps.setString(2, vo.getBoard_title());
			ps.setString(3, vo.getBoard_content());
			ps.setString(4, vo.getRegdate());
			ps.setInt(5, vo.getBoard_no());
			
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("updateBoard 에러!");
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
		
		System.out.println("============================");
	}

}
