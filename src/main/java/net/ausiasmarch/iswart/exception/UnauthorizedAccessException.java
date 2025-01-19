package net.ausiasmarch.iswart.exception;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String mensaje) {
        super(mensaje);
    }
}
