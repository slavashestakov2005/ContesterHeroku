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
			var page_number = 5;
			var page_contest = 2;
			Check(document, page_contest);
		</script>
		<script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
		<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
		<title>Отборочный тур Сириуса</title>
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
			<div id="nav3"><center><h1><a href="contest.jsp">Отборочный тур Сириуса</a></h1></center></div>
		</div>
		<div id="page">
			<iframe src="sidebar.html" width="150px" height="100%" scrolling="no" frameborder="no" style="position: absolute;">Список задач</iframe>"
			<div id="content">
				<center><h2>A. Корыстный олимпиадник</h2></center>
				<center>(Время: 1 сек. Память: 256 Мб)</center>
				<h3>Условие:</h3>
				<p>На одной небезызвестной олимпиаде \(n\) человек взяли дипломы победителей. Теперь единственное, что эти победители обсуждают — размер премии за успешное выступление на олимпиаде. Один из них устал ждать денег, поэтому он пошёл в министерство образования и потребовал свою премию.</p>
				<p>В министерстве образования сжалились и решили выделить корыстному олимпиаднику его премию. Бюджет премий был разделён на \(n\) почти равных частей так, что в каждой части было целое число денег, и разница числа денег между любыми двумя частями не превышала \(1\). Корыстному олимпиаднику дали наименьшую из всех частей. После этого в бюджете премий осталось \(m\) денег, и они в дальнейшем были распределены между оставшимися \(n - 1\) победителями олимпиады.</p>
				<p>Теперь победителям олимпиады интересно, а какой же был изначальный бюджет премий. Помогите им это выяснить.</p>
				<h3>Входные данные:</h3>
				<p>В первой строке вводится единственное число \( n (2 \leq n \leq 15) \) — число победителей олимпиады.</p>
				<p>Во второй строке вводится единственное число \( m (n \leq m \leq 100) \) — число оставшихся денег в бюджете премий после выдачи премии корыстному олимпиаднику.</p>
				<h3>Выходные данные:</h3>
				<p>В единственной строке выведите два числа — минимальное и максимальное возможное число денег в изначальном бюджете премий.</p>
				<h3>Примеры:</h3>
				<table border="1" width="95%">
					<tr>
						<td width="50%"><center>Input</center></td>
						<td width="50%"><center>Output</center></td>
					</tr>
					<tr>
						<td>
							<p>2</p>
							<p>5</p>
						</td>
						<td>
							<p>9 10</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>3</p>
							<p>5</p>
						</td>
						<td>
							<p>7 7</p>
						</td>
					</tr>
				</table>
				<div id="code">
					<p>Решение:</p>
					<% out.print("<form action=\"../run?contest=2&task=5&name="); %>${cookie['name'].getValue()}<% out.print("&surname="); %>${cookie['surname'].getValue()}<% out.print("\" method=\"post\">"); %>
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