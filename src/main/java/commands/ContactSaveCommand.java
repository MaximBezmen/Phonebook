package commands;

import service.CustomBodyJson;
import service.dto.ContactDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactSaveCommand extends AbstractCommand {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = CustomBodyJson.getBody(request);
        ContactDto contactDto = objectMapper.readValue(body, ContactDto.class);
        String responseAnswer = objectMapper.writeValueAsString(contactService.saveContact(contactDto)) ;
        response.getWriter().write(responseAnswer);
    }
}
