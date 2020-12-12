package conrtroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.dao.daoImpl.AddressDao;
import database.dao.daoImpl.ContactDao;
import database.dao.daoImpl.PhoneNumberDao;
import service.ContactService;
import service.CustomBodyJson;
import service.dto.ContactDto;
import service.impl.ContactServiceImpl;
import service.mapper.ContactMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class ContactController extends HttpServlet {
    private final ContactService contactService;
    private final ObjectMapper objectMapper;

    public ContactController() {
        this.contactService = new ContactServiceImpl(new ContactDao(new AddressDao(), new PhoneNumberDao()), new ContactMapper());
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long contactId = Long.valueOf(req.getParameter("id"));
        String response = contactService.getContactById(contactId);
        resp.getWriter().write(response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = CustomBodyJson.getBody(req);
        ContactDto contactDto = objectMapper.readValue(body, ContactDto.class);
        String response = contactService.saveContact(contactDto);
        resp.getWriter().write(response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = CustomBodyJson.getBody(req);
        ContactDto contactDto = objectMapper.readValue(body, ContactDto.class);
        String response = contactService.updateContact(contactDto);
        resp.getWriter().write(response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        contactService.deleteContact(id);
        resp.setStatus(200);
    }
}
