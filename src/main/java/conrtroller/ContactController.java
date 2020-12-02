package conrtroller;

import database.dao.daoImpl.AddressDao;
import database.dao.daoImpl.ContactDao;
import entity.Address;
import entity.Contact;
import service.ContactService;
import service.impl.ContactServiceImpl;
import service.json.CustomJson;
import service.mapper.ContactMapper;
import type.FamilyStatusType;
import type.SexType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/")
public class ContactController extends HttpServlet {
    private final ContactService contactService;

    public ContactController() {
        this.contactService = new ContactServiceImpl(new ContactDao(new AddressDao()),new CustomJson(),new ContactMapper());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Contact contact = new Contact();
        contact.setFirstName("A");
        contact.setLastName("B");
        contact.setMiddleName("C");
        contact.setBirthday(LocalDate.now());
        contact.setGender(SexType.FEMALE);
        contact.setCitizenship("dsd");
        contact.setFamilyStatus(FamilyStatusType.SINGLE);
        contact.setWebSite("www");
        contact.setEmail("@");
        contact.setCurrentPlaceOfWork("ADC");
        Address address = new Address();
        address.setCountry("Belareus");
        address.setCity("Minsk");
        address.setStreet("Lov");
        address.setHouse(1);
        address.setFlat(3);
        contact.setAddress(address);

        resp.getWriter().write(contactService.saveContact(contact));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
