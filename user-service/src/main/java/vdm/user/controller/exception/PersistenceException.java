package vdm.user.controller.exception;

public class PersistenceException extends RuntimeException {

  public PersistenceException (String message) {
    super(message);
  }
}
