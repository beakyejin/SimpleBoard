package co.hk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

	public static int getMaxNo() {
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
		
		return maxNo;
	}

	public static void insertBoard(BoardVO vo) {
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
	}

	public static List<BoardVO> getList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select  "
						+ " board_no, board_title, to_char(regdate,'YYYY-MM-DD') as regdate "
						+ " , cnt "
					+" from s_board order by board_no desc";
		
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
				
				vo.setBoard_no(board_no);
				vo.setBoard_title(board_title);
				vo.setRegdate(regdate);
				vo.setCnt(cnt);
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("getList 에러!");
			e.printStackTrace();
		} finally{
			DBConn.close(con, ps, rs);
		}
		
		return list;
	}

}
