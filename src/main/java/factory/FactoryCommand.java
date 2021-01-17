package factory;

import commands.*;

public class FactoryCommand {
    private FactoryCommand(){

    }
    public static AbstractCommand getCommand(String command) {
        if (command.equalsIgnoreCase("GET")) {
            return new ContactReadCommand();
        }
        if (command.equalsIgnoreCase("POST")) {
            return new ContactSaveCommand();
        }
        if (command.equalsIgnoreCase("PUT")) {
            return new ContactUpdateCommand();
        }
        if (command.equalsIgnoreCase("DELETE")) {
            return new ContactDeleteCommand();
        }
        return null;
    }
}
