<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Главная</title>
  <style>
    .home-container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }
    .greeting {
      margin-bottom: 30px;
      font-size: 1.5em;
      text-align: center;
    }
    .action-panel {
      display: flex;
      flex-direction: column;
      gap: 10px;
      margin-bottom: 20px;
    }
    .action-btn {
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
    }
    .action-btn:hover {
      background-color: #3367d6;
    }
    .logout-form {
      margin-top: 20px;
    }
    .logout-btn {
      width: 100%;
      padding: 12px 20px;
      background-color: #ea4335;
      color: white;
      border: none;
      border-radius: 5px;
      text-align: center;
      font-size: 1em;
      cursor: pointer;
      transition: background-color 0.3s;
    }
    .logout-btn:hover {
      background-color: #d33426;
    }
  </style>
</head>
<body>
<div class="home-container">
  <c:if test="${not empty user.name}">
    <h1 class="greeting">Добро пожаловать, ${user.name} (${user.role})!</h1>

    <div class="action-panel">
      <a href="/quiz/list" class="action-btn">Список квизов</a>
    </div>

    <form action="${pageContext.request.contextPath}/auth/signout" method="post" class="logout-form">
      <button type="submit" class="logout-btn">Выйти</button>
    </form>
  </c:if>

  <c:if test="${empty user.name}">
    <h1 class="greeting">Добро пожаловать!</h1>
    <a href="${pageContext.request.contextPath}/auth/signin" class="action-btn">Войти</a>
  </c:if>
</div>
</body>
</html>