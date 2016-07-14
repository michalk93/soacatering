package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by mkolbusz on 7/15/16.
 */
public class DynamicImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("file");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("/home/mkolbusz/uploads/" + filename));
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();

        resp.getOutputStream().write(bytes);
    }
}
