<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">

		<%-- 글쓰기 영역(로그인 된 사람만 보이게) --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 효과 --%>
					<input type="file" id="file" class="d-none "accept=".jpg, .png, .gif, .jpeg">
					
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					
					<%-- 업로드 된 임시 파일명 노출 --%>
					<div id="fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div> <%--// 글쓰기 영역 끝 --%>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		
		<div class="timeline-box my-5">
			<c:forEach items="${cardList}" var="card">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${card.user.loginId}</span>
					
					<a href="#" class="more-btn">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>	
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<a href="#" id="unfilledHeart" class="like-btn" data-post-id="${card.post.id}">
						<img src="/img/heart-icon.png" width="18" height="18" alt="unfilled heart">
					</a>
					
					<a href="#" id="filledHeart" class="like-btn" data-post-id="${card.post.id}">
						<img src="/img/filled-heart-icon.png" width="18" height="18" alt="filled heart">
					</a>
					좋아요 11개
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.user.loginId}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<c:forEach items="${card.commentList}" var="commentView">
					<%-- 댓글 내용들 --%>
					<div class="card-comment m-1">
						<span class="font-weight-bold">${commentView.user.loginId}</span>
						<span>${commentView.comment.content}</span>
						
						<%-- 댓글 삭제 버튼 --%>
						<%--로그인 된 사람과 댓글쓴이가 일치할 때 삭제버튼 노출 --%>
						<c:if test="${userId eq commentView.user.id}">
						<a href="#" class="comment-del-btn" data-comment-id="${commentView.comment.id}">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
						</a>
						</c:if>
					</div>
					</c:forEach>
					
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
					</div>
				</div> <%--// 댓글 목록 끝 --%>
			</div> <%--// 카드1 끝 --%>
			</c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>

<script>
$(document).ready(function() {
	// 파일이미지 클릭 => 숨겨져 있던 type="file"을 동작시킨다.
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault();  // a 태그의 올라가는 현상 방지
		$('#file').click();
	});
	
	// 이미지를 선택하는 순간 -> 유효성 확인 및 업로드 된 파일명 노출
	$('#file').on('change', function(e) {
		let fileName = e.target.files[0].name; // songbird-8348139_1280.png
		console.log(fileName);
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		//alert(ext);
		if (ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'jpeg') {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$('#file').val(""); // 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
			$("#fileName").text(""); // 파일명 비우기
			return;
		}
		
		//유효성을 통과한 이미지는 업로드 된 파일명을 노출시킨다
		$('#fileName').text(fileName);
	});
	
	// 글쓰기
	$('#writeBtn').on('click', function() {
		let content = $('#writeTextArea').val();
		console.log(content);
		if (content.length < 1) {
			alert("글 내용을 입력해주세요");
			return;
		}
		
		let file = $('#file').val();
		if (file == '') {
			alert('파일을 업로드 해주세요');
			return;
		}
		
		// 파일이 업로드 된 경우 확장자 체크
		//파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
		let ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
		if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
			alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
			$('#file').val(''); // 파일을 비운다.
			return;
		}
		
		// 폼태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]); 
		// $('#file')[0]은 첫번째 input file 태그를 의미, files[0]는 업로드된 첫번째 파일
		
		// AJAX form 데이터 전송
		$.ajax({
			type: "post"
			, url: "/post/create"
			, data: formData
			, enctype: "multipart/form-data"    // 파일 업로드를 위한 필수 설정
			, processData: false    // 파일 업로드를 위한 필수 설정
			, contentType: false    // 파일 업로드를 위한 필수 설정
			, success: function(data) {
				if (data.code == 200) {
					location.reload();
				} else if (data.code == 500) { // 비로그인 일 때
					location.href = "/user/sign-in-view";
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(e) {
				alert("글 저장에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});  // --- ajax 끝
	});
	
	// 댓글 쓰기
	$('.comment-btn').on('click', function() {
		//alert("aaaaa");
		let postId = $(this).data('post-id'); // data-post-id=13
		//alert(postId);
		
		// 댓글 내용 가져오기
		// 1)
		//let content = $(this).siblings("input").val().trim(); //siblings :형제들의 태그를 가져온다 
		
		// 2)
		let content = $(this).prev().val().trim();
		//alert(content);
		
		// ajax
		$.ajax({
			type:"post"
			, url:"/comment/create"
			, data:{"postId":postId, "content":content}
		
			, success: function(data) {
				if (data.code == 200) {
					alert("성공");
					location.reload(true);
				} else if (data.code == 500) {
					alert(data.errorMessage);
					location.href = "/user/sign-in-view";
				}
			}
			, error: function(request, status, error) {
				alert("댓글 쓰기 실패했습니다.");
			}
		});
	});
	
	//댓글 삭제
	$('.comment-del-btn').on('click', function(e) {
		//alert("댓글 삭제 클릭");
		e.preventDefault(); //a태그의 위로 올라가는 현상 방지
		
		let commentId = $(this).data("comment-id");
		//alert(commentId);
		
		
		$.ajax({
			//request
			type:"DELETE"
			, url:"/comment/delete"
			, data:{"commentId":commentId}
		
			//response
			, success: function(data) {
				if (data.code == 200) {
					location.reload(true);
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(request, status, error) {
				alert("댓글 삭제에 실패햇습니다");
			}
		});
		
	});
	

	//좋아요 해제
	$('.like-btn').on('click', function(e) {
		e.preventDefault();
		
		let postId = $(this).data("post-id");
		
		$.ajax({
			//request
			type:"get" //get은 생략가능
			, url:"/like/" + postId
			//data를 추가할 필요 없음
			
			//response
			, success:function(data) {
				if (data.code == 200) {
					location.reload(true); //새로고침 -> timeline다시 가져옴 -> 하트 채워지거나 비워지거나
				} else if (data.code == 500) { //비로그인 상태
					alert("로그인을 해주세요");
				}
			}
		
			, error:function(request, status, error) {
				alert("좋아요 누르기에 실패했습니다");
			}
		});
	});
});
</script>