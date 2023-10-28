package linketinder.utils

import javax.servlet.http.HttpServletRequest

class ServletUtils {

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
}
