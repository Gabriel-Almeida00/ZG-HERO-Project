package linketinder.servlet

import javax.servlet.http.HttpServletResponse

class ServletDelete extends Servlet {
    void methodDelete(HttpServletResponse response, Runnable operation){
        try{
            operation.run()
            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

}
