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
			var page_number = 6;
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
				<center><h2>B. Шифровка сдвигом</h2></center>
				<center>(Время: 1 сек. Память: 256 Мб)</center>
				<h3>Условие:</h3>
				<p>Петя и Вася придумали систему шифровки для обмена записками. Суть ее заключается в следующем: дана исходная строка \( S \). \( S^{\prime} \) — циклический сдвиг строки влево (первый символ становится последним, а остальные перемещаются на одну позицию влево). \( S^{\prime \prime} \) — циклический сдвиг строки \( S^{\prime}\) и т.д. Петя с Васей выписывают на листок бесконечную последовательность символов \( SS^{\prime}S^{\prime \prime}S^{\prime \prime \prime} \dotso \). Если им необходимо зашифровать символ \(C\), то они ищут какое-либо вхождение этого символа в выписанную последовательность и записывают его порядковый номер \(k\). Нумерацию символов они ведут с единицы.</p>
				<p>Злоумышленник Коля перехватил сообщение и выкрал исходную строку \(S\). Однако он не может определить, какой символ стоит в последовательности \( SS^{\prime}S^{\prime \prime}S^{\prime \prime \prime} \dotso \) на \(k\)-ом месте. Помогите злоумышленнику Коле узнать, какой символ соответствует числу \(k\).</p>
				<h3>Входные данные:</h3>
				<p>Первая строка входного файла содержит строку, состоящую только из строчных латинских букв. Длина строки не превышает 100000 символов. Вторая строка входного файла содержит единственное целое число \( 1 \leq k \leq 2 \cdot 10^9 \).</p>
				<h3>Выходные данные:</h3>
				<p>Единственная строка выходного файла должна содержать символ, который окажется на \( k \)-ом месте сформированной строки.</p>
				<h3>Примеры:</h3>
				<table border="1" width="95%">
					<tr>
						<td width="50%"><center>Input</center></td>
						<td width="50%"><center>Output</center></td>
					</tr>
					<tr>
						<td>
							<p>abcd</p>
							<p>5</p>
						</td>
						<td>
							<p>b</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>abcd</p>
							<p>17</p>
						</td>
						<td>
							<p>a</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>a</p>
							<p>1</p>
						</td>
						<td>
							<p>a</p>
						</td>
					</tr>
				</table>
				<div id="code">
					<p>Решение:</p>
					<% out.print("<form action=\"../run?contest=2&task=6&name="); %>${cookie['name'].getValue()}<% out.print("&surname="); %>${cookie['surname'].getValue()}<% out.print("\" method=\"post\">"); %>
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