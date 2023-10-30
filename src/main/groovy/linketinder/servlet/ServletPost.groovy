package linketinder.servlet


import javax.servlet.http.HttpServletResponse

class ServletPost extends Servlet {
    void methodPost(HttpServletResponse response, Runnable operation) {
        try {
            writeResponse(response)
            operation.run()
            response.setStatus(HttpServletResponse.SC_CREATED)

        } catch (Exception e) {
            this.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }
}