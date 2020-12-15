package commands;

import java.io.IOException;

public class UnknownCommand extends AbstractCommand {

    @Override
    public void process() throws IOException {
        response.getWriter().write("unknown");
    }
}
