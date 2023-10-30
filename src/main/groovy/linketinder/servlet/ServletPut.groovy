package linketinder.servlet

import javax.servlet.http.HttpServletResponse

class ServletPut extends Servlet {
    void methodPut(HttpServletResponse response, Runnable operation) {
        try {
            writeResponse(response)
            operation.run()
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

}
