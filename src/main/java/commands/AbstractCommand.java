package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.dao.daoImpl.AddressDao;
import database.dao.daoImpl.ContactDao;
import database.dao.daoImpl.PhoneNumberDao;
import service.ContactService;
import service.impl.ContactServiceImpl;
import service.mapper.ContactMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCommand {
    protected ContactService contactService;
    protected ObjectMapper objectMapper;

    public void init() {
        this.contactService = new ContactServiceImpl(new ContactDao(new AddressDao(), new PhoneNumberDao()), new ContactMapper());
        this.objectMapper = new ObjectMapper();
    }

    public abstract void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
