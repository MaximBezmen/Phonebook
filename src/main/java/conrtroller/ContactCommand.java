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
                break;
            case "POST":
                new ContactController().doPost(request, response);
                break;
            case "PUT":
                new ContactController().doPut(request, response);
                break;
            case "DELETE":
                new ContactController().doDelete(request, response);
                break;
            default:
                throw new IOException();
        }
    }

}
