package linketinder.servlet

import com.google.gson.Gson

import javax.servlet.http.HttpServletResponse

class ServletGet extends Servlet  {
    Gson gson = new Gson()

    void methodGet(HttpServletResponse response, Object data) {
        try {
            writeResponse(response)
            response.setStatus(HttpServletResponse.SC_OK)

            String json = gson.toJson(data)
            try (PrintWriter out = response.getWriter()) {
                out.print(json)
            }
        } catch (Exception e) {
            writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

}
