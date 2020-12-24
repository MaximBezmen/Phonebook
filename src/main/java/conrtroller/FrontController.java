package conrtroller;

import commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CustomBodyJson;

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
    Logger logger = LoggerFactory.getLogger(FrontController.class);
    Map<UrlMapping, AbstractCommand> mapping;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AbstractCommand command = processRequest(request);
        command.init(getServletContext(), request, response);
        command.process();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = CustomBodyJson.getBody(request);
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

    @Override
    public void init() throws ServletException {
        mapping = new HashMap<>();
        mapping.put(new UrlMapping("GET", "/Phonebook_war/contacts/"),new ContactReadCommand());
        mapping.put(new UrlMapping("POST", "/Phonebook_war/contacts"),new ContactSaveCommand());
        mapping.put(new UrlMapping("DELETE", "/Phonebook_war/contacts/"),new ContactDeleteCommand());
        mapping.put(new UrlMapping("PUT", "/Phonebook_war/contacts/"),new ContactUpdateCommand());
    }

    protected AbstractCommand processRequest(HttpServletRequest request) {
        UrlMapping urlMapping = new UrlMapping(request.getMethod(),request.getRequestURI());
        return mapping.get(urlMapping);
    }

}
