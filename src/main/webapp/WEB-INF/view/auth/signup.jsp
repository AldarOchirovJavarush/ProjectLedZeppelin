<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Регистрация</title>
    <style>
        .app-container {
            max-width: 500px;
            margin: 40px auto;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            font-family: 'Arial', sans-serif;
        }
        .app-title {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        .form-control {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        .btn {
            display: block;
            padding: 12px;
            text-align: center;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s;
            border: none;
            width: 100%;
        }
        .btn-success {
            background: #34a853;
            color: white;
        }
        .btn-success:hover {
            background: #2d9249;
        }
        .message {
            padding: 12px;
            margin: 20px 0;
            border-radius: 4px;
            text-align: center;
        }
        .error {
            background: #ffebee;
            color: #c62828;
        }
        .success {
            background: #e8f5e9;
            color: #2e7d32;
        }
        .text-center {
            text-align: center;
            margin-top: 20px;
        }
        .link {
            color: #4285f4;
            text-decoration: none;
        }
        .link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="app-container">
    <h1 class="app-title">Регистрация</h1>

    <c:if test="${not empty error}">
        <div class="message error">${error}</div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="message success">${success}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/auth/signup" method="post">
        <div class="form-group">
            <label>Логин:</label>
            <input type="text" name="username" class="form-control" required minlength="4">
        </div>

        <div class="form-group">
            <label>Пароль:</label>
            <input type="password" name="password" class="form-control" required minlength="6">
        </div>

        <button type="submit" class="btn btn-success">Зарегистрироваться</button>
    </form>

    <div class="text-center">
        Уже есть аккаунт? <a href="${pageContext.request.contextPath}/auth/signin" class="link">Войти</a>
    </div>
</div>
</body>
</html>