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

function ToCorrectText(s){
    var el = document.createElement("div");
    el.innerText = el.textContent = s;
    s = el.innerHTML;
    return s;
}


function checkName(password){
    return /^[\w_а-яА-Я\+\-\*\/]+$/.test(password);
}


function printNameSurname(document){
    var name = getCookie(document, "name");
    var surname = getCookie(document, "surname");
    if (!checkName(name) || !checkName(surname)){
        alert("Имя и фамилия могут содержать только буквы, цифры и _ + - * /");
		alert(name + " - " + surname);
        document.location.replace("index.jsp");
    }
    document.write(name);
    document.write("<br \>\n");
    document.write(surname);
}


function textInput(document) {
    var textarea = document.getElementById("code_text");
    textarea.style.height = '1px';
    textarea.style.height = (textarea.scrollHeight + 6) + 'px';
    if (textarea.value != null && textarea.value !== "") codeInput(document, 'text');
}

function codeInput(document, type) {
    var codeFile = document.getElementById("code_file");
    var codeText = document.getElementById("code_text");
    if (type === "file") codeText.value = "";
    if (type === "text") codeFile.value = "";
}

function readFile(document) {
    var codeFile = document.getElementById("code_file");
    var codeText = document.getElementById("code_text");
    let file = codeFile.files[0];
    let reader = new FileReader();
    reader.readAsText(file);
    reader.onload = function(){
        codeText.value = reader.result;
        textInput(document);
    }
}

function Edit(document, type, number) {
    if (type === 'task' || type === 'solution'){
        const Url = "../../edit_task";
        var data = new Map();
        data.set("name", getCookie(document, "name"));
        data.set("surname", getCookie(document, "surname"));
        data.set("task", number);
        var request = new XMLHttpRequest();
        request.open("POST", Url + query(data));
        request.send();
        request.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                var answer = this.responseText;
                if (answer !== "Fail"){
                    document.getElementById("content").innerHTML = answer;
                }
                else{
                    alert("Ошибка доступа");
                }
            }
        };
    }
    if (type === 'contest'){
        const Url = "../../edit_contest";
        var data = new Map();
        data.set("name", getCookie(document, "name"));
        data.set("surname", getCookie(document, "surname"));
        data.set("contest", number);
        var request = new XMLHttpRequest();
        request.open("POST", Url + query(data));
        request.send();
        request.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                var answer = this.responseText;
                if (answer !== "Fail"){
                    document.getElementById("content").innerHTML = answer;
                }
                else{
                    alert("Ошибка доступа");
                }
            }
        };
    }
}

function Check(document, number) {
    const Url = "../../check";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("password", getCookie(document, "password"));
    data.set("contest", number);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data), false);
    request.send();
    if (request.readyState === 4 && request.status === 200) {
        var answer = request.responseText;
        if (answer === "Fail"){
            document.location.replace("../" + number + ".jsp");
            alert("Неверный пароль или контест не идёт.");
        }
    }
}

function CreateContest(document) {
    var Url = "../create_contest";
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

function EditSettings(document){
    var Url = "../edit_settings";
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
                window.parent.document.getElementById("content").innerHTML = answer;
            } else {
                alert("Ошибка доступа");
            }
        }
    };
    return false;
}

function getResultUrl(type) {
    if (type === 0) return "../result";
    return "../../result";
}

function viewResults(contestId, type){
    var Url = getResultUrl(type);
    var data = new Map();
    data.set("contest", contestId);
    var request = new XMLHttpRequest();
    request.open("GET", Url + query(data));
    request.send();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.parent.document.getElementById("content").innerHTML = this.responseText;
        }
    };
    return false;
}

function viewCode(document, taskId){
	Url = "../../view_code";
	var data = new Map();
	data.set("task",	taskId);
	data.set("name", getCookie(document, "name"));
	data.set("surname", getCookie(document, "surname"));
	var request = new XMLHttpRequest();
	request.open("GET", Url + query(data));
	request.send();
	 request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.parent.document.getElementById("content").innerHTML = this.responseText;
        }
    };
	return false;
}

function renderLatex(document){
    var Url = "render";
    var data = new Map();
    data.set("name", getCookie(document, "name"));
    data.set("surname", getCookie(document, "surname"));
    data.set("text", document.getElementById("text_area").value);
    var request = new XMLHttpRequest();
    request.open("POST", Url + query(data));
    request.send();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var answer = this.responseText;
            if (answer !== "Fail") {
                document.getElementById("latex_frame").contentDocument.location.reload();
            } else {
                alert("Ошибка доступа");
            }
        }
    };
    return false;
}
