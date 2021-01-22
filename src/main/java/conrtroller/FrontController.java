package conrtroller;

import commands.AbstractCommand;
import commands.UnknownCommand;
import commands.UrlMapping;
import factory.FactoryCommand;

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
    Map<UrlMapping, String> mapping;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init();
        command.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init();
        command.process(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init();
        command.process(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init();
        command.process(request, response);
    }

    @Override
    public void init() throws ServletException {
        mapping = new HashMap<>();
        mapping.put(new UrlMapping("GET", "/Phonebook_war/contacts"), "ContactReadCommand");
        mapping.put(new UrlMapping("POST", "/Phonebook_war/contacts"), "ContactSaveCommand");
        mapping.put(new UrlMapping("DELETE", "/Phonebook_war/contacts"), "ContactDeleteCommand");
        mapping.put(new UrlMapping("PUT", "/Phonebook_war/contacts"), "ContactUpdateCommand");
    }

    protected AbstractCommand processRequest(HttpServletRequest request) {
        UrlMapping urlMapping = new UrlMapping(request.getMethod(), request.getRequestURI());

        AbstractCommand command = FactoryCommand.getCommand(mapping.get(urlMapping));

        if (command == null) {
            return new UnknownCommand();
        }
        return command;
    }

}
