<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Book Management System</title>
    <%-- JSP에서 웹 경로를 사용할 땐 c:url을 사용하는 것이 안전합니다. --%>
    <link rel="stylesheet" href="/book/book-style.css">
    <script src="/book/book.js" defer></script>
</head>
<body>

    <div class="container">
        <h1>
            📚 도서 관리 시스템 (총 <span class="book-count">0</span>권)
        </h1>

        <div class="form-container">
            <h2>📖 새 도서 등록</h2>
            <form id="book-form">
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" id="title" placeholder="책 제목을 입력하세요." required>
                </div>
                <div class="form-group">
                    <label for="author">저자</label>
                    <input type="text" id="author" placeholder="저자 이름을 입력하세요." required>
                </div>
                <div class="form-group">
                    <label for="price">가격</label>
                    <input type="number" id="price" placeholder="가격을 입력하세요." required>
                </div>
                <button type="submit" class="add-btn">등록하기</button>
            </form>
        </div>

        <div class="list-container">
            <h2>📑 등록된 도서 목록</h2>
            <ul class="book-list">
                <%-- 자바스크립트를 통해 동적으로 책 목록이 채워질 공간입니다. --%>
            </ul>
        </div>

    </div>

</body>
</html>