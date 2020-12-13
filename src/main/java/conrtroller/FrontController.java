package conrtroller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mappingUrl = request.getServletPath();
        String methode = request.getMethod();

        Map<String, FrontCommand> frontCommandMap = new HashMap<>();
        frontCommandMap.put("/contact", new ContactCommand());

        FrontCommand frontCommand = frontCommandMap.get(mappingUrl);
        frontCommand.execute(methode, request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

}
