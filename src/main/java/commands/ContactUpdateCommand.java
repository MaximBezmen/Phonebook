package commands;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import service.CustomBodyJson;
import service.dto.ContactDto;

import javax.servlet.ServletException;
import java.io.IOException;

public class ContactUpdateCommand extends AbstractCommand {
    @Override
    public void process() throws ServletException, IOException {
        String body = CustomBodyJson.getBody(request);
        objectMapper.registerModule(new JavaTimeModule());
        ContactDto contactDto = objectMapper.readValue(body, ContactDto.class);
        String responseAnswer = contactService.updateContact(contactDto);
        response.getWriter().write(responseAnswer);
    }
}
