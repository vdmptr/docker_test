package vdm.post.controller.exception.handler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vdm.post.controller.exception.PersistenceException;
import vdm.post.controller.exception.ValidationException;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(PersistenceException.class)
  public ResponseEntity<String> handlePersistenceException(HttpServletRequest request,
                                                           PersistenceException ex) {
    var response = ex.getMessage();

    return ResponseEntity.status(404).body(response);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> handleValidationException(HttpServletRequest request,
                                                          ValidationException ex) {
    var response = ex.getMessage();

    return ResponseEntity.status(400).body(response);
  }
}

