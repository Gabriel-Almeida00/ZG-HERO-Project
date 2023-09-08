package linketinder.Exception

import java.sql.SQLException

class DataBaseException extends RuntimeException {
    DataBaseException(String message,  SQLException throwables) {
        super(message)
    }

}
