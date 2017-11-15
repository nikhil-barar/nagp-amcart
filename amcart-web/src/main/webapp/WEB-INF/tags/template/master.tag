<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/style.css">

<title>${not empty pageTitle ? pageTitle : 'Amcart'}</title>
</head>
<body>
	<%-- Inject the page body here --%>
	<jsp:doBody/>
</body>