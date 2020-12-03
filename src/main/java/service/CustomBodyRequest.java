package service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomBodyRequest {
    public Map<String, String> getBody(HttpServletRequest request) {
        StringBuilder buffer;
        BufferedReader reader;
        String line;
        Map<String, String> params = new HashMap<>();
        try {
            reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                buffer = new StringBuilder(line);
                if (buffer.length()>1)
                buffer.deleteCharAt(buffer.length() - 1);
                String param = buffer.toString();
                param = param.replaceAll("\"", "");
                param = param.replaceAll(" ", "");
                String[] paramSplit = param.split(":");
                if (paramSplit.length > 1)
                    params.put(paramSplit[0], paramSplit[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return params;
    }
}
