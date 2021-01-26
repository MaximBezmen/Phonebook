package service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class CustomBodyJson {
    public static String getBody(HttpServletRequest request) {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = request.getReader();
            String body = null;
            while ((body = reader.readLine()) != null) {
                stringBuilder.append(body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
