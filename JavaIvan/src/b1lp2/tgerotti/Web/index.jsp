<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/xhtml1-loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
	<title>Calculadora JSP</title>
</head>

<body>

	<form action="calcula.jsp" method="post">
		
		<p><h3>Insira dois números e selecione a operação!</h3></p>		
		<input type="text" name="numero1" value="" />
		<input type="text" name="numero2" value="" />
		<br>
		<br>
		
		<input type="radio" name="operacao" value="+" checked/>Soma	
		<input type="radio" name="operacao" value="-" />Subtração
		<input type="radio" name="operacao" value="/" />Divisão
		<input type="radio" name="operacao" value="*" />Multiplicação
		<br>
		<br>

		<input type="submit" value="Calcula">
	</form>

</body>
</html>