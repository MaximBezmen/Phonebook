package conrtroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactCommand implements Command {
    @Override
    public void execute(String methode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        switch (methode) {
            case "GET":
                new ContactController().doGet(request, response);
            case "POST":
                new ContactController().doPost(request, response);
            case "PUT":
                new ContactController().doPut(request, response);
            case "DELETE":
                new ContactController().doDelete(request, response);
            default:
                throw new IOException();
        }
    }

}
