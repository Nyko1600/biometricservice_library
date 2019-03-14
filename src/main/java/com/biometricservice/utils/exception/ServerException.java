package com.biometricservice.utils.exception;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ServerException extends RuntimeException {
  private Integer code;
  private int statusCode;
  private Throwable cause;
  private String error;

  public ServerException(String msg) {
    this(msg, 500);
  }

  public ServerException(String msg, int status) {
    this(msg, status, "internal_server_error");
  }

  public ServerException(String msg, Throwable cause) {
    this(msg, 500, "internal_server_error", cause);
  }

  public ServerException(String msg, int status, String error) {
    this(msg, status, error, (Throwable)null);
  }

  public ServerException(String msg, int status, String error, Throwable cause) {
    super(msg, cause);
    this.code = null;
    this.statusCode = status;
    this.error = error;
    this.cause = cause;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public String getError() {
    return this.error;
  }

  public ServerException setCode(Integer code) {
    this.code = code;
    return this;
  }

  public static void throwServerException(String msg) {
    throw new ServerException(msg);
  }

  public static void throwServerException(String msg, Throwable t) {
    throw new ServerException(msg, t);
  }

  public Integer getCode() {
    return this.code;
  }


  private List<String> trace() {
    ArrayList<String> errors = new ArrayList();
    if (this.cause != null) {
      Arrays.asList(this.cause.getStackTrace()).forEach((t) -> {
        errors.add(String.format("%s.%s[%d]", t.getClassName(), t.getMethodName(), t.getLineNumber()));
      });
    }

    return errors;
  }
}