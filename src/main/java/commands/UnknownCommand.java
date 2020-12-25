package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnknownCommand extends AbstractCommand {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Url not mapping in app.");
    }
}
