<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
			var page_type = "solution";
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
					<a href="edit.html">Редактировать</a>
					<a href="../../index.html">Выйти</a>
				</p>
			</div>
			<div id="nav3"><center><h1><a href="contest.jsp">Пример</a></h1></center></div>
		</div>
		<div id="page">
			<iframe src="sidebar.html" width="150px" height="100%" scrolling="no" frameborder="no" style="position: absolute;">Список задач</iframe>
			<div id="content">
				<center><h2>A+B</h2></center>
				<h3>Решение:</h3>
				<p>Решние задачи A+B на Python:</p>
				<p><font color="#FF6699">print</font>(<font color="#FF6699">sum</font>(<font color="#FF6699">map</font>(<font color="#FF6699">int</font>, <font color="#FF6699">input</font>.split())))</p>
				<button onclick="document.location.href='1.jsp'">Вернуться</button>
				<div id="down2"></div>
			</div>
		</div>
	</body>
</html>