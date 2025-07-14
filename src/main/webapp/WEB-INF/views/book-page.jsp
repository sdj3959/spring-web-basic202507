<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web Study</title>

    <script src="/book.js" defer></script>
</head>
<body>

    <h1>여기는 책 페이지입니다. (현재 총 책의 수: <span class="book-count">0</span>권)</h1>

    <form>
<%--
    제목, 저자, 가격을 입력할 수 있게 해주고 등록 버튼을 누르면 POST요청 나가서
    목록에 갱신되게 해주세요~~~
--%>
    </form>

    <ul class="book-list"></ul>


</body>
</html>