<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web Study</title>
</head>
<body>
  <h1>애완동물 페이지입니다.</h1>
  <%--  애완동물 목록을 서버 사이드 렌더링 --%>
    <ul>
<%--        for ( : ) --%>
        <c:forEach var="pet" items="${petList}">
            <li>이름 : ${pet.name}, 종 : ${pet.kind},
                나이 : ${pet.age}살, 접종여부 : ${pet.injection}
            </li>
        </c:forEach>
    </ul>
</body>
</html>