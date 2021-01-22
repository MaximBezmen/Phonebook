package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactDeleteCommand extends AbstractCommand {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        contactService.deleteContact(id);
        response.setStatus(200);
    }

}
