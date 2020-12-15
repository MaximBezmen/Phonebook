package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.dao.daoImpl.AddressDao;
import database.dao.daoImpl.ContactDao;
import database.dao.daoImpl.PhoneNumberDao;
import service.ContactService;
import service.impl.ContactServiceImpl;
import service.mapper.ContactMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCommand {
    protected ContactService contactService;
    protected ObjectMapper objectMapper;
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.contactService = new ContactServiceImpl(new ContactDao(new AddressDao(), new PhoneNumberDao()), new ContactMapper());
        this.objectMapper = new ObjectMapper();
    }
    public abstract void process() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/WEB-INF/jsp/%s.jsp", target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
