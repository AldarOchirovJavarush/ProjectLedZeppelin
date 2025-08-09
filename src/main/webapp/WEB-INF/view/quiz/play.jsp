<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>${question.text}</title>
  <style>
    .quiz-container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }
    .question-text {
      margin-bottom: 30px;
      font-size: 1.5em;
      text-align: center;
    }
    .answers-column {
      display: flex;
      flex-direction: column;
      gap: 10px;
    }
    .answer-btn {
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
    .answer-btn:hover {
      background-color: #3367d6;
    }
  </style>
</head>
<body>
<div class="quiz-container">
  <h1 class="question-text">${question.text}</h1>

  <div class="answers-column">
    <c:forEach items="${question.answers}" var="answer">
      <a href="<c:url value='/quiz/play?id=${quizId}&q=${answer.nextId}'/>"
         class="answer-btn">
          ${answer.text}
      </a>
    </c:forEach>
  </div>
</div>
</body>
</html>