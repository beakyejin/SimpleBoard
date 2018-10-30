<%@page import="co.hk.CommentVO"%>
<%@page import="co.hk.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<CommentVO> cmtList = (List<CommentVO>)request.getAttribute("cmtList"); %>
<script type="text/javascript">
	function clkSubmit() {
		var frm = document.frm;
		
		if(frm.comment_content.value == ""){
			alert("댓글을 작성해 주세요!");
			frm.comment_content.focus();
			return false;
		}
		return true;
	}
	
	function delCmt(comment_no, board_no) {
		location.href="cmtDelete?comment_no="+comment_no+"&board_no="+board_no;
	}
</script>
<section>
	<div class="tbl_detail">
		<%if("${vo}" != null){ %>
		<table>
			<colgroup>
				<col>
				<col width="45%">
				<col>
				<col>
				<col>
				<col>
			</colgroup>
			<tr>
				<th>제목</th>
				<td>${vo.board_title}</td>
				<th>작성날짜</th>
				<td>${vo.regdate}</td>
				<th>조회수</th>
				<td>${vo.cnt}</td>
			</tr>
			<tr>
				<td colspan="6">
					${vo.board_content}
				</td>
			</tr>
		</table>
		<%} %>
		<div class="btn">
			<input type="button" value="목록" onclick="location.href='list'">
			<input type="button" value="수정" onclick="location.href='mod'">
			<input type="button" value="삭제" onclick="location.href='delete'">
		</div>
	</div>
	<div class="cmt_div">
		<div class="cmt_text">
			<form name="frm" action="cmt" onsubmit="return clkSubmit();" method="post">
				<input type="hidden" name="board_no" value="${vo.board_no}">
				<textarea name="comment_content"></textarea>
				<input type="submit" value="댓글 작성">
			</form>
		</div>
		<div class="cmt_list">
			<table>
				<colgroup>
					<col>
					<col width="60%">
					<col>
					<col>
				</colgroup>
			<% if(cmtList != null && cmtList.size() >0){
				for(CommentVO vo : cmtList) {%>
				<tr>
					<td><%=vo.getComment_no() %></td>
					<td><%=vo.getComment_content() %></td>
					<td><%=vo.getRegdate() %></td>
					<td><input type="button" value="삭제" 
							onclick="delCmt(<%=vo.getComment_no() %>, <%=vo.getBoard_no()%>);">
					</td>
				</tr>
			<%}
			}%>
			</table> 
		</div>
	</div>
</section>