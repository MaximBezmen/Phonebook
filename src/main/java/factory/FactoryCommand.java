package factory;

import commands.*;

public class FactoryCommand {
    private FactoryCommand(){

    }
    public static AbstractCommand getCommand(String command) {
        if (command.equalsIgnoreCase("ContactReadCommand")) {
            return new ContactReadCommand();
        }
        if (command.equalsIgnoreCase("ContactSaveCommand")) {
            return new ContactSaveCommand();
        }
        if (command.equalsIgnoreCase("ContactUpdateCommand")) {
            return new ContactUpdateCommand();
        }
        if (command.equalsIgnoreCase("ContactDeleteCommand")) {
            return new ContactDeleteCommand();
        }
        return null;
    }
}
