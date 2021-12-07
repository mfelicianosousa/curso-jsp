<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erro</title>
</head>
<body>
  <h1> Mensagem de erro, entre em contato com a equipe de suporte do Sistema</h1>
  <%
      System.out.println( request.getAttribute("msg"));
   %>
</body>
</html>