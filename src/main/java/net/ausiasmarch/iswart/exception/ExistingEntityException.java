package net.ausiasmarch.iswart.exception;

public class ExistingEntityException extends RuntimeException {
    public ExistingEntityException(String mensaje) {
        super(mensaje);
    }
}
