<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function clkSubmit() {
		var frm = document.frm;
		
		if(frm.board_no.value == ""){
			alert("번호를 입력해주세요.");
			frm.board_no.focus();
			return false;
		}else if(frm.board_no.value.length != 6){
			//자리수 체크 
			alert("게시글 번호가 6자리가 아닙니다.");
			frm.board_no.focus();
			return false;
		}else if(frm.board_title.value == ""){
			alert("제목을 입력해주세요.");
			frm.board_title.focus();
			return false;
		}else if(frm.board_title.value.length >= 10){
			alert("10자 이하로 입력해주세요.");
			frm.board_title.focus();
			return false;
		}else if(frm.board_content.value == ""){
			alert("내용을 입력해주세요.");
			frm.board_content.focus();
			return false;
		}else if(frm.regdate.value == ""){
			alert("날짜를 입력해주세요.");
			frm.regdate.focus();
			return false;
		}
		return true;
	}
	
	function clkFind() {
		location.href="list";
	}
	
</script>
<section>
	<form name="frm" action="mod" method="post" onsubmit="return clkSubmit();">
		<div class="tbl_write">
			<table>
				<tr>
					<th>번호</th>
					<td>
						<input type="text" name="board_no" value="${vo.board_no}" readonly>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="board_title" value="${vo.board_title}">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="board_content">${vo.board_content}</textarea>
					</td>
				</tr>
				<tr>
					<th>등록 날짜</th>
					<td>
						<input type="date" name="regdate" value="${vo.regdate}">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="tbl_btn">
							<input type="submit" value="수정">
							<input type="button" value="조회" onclick="clkFind();">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</section>