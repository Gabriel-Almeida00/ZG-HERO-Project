package linketinder.Exception

class ConfigDataBaseException extends Exception {
    ConfigDataBaseException(String message) {
        super(message)
    }

    public ConfigDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
