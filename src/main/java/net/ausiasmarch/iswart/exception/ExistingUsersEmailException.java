package net.ausiasmarch.iswart.exception;

public class ExistingUsersEmailException extends RuntimeException {
    public ExistingUsersEmailException(String mensaje) {
        super(mensaje);
    }
}
