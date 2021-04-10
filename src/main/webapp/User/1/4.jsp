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
			var page_type = "task";
			var page_number = 4;
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
			<iframe src="sidebar.html" width="150px" height="100%" scrolling="no" frameborder="no" style="position: absolute;">Список задач</iframe>"
			<div id="content">
				<center><h2>Формула</h2></center>
				<center>(Время: 1 сек. Память: 16 Мб)</center>
				<h3>Условие:</h3>
				<p>Васины учителя математики устали от того, что он решает всё. И попытались задать ему сложное задание.</p>
				<p>Васе дают два числа, A и B.</p>
				<p>Он расскладывает их по основной теореме арифметики, как:</p>
				<p>\( A = p_1^{\alpha_1} * p_2^{\alpha_2} * ... * p_n^{\alpha_n} \)</p>
				<p>\( B = p_1^{\beta_1} * p_2^{\beta_2} * ... * p_n^{\beta_n} \)</p>
				<p>И ему нужно вычислить значение выражения:</p>
				<p>\( С = p_1^{min(\alpha_1, \beta_1)} * p_2^{min(\alpha_2, \beta_2)} * ... * p_n^{min(\alpha_n, \beta_n)} \)</p>
				<p>Вася с этой задачей справляется быстро, а вам нужно проверить его результаты!</p>
				<h3>Входные данные:</h3>
				<p>Два числа A и B, такие что \( 1 < A, B \leq 10^{18} \).</p>
				<h3>Выходные данные:</h3>
				<p>Вывести число C, которое должен получить Вася.</p>
				<h3>Примеры:</h3>
				<table border="1" width="95%">
					<tr>
						<td width="50%"><center>Input</center></td>
						<td width="50%"><center>Output</center></td>
					</tr>
					<tr>
						<td>
							<p>2 3</p>
						</td>
						<td>
							<p>1</p>
						</td>
					</tr>
				</table>
				<div id="code">
					<p>Решение:</p>
					<% out.print("<form action=\"../run?contest=1&task=4&name="); %>${cookie['name'].getValue()}<% out.print("&surname="); %>${cookie['surname'].getValue()}<% out.print("\" method=\"post\">"); %>
						<textarea id="code_text" name="code" placeholder="Введите код" oninput="textInput(document)"></textarea>
						<br/>
						<input id="code_file" type="file" oninput="codeInput(document, 'file');" onchange="readFile(document);"/>
						<select name="lang">
							<option value="cpp">C++</option>
							<option value="py">Python</option>
						</select>
						<input type="submit" value="Отправить" onclick="return Start(this);" />
					</form>
				</div>
				<button onclick="viewCode(document, page_number)">Мои посылки</button>				<div id="down2"></div>
			</div>
		</div>
	</body>
</html>