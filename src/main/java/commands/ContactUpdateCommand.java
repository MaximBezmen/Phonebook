package commands;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import service.CustomBodyJson;
import service.dto.ContactDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactUpdateCommand extends AbstractCommand {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = CustomBodyJson.getBody(request);
        objectMapper.registerModule(new JavaTimeModule());
        ContactDto contactDto = objectMapper.readValue(body, ContactDto.class);
        String responseAnswer = objectMapper.writeValueAsString(contactService.updateContact(contactDto));
        response.getWriter().write(responseAnswer);
    }
}
