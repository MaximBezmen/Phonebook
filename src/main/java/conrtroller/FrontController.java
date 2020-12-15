package conrtroller;

import commands.AbstractCommand;
import commands.ContactSaveCommand;
import commands.UnknownCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class FrontController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    protected AbstractCommand processRequest(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format(
                    "commands.%sCommand",
                    request.getParameter("command")));
            return (AbstractCommand) type
                    .asSubclass(AbstractCommand.class)
                    .newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new UnknownCommand();
        }
    }

}
