<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="display: flex; justify-content: space-between; align-items: center;">
  <jsp:useBean id="user" scope="session" type="com.javarush.ochirov.model.User"/>
  <c:if test="${not empty user.name}">
    <div>
      <p>Hello, ${user.name} (${user.role})!</p>
      <a href="/game">Start game</a>
    </div>
    <div>
      <form action="${pageContext.request.contextPath}/auth/signout" method="post">
        <button type="submit">Sign out</button>
      </form>
    </div>
  </c:if>

  <c:if test="${empty user.name}">
    <div>
      <a href="${pageContext.request.contextPath}/auth/signin">Sign in</a>
    </div>
  </c:if>
</div>