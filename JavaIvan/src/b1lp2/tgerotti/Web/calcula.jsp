<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/xhtml1-loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
 
<html>  
<head><title>Calcula JSP</title></head>  
<body>  
<h2>
	<% 	
	String nu1 = request.getParameter("numero1");
	String nu2 = request.getParameter("numero2");
	String oper = request.getParameter("operacao");
	
	try{
			int n1 = Integer.parseInt(nu1);
			int n2 = Integer.parseInt(nu2);
			out.println("O resultado da operação é: " + calcula(n1,n2,oper));
		}catch(NumberFormatException e){
			out.println("Entre apenas com numeros válidos!");	
		}
	
	
	%>
</h2>
	<p><a href="index.jsp">Home</a>	
</body>  
</html>
	<%!
	String calcula(int n1, int n2, String oper){
		int re = 0;
		if(oper.equals("/")){
			
			if (n1 == 0 || n2 == 0){
				if (oper.equals("/")){
					return "Não é possivel divisão por 0 !";					
				}
			}else{
							
				if ( (n1 % n2) != 0){
					float res = (float)  n1 / n2;
					DecimalFormat aprox = new DecimalFormat( " 0.00 " );
			
					return aprox.format(res);
				}
				
				return Integer.toString(n1 / n2);
			}
		}else{
			if(oper.equals("*")){
				re = n1 * n2;
			}
			if(oper.equals("+")){
				re = n1 + n2;
			}
			if(oper.equals("-")){
				re = n1 - n2;
			}
		}	
		return Integer.toString(re);
	}
	%>