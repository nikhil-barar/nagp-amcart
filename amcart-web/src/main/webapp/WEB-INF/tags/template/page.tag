<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/footer"%>

<template:master pageTitle="${pageTitle}">
	<jsp:body>
		<header:header />
		<div class="pg-body">
			<jsp:doBody />
		</div>
		<footer:footer />
	</jsp:body>
</template:master>