package linketinder.utils.servlet

import com.google.gson.Gson

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.stream.Collectors

class ServletUtils {
    private Gson gson = new Gson()

     int pegarIdDaUrl(HttpServletRequest request) {
        String pathInfo = request.getPathInfo()
        String[] pathParts = pathInfo.split("/")
        if (pathParts.length == 2) {
            try {
                return Integer.parseInt(pathParts[1])
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("O valor passado na url deve ser numérico " + e.getMessage())
            }
        }
        return -1
    }

    <T> T parseObjectFromRequest(HttpServletRequest request, Class<T> objectClass) throws IOException {
        request.setCharacterEncoding("UTF-8")
        String requestBody = request.getReader().lines()
                .collect(Collectors.joining(System.lineSeparator()))
        return gson.fromJson(new String(requestBody.getBytes("UTF-8"), "UTF-8"), objectClass)
    }
}
