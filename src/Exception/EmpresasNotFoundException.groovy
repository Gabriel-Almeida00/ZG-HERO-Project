package Exception

class EmpresasNotFoundException extends RuntimeException {
    EmpresasNotFoundException(String message) {
        super(message)
    }
}
