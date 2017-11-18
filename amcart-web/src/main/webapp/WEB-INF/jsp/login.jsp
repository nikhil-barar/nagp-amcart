<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/user"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')">
    <% response.sendRedirect("/"); %>
</sec:authorize>

<template:page pageTitle="${pageTitle}">
	<div class="container login-register">
		<div class="row">
			<user:socialLogin />
			<user:login />
			<user:register />
		</div>
	</div>

</template:page>
