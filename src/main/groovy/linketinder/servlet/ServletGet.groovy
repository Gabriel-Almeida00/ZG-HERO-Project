package linketinder.servlet

import com.google.gson.Gson

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletResponse

class ServletGet extends HttpServlet {
    Gson gson = new Gson()

    void methodGet(HttpServletResponse response, Object data) {
        try {
            response.setCharacterEncoding("UTF-8")
            response.setContentType("application/json; charset=UTF-8")
            response.setStatus(HttpServletResponse.SC_OK)

            String json = gson.toJson(data)
            try (PrintWriter out = response.getWriter()) {
                out.print(json)
            }
        } catch (Exception e) {
            writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    void writeErrorResponse(HttpServletResponse response, int statusCode, String errorMessage) {
        response.setStatus(statusCode)
        response.setCharacterEncoding("UTF-8")
        response.setContentType("application/json; charset=UTF-8")

        String errorResponse = "{ \"error\": \"" + errorMessage + "\" }"
        response.getWriter().write(errorResponse)
    }
}
