package linketinder.servlet

import com.google.gson.Gson

import javax.servlet.http.HttpServletResponse

class ServletResponse {
    Gson gson = new Gson()

    void writeResponse(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080")
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")

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

    void methodPost(HttpServletResponse response, Runnable operation) {
        try {
            writeResponse(response)
            operation.run()
            response.setStatus(HttpServletResponse.SC_CREATED)
        } catch (Exception e) {
            this.writeErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage())
        }
    }

    void methodPut(HttpServletResponse response, Runnable operation) {
        try {
            writeResponse(response)
            operation.run()
            response.setStatus(HttpServletResponse.SC_OK)
        } catch (Exception e) {
            this.writeErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, e.getMessage())
        }
    }

    void methodDelete(HttpServletResponse response, Runnable operation){
        try{
            operation.run()
            response.setStatus(HttpServletResponse.SC_NO_CONTENT)
        } catch (Exception e) {
            this.writeErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, e.getMessage())
        }
    }
}
