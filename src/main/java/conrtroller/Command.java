package conrtroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    void execute(String methode, HttpServletRequest request, HttpServletResponse response) throws IOException;
}