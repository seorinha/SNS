<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%--업로드 박스 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			<%--이미지 업로드 아이콘과 업로드 버튼을 한행에서 멀리 떨어뜨리기 위한 div --%>
			
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<a href="#" id="fileUploadBtn"><img src="img/" width="35"></a>
				</div>
				<button id="writeBtn" class="btn btn-primary">업로드</button>				
			</div>	
		</div>
	
	<%--글 --%>
	<div>
		<%--상단 user 이름 --%>
		<div class="bg-secondary">
			<span class="font-weight-bold">${}</span>
			<img src="img/more-icon.png" width="" height="">
		</div>
		
		<%--업로드 이미지 --%>
		<div class="img-box">
			<img src="https://cdn.pixabay.com/photo/2017/04/09/09/56/avenue-2215317_1280.jpg" alt="업로드 이미지" width="" height="">
		</div>
		<%-- 좋아요--%>
		<div class="like">
			<img src="img/heart-icon.png">
			<img src="img/heart-icon (1).png">
			<span>좋아요 ${}개</span>
		</div>
		<%--post영역--%>
		<div class="post-box">
			
		</div>
		<%--댓글 영역 --%>
		<div class="bg-secondary font-weight-bold">
			댓글
		</div>
		<div class="comment-write d-flex">
			
		</div>
	</div>
	
	</div>
</div>