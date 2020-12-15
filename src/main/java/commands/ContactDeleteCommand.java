package commands;

public class ContactDeleteCommand extends AbstractCommand {

    @Override
    public void process() {
        Long id = Long.parseLong(request.getParameter("id"));
        contactService.deleteContact(id);
        response.setStatus(200);
    }

}
