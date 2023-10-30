package linketinder.servlet


import javax.servlet.http.HttpServletResponse

class ServletResponseUtils {

     void configureResponse(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8")
        response.setContentType("application/json; charset=UTF-8")
    }

     void writeResponse(HttpServletResponse response, String jsonResponse) throws IOException {
        configureResponse(response)
        response.setStatus(HttpServletResponse.SC_OK)
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse)
        }
    }

    void writeErrorResponse(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        response.setStatus(statusCode)
        response.setCharacterEncoding("UTF-8")
        response.setContentType("application/json; charset=UTF-8")

        String errorResponse = "{ \"error\": \"" + errorMessage + "\" }"
        response.getWriter().write(errorResponse)
    }
}
