<%@page import="co.hk.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<BoardVO> list = (List<BoardVO>)request.getAttribute("list"); %>
<script type="text/javascript">
	function clkTr(board_no) {
		var form = document.createElement("form");
		
		form.method = "POST";
		form.action = "detail";
		
		var element1 = document.createElement("input");
		element1.value = board_no;
		element1.name = "no";
		
		form.appendChild(element1);
		document.body.appendChild(form);
		form.submit();
	}
</script>
<section>
	<div class="tbl_list">
		<table>
			<colgroup>
				<col class="col1"  width=8%> 
				<col class="col2" width=65%>
				<col class="col3" width=10%>
				<col class="col4" width=8%>
			</colgroup>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성날짜</th>
				<th>조회수</th>
			</tr>
			<%if(list != null && list.size() > 0){ 
			for(BoardVO vo : list) {%>
			<tr>
				<td><%=vo.getBoard_no() %></td>
				<%-- <td><a href="detail?no=<%=vo.getBoard_no()%>">
						<%=vo.getBoard_title() %></a></td> --%>
				<td onclick="clkTr(<%=vo.getBoard_no()%>)">
						<%=vo.getBoard_title() %></td>
				<td><%=vo.getRegdate() %></td>
				<td><%=vo.getCnt() %></td>
			</tr>
			<%}
			}%>
		</table>
	</div>
</section>