package com.aavn.http;

public class MessageRespone {
  
  private String message;
  
  public MessageRespone(String message) {
    this.message = message;
  }

  public static MessageRespone create(String message) {
    return new MessageRespone(message);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
