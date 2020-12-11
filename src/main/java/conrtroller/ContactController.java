package conrtroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.dao.daoImpl.AddressDao;
import database.dao.daoImpl.ContactDao;
import service.ContactService;
import service.CustomBodyRequest;
import service.dto.ContactDto;
import service.impl.ContactServiceImpl;
import service.mapper.ContactMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class ContactController extends HttpServlet {
    private final ContactService contactService;
    private final CustomBodyRequest customBodyRequest;

    public ContactController() {
        this.customBodyRequest = new CustomBodyRequest();
        this.contactService = new ContactServiceImpl(new ContactDao(new AddressDao()), new ContactMapper(phoneNumberMapper));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long contactId = Long.parseLong(req.getParameter("id"));
        resp.getWriter().write(contactService.getContactById((contactId)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            stringBuilder.append(line);
        }

        ContactDto contactDto = new ObjectMapper().readValue(stringBuilder.toString(),ContactDto.class);
        int x = 0;
        resp.getWriter().write(new ObjectMapper().writeValueAsString(contactDto));

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        contactService.deleteContact(id);
        resp.setStatus(200);
    }
}
