package launch;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.database.DataBaseHelper;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

    private static File getRootFolder() {
        try {
            File root;
            String runningJarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
            int lastIndexOf = runningJarPath.lastIndexOf("/target/");
            if (lastIndexOf < 0) {
                root = new File("");
            } else {
                root = new File(runningJarPath.substring(0, lastIndexOf));
            }
            System.out.println("application resolved root folder: " + root.getAbsolutePath());
            return root;
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void createDataBase(){
        DataBaseHelper.execute("DROP TABLE IF EXISTS constants, contests, contests_langs, contests_tasks, langs, sendings, tasks, tests");
        /*loadConstants();
        loadContests();
        loadContestsLangs();
        loadContestsTasks();
        loadLangs();
        loadSendings();
        loadTasks();
        loadTests();*/
    }

    private static void loadConstants(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"constants\" (\n" +
                "\t\"name\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"value\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"name\")\n" +
                ");");
        DataBaseHelper.execute("INSERT INTO constants VALUES ('admin_name', 'admin_name');");
        DataBaseHelper.execute("INSERT INTO constants VALUES ('admin_surname', 'admin_surname');");
        DataBaseHelper.execute("INSERT INTO constants VALUES ('compile_time', '10000');");
    }

    private static void loadContests(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"contests\" (\n" +
                "\t\"id\"\tserial primary key,\n" +
                "\t\"name\"\tTEXT NOT NULL,\n" +
                "\t\"description\"\tTEXT,\n" +
                "\t\"start\"\tBIGINT NOT NULL,\n" +
                "\t\"finish\"\tBIGINT NOT NULL,\n" +
                "\t\"password\"\tTEXT NOT NULL\n" +
                ");");
        DataBaseHelper.execute("INSERT INTO contests VALUES ('1', 'Пример', 'Здесь располагается описание контеста.', '1615345200000', '1622430000000', '');");
        DataBaseHelper.execute("INSERT INTO contests VALUES ('2', 'Отборочный тур Сириуса', 'Отборочный тур на программу \"Алгоритмы и анализ данныз\" (декабрь 2020).\n" +
                "Тур проводился 06.11.2020 с системой прокторинга.\n" +
                "Время на написание — 4 часа.\n" +
                "Начать было можно в любое время с 8:30 до 10:00 (МСК).', '1604626200000', '1636162200000', '_*');");
    }

    private static void loadContestsLangs(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"contests_langs\" (\n" +
                "\t\"id_contest\"\tINTEGER NOT NULL primary key,\n" +
                "\t\"id_lang\"\tINTEGER NOT NULL primary key\n" +
                ");");
        DataBaseHelper.execute("INSERT INTO contests_langs VALUES ('1', '1');");
        DataBaseHelper.execute("INSERT INTO contests_langs VALUES ('1', '2');");
    }

    private static void loadContestsTasks(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"contests_tasks\" (\n" +
                "\t\"id_contest\"\tINTEGER NOT NULL primary key,\n" +
                "\t\"id_task\"\tINTEGER NOT NULL primary key\n" +
                ");");
        DataBaseHelper.execute("INSERT INTO contests_tasks VALUES ('1', '1');");
        DataBaseHelper.execute("INSERT INTO contests_tasks VALUES ('1', '2');");
        DataBaseHelper.execute("INSERT INTO contests_tasks VALUES ('1', '3');");
        DataBaseHelper.execute("INSERT INTO contests_tasks VALUES ('1', '4');");
        DataBaseHelper.execute("INSERT INTO contests_tasks VALUES ('2', '5');");
        DataBaseHelper.execute("INSERT INTO contests_tasks VALUES ('2', '6');");
    }

    private static void loadLangs(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"langs\" (\n" +
                "\t\"id\"\tserial primary key,\n" +
                "\t\"name\"\tTEXT NOT NULL,\n" +
                "\t\"end1\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"end2\"\tTEXT NOT NULL,\n" +
                "\t\"compile\"\tTEST NOT NULL,\n" +
                "\t\"execute\"\tTEXT NOT NULL,\n" +
                "\t\"freeTime\"\tINTEGER NOT NULL,\n" +
                "\t\"freeMemory\"\tINTEGER NOT NULL,\n" +
                "\t\"minTime\"\tINTEGER NOT NULL,\n" +
                "\t\"minMemory\"\tINTEGER NOT NULL\n" +
                ");");
        DataBaseHelper.execute("INSERT INTO langs VALUES ('1', 'C++', 'cpp', 'exe', 'g++ -static -fno-strict-aliasing -DACMP -lm -s -x c++ -std=c++17 -Wl,--stack=67108864 -O2 -o {1} {0}', '{0}', '290', '3600', '30', '400');");
        DataBaseHelper.execute("INSERT INTO langs VALUES ('2', 'Python', 'py', 'pyc', 'python -c \"import py_compile; py_compile.compile(r\\\"{0}\\\", r\\\"{1}\\\");\"', 'python {0}', '290', '7000', '45', '740');");
    }

    private static void loadSendings(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"sendings\" (\n" +
                "\t\"user_id\"\tTEXT NOT NULL,\n" +
                "\t\"task_id\"\tINTEGER NOT NULL,\n" +
                "\t\"status\"\tINTEGER NOT NULL,\n" +
                "\t\"code\"\tTEXT,\n" +
                "\t\"lang_id\"\tINTEGER NOT NULL,\n" +
                "\t\"time\"\tBIGINT NOT NULL,\n" +
                "\t\"bad_test\"\tINTEGER NOT NULL\n" +
                ");");
    }

    private static void loadTasks(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"tasks\" (\n" +
                "\t\"id\"\tserial primary key,\n" +
                "\t\"name\"\tTEXT NOT NULL,\n" +
                "\t\"description\"\tTEXT,\n" +
                "\t\"input\"\tTEXT,\n" +
                "\t\"output\"\tTEXT,\n" +
                "\t\"solution\"\tTEXT,\n" +
                "\t\"timeLimit\"\tINTEGER NOT NULL,\n" +
                "\t\"memoryLimit\"\tINTEGER NOT NULL\n" +
                ");");
        DataBaseHelper.execute("INSERT INTO tasks VALUES ('1', 'A+B', 'Вводятся числа A и B. Выведите их сумму.', 'Два целых числа, таких что \\( |A| \\leq 10^9 \\) и \\( |B| \\leq 10^9 \\).', 'Выведите одно число, сумму входных чисел.', 'Решние задачи A+B на Python:\n" +
                "<font color=\"#FF6699\">print</font>(<font color=\"#FF6699\">sum</font>(<font color=\"#FF6699\">map</font>(<font color=\"#FF6699\">int</font>, <font color=\"#FF6699\">input</font>.split())))\n" +
                "', '1000', '16');");
        DataBaseHelper.execute("INSERT INTO tasks VALUES ('2', 'Задача №2', 'В задачах можно использовать \\( \\LaTeX{} \\).\n" +
                "Он находится между символами \\ ( и \\ ) (без пробела между ними).\n" +
                "Часто используемые символы в \\( \\LaTeX \\):\n" +
                "\\( \\alpha \\) — \\alpha — и другие греческие буквы.\n" +
                "\\( \\frac{a}{b} \\) — \\frac{a}{b} — дроби.\n" +
                "\\( a^{b} и a_b \\) — a^{b} и a_b — стпени и индексы.\n" +
                "\\( \\leq и \\geq \\) — \\leq и \\geq — знаки сравнения.\n" +
                "\\( \\prime \\) — \\prime — одинарная кавычка.\n" +
                "\\( \\dotso \\) — \\dotso — один из видов многоточия.\n" +
                "', '', '', '', '1', '1');");
        DataBaseHelper.execute("INSERT INTO tasks VALUES ('3', 'Задача №3', 'В контестере можно использовать HTML.\n" +
                "В частности можно делать ссылки:\n" +
                "<a href=\"https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C\">Сделайте калькуляятор по этой инструкции.</a>\n" +
                "Или картинки.\n" +
                "<img src=\"../../Images/edit.png\"\n" +
                "Также html код можно выводить в исходном виде:\n" +
                "<script>document.write(ToCorrectText(\"<i> Не курсивный текст </i>\"));</script>\n" +
                "<i>Курсивный текст</i>\n" +
                "Для этого нужно написать:\n" +
                "document.write(ToCorrectText(\"<script>document.write(ToCorrectText(\"<i> Не курсивный текст </i>\"));</script>\"));\n" +
                "Это должно располагаться внутри тега script.', '', '', '', '1000', '16');");
        DataBaseHelper.execute("INSERT INTO tasks VALUES ('4', 'Формула', 'Васины учителя математики устали от того, что он решает всё. И попытались задать ему сложное задание.\n" +
                "Васе дают два числа, A и B.\n" +
                "Он расскладывает их по основной теореме арифметики, как:\n" +
                "\\( A = p_1^{\\alpha_1} * p_2^{\\alpha_2} * ... * p_n^{\\alpha_n} \\)\n" +
                "\\( B = p_1^{\\beta_1} * p_2^{\\beta_2} * ... * p_n^{\\beta_n} \\)\n" +
                "И ему нужно вычислить значение выражения:\n" +
                "\\( С = p_1^{min(\\alpha_1, \\beta_1)} * p_2^{min(\\alpha_2, \\beta_2)} * ... * p_n^{min(\\alpha_n, \\beta_n)} \\)\n" +
                "Вася с этой задачей справляется быстро, а вам нужно проверить его результаты!', 'Два числа A и B, такие что \\( 1 < A, B \\leq 10^{18} \\).', 'Вывести число C, которое должен получить Вася.', '', '1000', '16');");
        DataBaseHelper.execute("INSERT INTO tasks VALUES ('5', 'A. Корыстный олимпиадник', 'На одной небезызвестной олимпиаде \\(n\\) человек взяли дипломы победителей. Теперь единственное, что эти победители обсуждают — размер премии за успешное выступление на олимпиаде. Один из них устал ждать денег, поэтому он пошёл в министерство образования и потребовал свою премию.\n" +
                "\n" +
                "В министерстве образования сжалились и решили выделить корыстному олимпиаднику его премию. Бюджет премий был разделён на \\(n\\) почти равных частей так, что в каждой части было целое число денег, и разница числа денег между любыми двумя частями не превышала \\(1\\). Корыстному олимпиаднику дали наименьшую из всех частей. После этого в бюджете премий осталось \\(m\\) денег, и они в дальнейшем были распределены между оставшимися \\(n - 1\\) победителями олимпиады.\n" +
                "\n" +
                "Теперь победителям олимпиады интересно, а какой же был изначальный бюджет премий. Помогите им это выяснить.', 'В первой строке вводится единственное число \\( n (2 \\leq n \\leq 15) \\) — число победителей олимпиады.\n" +
                "\n" +
                "Во второй строке вводится единственное число \\( m (n \\leq m \\leq 100) \\) — число оставшихся денег в бюджете премий после выдачи премии корыстному олимпиаднику.', 'В единственной строке выведите два числа — минимальное и максимальное возможное число денег в изначальном бюджете премий.', '', '1000', '256');");
        DataBaseHelper.execute("INSERT INTO tasks VALUES ('6', 'B. Шифровка сдвигом', 'Петя и Вася придумали систему шифровки для обмена записками. Суть ее заключается в следующем: дана исходная строка \\( S \\). \\( S^{\\prime} \\) — циклический сдвиг строки влево (первый символ становится последним, а остальные перемещаются на одну позицию влево). \\( S^{\\prime \\prime} \\) — циклический сдвиг строки \\( S^{\\prime}\\) и т.д. Петя с Васей выписывают на листок бесконечную последовательность символов \\( SS^{\\prime}S^{\\prime \\prime}S^{\\prime \\prime \\prime} \\dotso \\). Если им необходимо зашифровать символ \\(C\\), то они ищут какое-либо вхождение этого символа в выписанную последовательность и записывают его порядковый номер \\(k\\). Нумерацию символов они ведут с единицы.\n" +
                "Злоумышленник Коля перехватил сообщение и выкрал исходную строку \\(S\\). Однако он не может определить, какой символ стоит в последовательности \\( SS^{\\prime}S^{\\prime \\prime}S^{\\prime \\prime \\prime} \\dotso \\) на \\(k\\)-ом месте. Помогите злоумышленнику Коле узнать, какой символ соответствует числу \\(k\\).', 'Первая строка входного файла содержит строку, состоящую только из строчных латинских букв. Длина строки не превышает 100000 символов. Вторая строка входного файла содержит единственное целое число \\( 1 \\leq k \\leq 2 \\cdot 10^9 \\).', 'Единственная строка выходного файла должна содержать символ, который окажется на \\( k \\)-ом месте сформированной строки.', '', '1000', '256');");
    }

    private static void loadTests(){
        DataBaseHelper.execute("CREATE TABLE IF NOT EXISTS \"tests\" (\n" +
                "\t\"id\"\tserial primary key,\n" +
                "\t\"id_task\"\tINTEGER NOT NULL,\n" +
                "\t\"input\"\tTEXT,\n" +
                "\t\"output\"\tTEXT,\n" +
                "\t\"example\"\tINTEGER NOT NULL,\n" +
                "\t\"public\"\tINTEGER NOT NULL\n" +
                ");");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('1', '1', '2 3', '5', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('2', '1', '0 5', '5', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('3', '1', '-5 100', '95', '0', '1');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('5', '1', '7 13', '20', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('6', '3', '3 5', '125', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('7', '4', '2 3', '1', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('8', '5', '2\n" +
                "5', '9 10', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('9', '5', '3\n" +
                "5', '7 7', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('10', '6', 'abcd\n" +
                "5', 'b', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('11', '6', 'abcd\n" +
                "17', 'a', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('12', '6', 'a\n" +
                "1', 'a', '1', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('13', '1', '1000 -1234', '-234', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('14', '1', '7 8', '15', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('15', '1', '10 11', '21', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('16', '1', '6 8', '14', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('17', '1', '10 -10', '0', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('18', '1', '10 10', '20', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('19', '1', '0 0', '0', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('20', '4', '100 101', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('21', '4', '1000 1001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('22', '4', '10000 10001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('23', '4', '100000 100001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('24', '4', '1000000 1000001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('25', '4', '10000000 10000001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('26', '4', '100000000 100000001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('27', '4', '200000000 200000001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('28', '4', '300000000 300000001', '1', '0', '0');");
        DataBaseHelper.execute("INSERT INTO tests VALUES ('29', '4', '1000000000 1000000001', '1', '0', '0');");
    }

    public static void main(String[] args) throws Exception {

        createDataBase();

        File root = getRootFolder();
        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
        Tomcat tomcat = new Tomcat();
        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempPath.toString());

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        File webContentFolder = new File(root.getAbsolutePath(), "src/main/webapp/");
        if (!webContentFolder.exists()) {
            webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
        }
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder.getAbsolutePath());
        //Set execution independent of current thread context classloader (compatibility with exec:java mojo)
        ctx.setParentClassLoader(Main.class.getClassLoader());

        System.out.println("configuring app with basedir: " + webContentFolder.getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);

        WebResourceSet resourceSet;
        if (additionWebInfClassesFolder.exists()) {
            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
            System.out.println("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
        } else {
            resourceSet = new EmptyResourceSet(resources);
        }
        resources.addPreResources(resourceSet);
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}
