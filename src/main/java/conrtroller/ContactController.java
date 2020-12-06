package conrtroller;

import database.dao.daoImpl.AddressDao;
import database.dao.daoImpl.ContactDao;
import service.ContactService;
import service.CustomBodyRequest;
import service.dto.AddressDto;
import service.dto.ContactDto;
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
import java.util.Map;

@WebServlet("/")
public class ContactController extends HttpServlet {
    private final ContactService contactService;
    private final CustomBodyRequest customBodyRequest;

    public ContactController() {
        this.customBodyRequest = new CustomBodyRequest();
        this.contactService = new ContactServiceImpl(new ContactDao(new AddressDao()),new CustomJson(),new ContactMapper());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long contactId = Long.parseLong(req.getParameter("id"));
        resp.getWriter().write(contactService.readContact((contactId)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String,String> body = customBodyRequest.getBody(req);
        ContactDto contactDto = new ContactDto();
        AddressDto addressDto = new AddressDto();
        contactDto.setFirstName(body.get("firstName"));
        contactDto.setLastName(body.get("lastName"));
        contactDto.setLastName(body.get("middleName"));
        contactDto.setBirthday(LocalDate.parse(body.get("birthday")));
        contactDto.setGender(SexType.valueOf(body.get("gender")));
        contactDto.setCitizenship(body.get("citizenship"));
        contactDto.setFamilyStatus(FamilyStatusType.valueOf(body.get("familyStatus")));
        contactDto.setWebSite(body.get("webSite"));
        contactDto.setEmail(body.get("email"));
        contactDto.setCurrentPlaceOfWork(body.get("currentPlaceOfWork"));

        addressDto.setId(Long.parseLong(body.get("addressId")));
        addressDto.setCountry(body.get("country"));
        addressDto.setCity(body.get("city"));
        addressDto.setStreet(body.get("street"));
        addressDto.setHouse(Integer.parseInt(body.get("house")));
        addressDto.setHouse(Integer.parseInt(body.get("flat")));

        contactDto.setAddress(addressDto);
        contactService.saveContact(contactDto);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
