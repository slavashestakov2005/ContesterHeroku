<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	try{
		String pwd = request.getParameter("password");
		if (pwd != null) {
			Cookie password = new Cookie("password", pwd);
			password.setMaxAge(60 * 60 * 10);
			// Add both the cookies in the response header.
			response.addCookie(password);
		}
	}catch(Exception e){}
%>

<html lang="ru">
	<head>
		<meta charset="UTF-8">
		<link rel="shortcut icon" href="../../Images/image.ico" type="image/x-icon">
		<link rel="stylesheet" href="../../CSS/users.css">
		<link rel="stylesheet" href="../../CSS/admin.css">
		<script src="../../JS/users.js" type="text/javascript"> </script>
		<script src="../../JS/admin.js" type="text/javascript"> </script>
		<script>
			var cnt = -1;
			var page_type = "contest";
			var page_number = 1;
			var page_contest = 1;
			Check(document, page_contest);
		</script>
		<script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
		<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
		<title>Пример</title>
	</head>
	<body>
		<div id="header">
			<div id="nav1"><center><button id="image" onclick="Edit(document, page_type, page_number); "><img src="../../Images/edit.png"></button></center></div>
			<div id="nav2">
				<p align="right">
					<script>printNameSurname(document);</script>
					<br/>
					<a href="edit.html">Редактировать</a>					<a href="../../index.html">Выйти</a>
				</p>
			</div>
			<div id="nav3"><center><h1><a href="contest.jsp">Пример</a></h1></center></div>
		</div>
		<div id="page">
			<iframe src="sidebar.html" width="150px" height="100%" scrolling="no" frameborder="no" style="position: absolute;">Список задач</iframe>"
			<div id="content">
				<center><h2>Пример</h2></center>
				<h3>Описание:</h3>
				<p>Здесь располагается описание контеста.</p>
				<h3>Время старта:</h3>
				<p>10.03.2021 10:00</p>
				<h3>Время окончания:</h3>
				<p>31.05.2021 10:00</p>
				<h3>Продолжительность:</h3>
				<p>82:00:00</p>
				<h3>Результаты:</h3>
				<p><a href="#" onclick="viewResults(1, 1);">Результаты</a></p>
				<div id="down2"></div>
			</div>
		</div>
	</body>
</html>