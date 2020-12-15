package commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class ContactReadCommand extends AbstractCommand {

    @Override
    public void process() throws ServletException, IOException {
        Long contactId = Long.valueOf(request.getParameter("id"));
        String responseAnswer = contactService.getContactById(contactId);
        response.getWriter().write(responseAnswer);
    }

}