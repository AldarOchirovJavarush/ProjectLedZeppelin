<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Available Quizzes</title>
  <style>
    .quiz-container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }
    .page-title {
      margin-bottom: 30px;
      font-size: 1.5em;
      text-align: center;
    }
    .quiz-list {
      display: flex;
      flex-direction: column;
      gap: 10px;
      margin-bottom: 20px;
    }
    .quiz-btn {
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
    .quiz-btn:hover {
      background-color: #3367d6;
    }
    .upload-link {
      display: block;
      padding: 12px 20px;
      background-color: #34a853;
      color: white;
      border-radius: 5px;
      text-align: center;
      text-decoration: none;
      margin-top: 20px;
    }
    .upload-link:hover {
      background-color: #2d9249;
    }
  </style>
</head>
<body>
<div class="quiz-container">
  <h1 class="page-title">Choose a Quiz</h1>

  <div class="quiz-list">
    <c:forEach items="${quizzes}" var="quiz">
      <a href="<c:url value='/quiz/play?id=${quiz.id}'/>" class="quiz-btn">
          ${quiz.title}
      </a>
    </c:forEach>
  </div>

  <a href="<c:url value='/quiz/upload'/>" class="upload-link">Upload New Quiz</a>
</div>
</body>
</html>