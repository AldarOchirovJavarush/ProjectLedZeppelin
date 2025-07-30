<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 400px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        .error { color: red; }
        .success { color: green; }
        input { width: 100%; padding: 8px; box-sizing: border-box; }
        button { background: #4CAF50; color: white; padding: 10px; border: none; width: 100%; }
    </style>
</head>
<body>
<div class="container">
    <h2>Create Account</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/auth/signup" method="post">
        <div class="form-group">
            <label>Username:</label>
            <input type="text" name="username" required minlength="4">
        </div>

        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" required minlength="6">
        </div>

        <button type="submit">Sign Up</button>
    </form>

    <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/signin">Sign In</a></p>
</div>
</body>
</html>