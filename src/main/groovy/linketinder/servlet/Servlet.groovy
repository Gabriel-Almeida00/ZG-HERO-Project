package linketinder.servlet

import javax.servlet.http.HttpServletResponse

class Servlet {

    void writeResponse(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8")
        response.setContentType("application/json; charset=UTF-8")
    }

    void writeErrorResponse(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        response.setStatus(statusCode)
        response.setCharacterEncoding("UTF-8")
        response.setContentType("application/json; charset=UTF-8")

        String errorResponse = "{ \"error\": \"" + errorMessage + "\" }"
        response.getWriter().write(errorResponse)
    }
}
