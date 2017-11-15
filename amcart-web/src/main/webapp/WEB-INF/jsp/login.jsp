<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>

<template:page pageTitle="${pageTitle}">
	<div class="container login-register">
		<div class="row">
			<user:login />
		</div>
	</div>

</template:page>
