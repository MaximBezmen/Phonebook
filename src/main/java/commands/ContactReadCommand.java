package commands;

import javax.servlet.HttpConstraintElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactReadCommand extends AbstractCommand {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long contactId = Long.valueOf(request.getParameter("id"));
        String responseAnswer = objectMapper.writeValueAsString(contactService.getContactById(contactId));
        response.getWriter().write(responseAnswer);
    }

}