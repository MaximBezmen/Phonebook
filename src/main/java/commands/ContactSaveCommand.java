package commands;

import service.CustomBodyJson;
import service.dto.ContactDto;

import javax.servlet.ServletException;
import java.io.IOException;

public class ContactSaveCommand extends AbstractCommand {

    @Override
    public void process() throws ServletException, IOException {
        String body = CustomBodyJson.getBody(request);
        ContactDto contactDto = objectMapper.readValue(body, ContactDto.class);
        String responseAnswer = objectMapper.writeValueAsString(contactService.saveContact(contactDto)) ;
        response.getWriter().write(responseAnswer);
    }
}
