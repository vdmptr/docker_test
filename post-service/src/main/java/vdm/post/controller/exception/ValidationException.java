package vdm.post.controller.exception;

public class ValidationException extends RuntimeException {

  public ValidationException(String message) {
    super(message);
  }
}
