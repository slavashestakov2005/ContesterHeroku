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
			var page_number = 2;
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
				<center><h2>Задача №2</h2></center>
				<center>(Время: 0.001 сек. Память: 1 Мб)</center>
				<h3>Условие:</h3>
				<p>В задачах можно использовать \( \LaTeX{} \).</p>
				<p>Он находится между символами \ ( и \ ) (без пробела между ними).</p>
				<p>Часто используемые символы в \( \LaTeX \):</p>
				<p>\( \alpha \) — \alpha — и другие греческие буквы.</p>
				<p>\( \frac{a}{b} \) — \frac{a}{b} — дроби.</p>
				<p>\( a^{b} и a_b \) — a^{b} и a_b — стпени и индексы.</p>
				<p>\( \leq и \geq \) — \leq и \geq — знаки сравнения.</p>
				<p>\( \prime \) — \prime — одинарная кавычка.</p>
				<p>\( \dotso \) — \dotso — один из видов многоточия.</p>
				<div id="code">
					<p>Решение:</p>
					<% out.print("<form action=\"../run?contest=1&task=2&name="); %>${cookie['name'].getValue()}<% out.print("&surname="); %>${cookie['surname'].getValue()}<% out.print("\" method=\"post\">"); %>
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