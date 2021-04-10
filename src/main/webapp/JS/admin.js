function getCookie(document, name) {
    var r = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
    if (r) return r[2].trim();
    else return "";
}

function query(parameters){
    var result = "?";
    parameters.forEach((value, key) => {
        result += encodeURIComponent(key) + '=' + encodeURIComponent(value) + "&";
    });
    return result;
}

function onServerAnswer(){
    if (this.readyState === 4 && this.status === 200) {
        var answer = this.responseText;
        if (answer === "Ok") {
            alert("Изменения сохранены");
        } else {
            alert("Ошибка доступа");
        }
    }
}

function splitDateTime(datetime){
    return new Date(datetime).getTime();
}

var minTime = "2020-10-21T19:50", maxTime = "9999-12-31T23:59";
var minTimeSplit = splitDateTime(minTime);
var maxTimeSplit = splitDateTime(maxTime);
minTime = "21.10.2020 19:50"; maxTime = "31.12.9999 23:59";

function checkTime(time) {
    time = splitDateTime(time);
    return minTimeSplit <= time && time <= maxTimeSplit;
}

function checkPassword(password){
    return /^[\w_\+\-\*\/]*$/.test(password);
}


function NewRow(document, cnt){
    var row = document.createElement("tr");
    var change = 'Change(document, ' + cnt + ');';
    row.id = "" + cnt;
    document.getElementById("task").appendChild(row);
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var td5 = document.createElement("td");
    var td6 = document.createElement("td");
    var td7 = document.createElement("td");
    var td8 = document.createElement("td");
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    row.appendChild(td5);
    row.appendChild(td6);
    row.appendChild(td7);
    row.appendChild(td8);
    td1.innerHTML = '' + cnt;
    td2.innerHTML = '<p id="index'          + cnt + '">-1</p>';
    td3.innerHTML = '<textarea id="input'	+ cnt + '" class="input_output" oninput="' + change + '"></textarea>';
    td4.innerHTML = '<textarea id="output' 	+ cnt + '" class="input_output" oninput="' + change + '"></textarea>';
    td5.innerHTML = '<input id="example' 	+ cnt + '" type="checkbox" onchange="' + change + '">';
    td6.innerHTML = '<input id="public' 	+ cnt + '" type="checkbox" onchange="' + change + '">';
    td7.innerHTML = '<button id="btn' 		+ cnt + '">Изменено</button>';
    td8.innerHTML = '<button onclick="DeleteTest(document, ' + cnt + ');">Удалить</button>';
}

function NewRowLang(document, cnt){
    var row = document.createElement("tr");
    var change = 'Change(document, ' + cnt + ');';
	var innerEnd = cnt + '" class="input_output" oninput="' + change + '"></textarea>';
    row.id = "" + cnt;
    document.getElementById("langs").appendChild(row);
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var td5 = document.createElement("td");
    var td6 = document.createElement("td");
    var td7 = document.createElement("td");
    var td8 = document.createElement("td");
    var td9 = document.createElement("td");
    var td10 = document.createElement("td");
    var td11 = document.createElement("td");
    var td12 = document.createElement("td");
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    row.appendChild(td5);
    row.appendChild(td6);
    row.appendChild(td7);
    row.appendChild(td8);
    row.appendChild(td9);
    row.appendChild(td10);
    row.appendChild(td11);
    row.appendChild(td11);
    row.appendChild(td12);
    td1.innerHTML = '<p id="index'          	+ cnt + '">-1</p>';
    td2.innerHTML = '<textarea id="name'		+ innerEnd;
    td3.innerHTML = '<textarea id="end1' 		+ innerEnd;
    td4.innerHTML = '<textarea id="end2' 		+ innerEnd;
	td5.innerHTML = '<textarea id="free_time' 	+ innerEnd;
	td6.innerHTML = '<textarea id="free_memory' + innerEnd;
	td7.innerHTML = '<textarea id="min_time'	+ innerEnd;
	td8.innerHTML = '<textarea id="min_memory'	+ innerEnd;
    td9.innerHTML = '<textarea id="compile' 	+ innerEnd;
    td10.innerHTML = '<textarea id="execute' 	+ innerEnd;
    td11.innerHTML = '<button id="btn' 			+ cnt + '">Изменено</button>';
    td12.innerHTML = '<button onclick="DeleteLang(document, ' + cnt + ');">Удалить</button>';
}

function Change(document, cnt){
    document.getElementById("btn" + cnt).disabled = false;
}

function ChangeConstant(document, cnt){
	document.getElementById("constant_edit" + cnt).disabled = false;
}

function Save(document, cnt, type, number){
    if (type === 'task') {
        var Url = "../../tasks";
        var data = new Map();
        data.set("name", getCookie(document, "name"));
        data.set("surname", getCookie(document, "surname"));
        data.set("id", number);
        data.set("t_name", document.getElementById("task_name").value);
        data.set("t_about", document.getElementById("task_description").value);
        data.set("t_input", document.getElementById("task_input").value);
        data.set("t_output", document.getElementById("task_output").value);
        data.set("t_solution", document.getElementById("task_solution").value);
		var t_time = document.getElementById("task_time").value;
		var t_memory = document.getElementById("task_memory").value;
		if (t_time <= 0 || t_memory <= 0){
			alert("Время и память на выполнение программы должны быть положительными.");
			return;
		}
		data.set("t_time", t_time);
		data.set("t_memory", t_memory);
        var request = new XMLHttpRequest();
        request.open("POST", Url + query(data));
        request.send();
        request.onreadystatechange = onServerAnswer;

        Url = "../../tests";
        for (var i = 1; i <= cnt; ++i) {
            if (document.getElementById("btn" + i).disabled === false) {
                var data = new Map();
                data.set("input", document.getElementById("input" + i).value);
                data.set("output", document.getElementById("output" + i).value);
                data.set("example", document.getElementById("example" + i).checked);
                data.set("public", document.getElementById("public" + i).checked);
                data.set("task", number);
                data.set("test", document.getElementById("index" + i).textContent);
                data.set("name", getCookie(document, "name"));
                data.set("surname", getCookie(document, "surname"));
                var request = new XMLHttpRequest();
                request.open("POST", Url + query(data));
                request.send();
                document.getElementById("btn" + i).disabled = true;
            }
        }
    }
    if (type === 'contest'){
        var Url = "../../contests";
        var start = document.getElementById("start_datetime").value;
        var finish = document.getElementById("finish_datetime").value;
        var password = document.getElementById("contest_password").value;
        if (!checkTime(start) || !checkTime(finish)){
            alert("Ошибка заполнения полей времени. Значения должны располагаться в диапазоне от " + minTime + " до " + maxTime + ".");
            return;
        }
        if (!checkPassword(password)){
            alert("Пароль должен содержать только буквы, цифры и _ + - * /");
            return;
        }
        var data = new Map();
        data.set("name", getCookie(document, "name"));
        data.set("surname", getCookie(document, "surname"));
        data.set("id", number);
        data.set("t_name", document.getElementById("task_name").value);
        data.set("t_about", document.getElementById("task_description").value);
        data.set("start", splitDateTime(start));
        data.set("finish", splitDateTime(finish));
        data.set("password", password);
        var request = new XMLHttpRequest();
        request.open("POST", Url + query(data));
        request.send();
        request.onreadystatechange = onServerAnswer;
    }
}

function SaveLangs(document, cnt) {
    var  Url = "../check";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("password", "");
    data.set("contest", -1);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = onServerAnswer;
    Url = "../langs";
    for (var i = 1; i <= cnt; ++i) {
        if (document.getElementById("btn" + i).disabled === false) {
            var data = new Map();
            data.set("l_id", document.getElementById("index" + i).textContent);
            data.set("l_name",			document.getElementById("name" + i).value);
            data.set("l_end1",			document.getElementById("end1" + i).value);
            data.set("l_end2",			document.getElementById("end2" + i).value);
            data.set("l_compile",		document.getElementById("compile" + i).value);
            data.set("l_execute",		document.getElementById("execute" + i).value);
            data.set("l_freeTime",		document.getElementById("free_time" + i).value);
            data.set("l_freeMemory",	document.getElementById("free_memory" + i).value);
            data.set("l_minTime",		document.getElementById("min_time" + i).value);
            data.set("l_minMemory",		document.getElementById("min_memory" + i).value);
            data.set("name", getCookie(document, "name"));
            data.set("surname", getCookie(document, "surname"));
            var request = new XMLHttpRequest();
            request.open("POST", Url + query(data));
            request.send();
            document.getElementById("btn" + i).disabled = true;
        }
    }
}

function SaveConstants(document, cnt){
	var Url = "../check";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("password", "");
    data.set("contest", -1);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = onServerAnswer;
    Url = "../constants";
    for (var i = 1; i <= cnt; ++i) {
        if (document.getElementById("constant_edit" + i).disabled === false) {
            var data = new Map();
            data.set("c_name",	document.getElementById("constant_name" + i).textContent);
            data.set("c_value",	document.getElementById("constant_value" + i).value);
            data.set("name", getCookie(document, "name"));
            data.set("surname", getCookie(document, "surname"));
            var request = new XMLHttpRequest();
            request.open("POST", Url + query(data));
            request.send();
            document.getElementById("constant_edit" + i).disabled = true;
        }
    }
}

function SaveLangsToContest(document, contestId){
	var Url = "../../check";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("password", "");
    data.set("contest", -1);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = onServerAnswer;
    Url = "../../contests_langs";
	var langsTable = document.getElementById("langs_table");
	for (var i = 1, row; row = langsTable.rows[i]; ++i) {
		let langId = row.cells[0].textContent;
		let added = document.getElementById("lang_btn" + langId);
		if (added.disabled == false) {
			var data = new Map();
            data.set("contest",	contestId);
            data.set("lang",	row.cells[0].textContent);
            data.set("name", getCookie(document, "name"));
            data.set("surname", getCookie(document, "surname"));
            var request = new XMLHttpRequest();
            request.open("POST", Url + query(data));
            request.send();
            added.disabled = true;
	   }
	}
}

function Delete(document, task, number){
    var Url = "../../delete_task";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("task", task);
    data.set("contest", number);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = onServerAnswer;
}

function DeleteContest(document, number){
    var Url = "../../delete_contest";
    var doDelete = prompt("Вы действительно хотите удалить?\nДля удаления введите \"да\".");
    if (doDelete !== "да") return;
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("contest", number);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var answer = this.responseText;
            if (answer === "Ok") {
                alert("Изменения сохранены");
                document.location.replace("../main.jsp");
            } else {
                alert("Ошибка доступа");
            }
        }
    };
}

function DeleteTest(document, number){
    if (number === -1) return;
    var test = document.getElementById("index" + number).textContent;
    // document.getElementById(number).remove();
    if (test === -1) return;
    var Url = "../../delete_test";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("test", test);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = onServerAnswer;
}

function DeleteLang(document, number){
    if (number === -1) return;
    var lang = parseInt(document.getElementById("index" + number).textContent);
    document.getElementById(number).remove();
    if (lang === -1) return;
    var Url = "../../delete_lang";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("lang", lang);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = onServerAnswer;
}

function Create(document){
    var Url = "../../create_task";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var answer = this.responseText;
            if (answer !== "Fail") {
                document.getElementById("content").innerHTML = answer;
            } else {
                alert("Ошибка доступа");
            }
        }
    };
}

function AddTask(document, contestId) {
    var Url = "../../add_task";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("contest", contestId);
    data.set("t_name", document.getElementById("task_name").value);
    data.set("t_about", document.getElementById("task_description").value);
    data.set("t_input", document.getElementById("task_input").value);
    data.set("t_output", document.getElementById("task_output").value);
    data.set("t_solution", document.getElementById("task_solution").value);
	var t_time = document.getElementById("task_time").value;
	var t_memory = document.getElementById("task_memory").value;
	if (t_time <= 0 || t_memory <= 0){
		alert("Время и память на выполнение программы должны быть положительными.");
		return;
	}
	data.set("t_time", t_time);
	data.set("t_memory", t_memory);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var answer = this.responseText;
            if (answer === "Ok") {
                alert("Изменения сохранены");
                document.location.replace("contest.jsp");
            } else {
                alert("Ошибка доступа");
                document.location.replace("contest.jsp");
            }
        }
    };
}


function AddContest(document) {
    var start = document.getElementById("start_datetime").value;
    var finish = document.getElementById("finish_datetime").value;
    if (!checkTime(start) || !checkTime(finish)){
        alert("Ошибка заполнения полей времени. Значения должны располагаться в диапазоне от " + minTime + " до " + maxTime + ".");
        return;
    }
    var Url = "../add_contest";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("c_name", document.getElementById("contest_name").value);
    data.set("c_about", document.getElementById("contest_description").value);
    data.set("start", splitDateTime(start));
    data.set("finish", splitDateTime(finish));
    data.set("password", document.getElementById("password").value);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var answer = this.responseText;
            if (answer === "Ok") {
                alert("Изменения сохранены");
                document.location.replace("main.jsp");
            } else {
                alert("Ошибка доступа");
                document.location.replace("main.jsp");
            }
        }
    };
}

function Generate(document, number){
    var Url = "../../generate";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("contest", number);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = onServerAnswer;
}

function DeleteLangFromContest(document, contestId, langId = null) {
	if (langId === null){
		let name = document.getElementById("lang_row_" + contestId).cells[1].textContent;
		document.getElementById("lang_row_" + contestId).remove();
		let opt = document.createElement("option");
		opt.id = "l_" + contestId;
		opt.value = "l_" + contestId;
		opt.innerHTML = name;
		document.getElementById("langs_list").appendChild(opt);
		return;
	}
	Url = "../../delete_contest_lang";
	var data = new Map();
	data.set("contest",	contestId);
	data.set("lang",	langId);
	data.set("name", getCookie(document, "name"));
	data.set("surname", getCookie(document, "surname"));
	var request = new XMLHttpRequest();
	request.open("POST", Url + query(data));
	request.send();
	var langsTable = document.getElementById("langs_table");
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var answer = this.responseText;
            if (answer === "Ok") {
                alert("Изменения сохранены");	
				let name = document.getElementById("lang_row_" + langId).cells[1].textContent;
				document.getElementById("lang_row_" + langId).remove();
				let opt = document.createElement("option");
				opt.id = "l_" + langId;
				opt.value = "l_" + langId;
				opt.innerHTML = name;
				document.getElementById("langs_list").appendChild(opt);
            } else {
                alert("Ошибка доступа");
            }
        }
    };
}

function AddLangToContest(document){
    var e = document.getElementById("langs_list");
    var langShortName = e.value;
    var langLongName = e.options[e.selectedIndex].text;
    var langId = langShortName.substr(2);

    var row = document.createElement("tr");
    row.id = "lang_row_" + langId;
    document.getElementById("langs_table").appendChild(row);
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    td1.innerHTML = langId;
    td2.innerHTML = langLongName;
    td3.innerHTML = '<center><button id="lang_btn' 		+ langId + '">Добавлено</button></center>';
    td4.innerHTML = '<center><button onclick="DeleteLangFromContest(document, ' + langId + ');">Удалить</button></center>';

    let option = document.getElementById(langShortName);
    option.parentNode.removeChild(option);
}