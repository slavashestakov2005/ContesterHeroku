package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       list(Paths.get("").toAbsolutePath().toString());
    }

    void list(String path){
        File f = new File(path);
        String[] dir = f.list();
        if (dir == null) return;
        for(int i = 0; i < dir.length; i++) {
            File f1 = new File(path + File.separator + dir[i]);
            if(f1.isFile()) System.out.println(path + File.separator + dir[i]);
            else list(path + File.separator + dir[i]);
        }
    }
}
