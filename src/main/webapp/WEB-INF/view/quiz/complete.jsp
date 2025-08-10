<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Тест завершен</title>
  <style>
    .quiz-container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      text-align: center;
    }
    .complete-title {
      margin-bottom: 30px;
      font-size: 1.5em;
    }
    .btn-to-list {
      padding: 12px 20px;
      background-color: #4285f4;
      color: white;
      border: none;
      border-radius: 5px;
      text-align: center;
      text-decoration: none;
      font-size: 1em;
      cursor: pointer;
      transition: background-color 0.3s;
      display: inline-block;
    }
    .btn-to-list:hover {
      background-color: #3367d6;
    }
  </style>
</head>
<body>
<div class="quiz-container">
  <h1 class="complete-title">Тест успешно завершен!</h1>
  <a href="${pageContext.request.contextPath}/quiz/list" class="btn-to-list">К списку квизов</a>
</div>
</body>
</html>