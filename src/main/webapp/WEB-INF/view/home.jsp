<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
  <c:when test="${not empty user.name}">
    <p>Hello, ${user.name} (${user.role})!</p>
    <a href="/game">Start game</a>
  </c:when>
  <c:otherwise>
    <form action="/home" method="post">
      <input type="text" name="playerName" required>
      <button type="submit">Save</button>
    </form>
  </c:otherwise>
</c:choose>