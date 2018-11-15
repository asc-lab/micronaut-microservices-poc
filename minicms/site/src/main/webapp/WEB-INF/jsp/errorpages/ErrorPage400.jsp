<!doctype html>
<%@ include file="/WEB-INF/jspf/htmlTags.jspf" %>
<%@ page isErrorPage="true" %>
<% response.setStatus(400); %>

<html lang="en">
<head>
  <meta charset="utf-8"/>
  <title>400 error</title>
</head>
<body>
<h1>Bad request!!!</h1>
<p>The request cannot be fulfilled due to bad syntax.</p>
</body>
</html>