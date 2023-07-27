package com.kainos.ea.exception;

/**
 * Exception thrown when the application fails to connect to the database.
 */
public class DatabaseConnectionException extends Throwable {
  @Override
  public String getMessage() {
    return "Failed To Connect To Database";
  }
}