<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta charset="UTF-8">
<title>Criptografia de Júlio César</title>
</head>
<c:url value="/" var="contextPath"/>
<body>
	<h1>Criptografia de Júlio César</h1>
	
	<div>
		<a href="${contextPath }newAnswer">
			<input type="button" value="Novo">
		</a>
		<a href="${contextPath }loadAnswer">
			<input type="button" value="Carregar">
		</a>
		<a href="${contextPath }decodeAnswer">
			<input type="button" value="Decifrar">
		</a>
		<a href="${contextPath }encryptAnswer">
			<input type="button" value="Criptografar">
		</a>
	</div>
	<br>
	<form:form action="${contextPath }saveAnswer" method="POST"
	commandName="answer">
		<div>
			<label>Número de casas:</label>
			<form:input path="numero_casas" readonly="true"/>
		</div>
		<div>
			<label>Token:</label>
			<form:input path="token" readonly="true"/>
		</div>
		<div>
			<label>Cifrado:</label>
			<form:input path="cifrado" readonly="true"/>
		</div>
		<div>
			<label>Decifrado:</label>
			<form:input path="decifrado" readonly="true"/>
		</div>
		<div>
			<label>Resumo criptográfico:</label>
			<form:input path="resumo_criptografico" readonly="true"/>
		</div>
		<br>
		<input type="submit" value="Salvar">
	</form:form>
</body>
</html>