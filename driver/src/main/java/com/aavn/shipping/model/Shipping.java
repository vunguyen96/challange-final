package com.aavn.shipping.model;

public class Shipping {

  private long driverId;
  private long orderId;
  private long price;
  private boolean isFinish;

  public Shipping(long driverId, long orderId, long price) {
    this.driverId = driverId;
    this.orderId = orderId;
    this.price = price;
  }

  public long getDriverId() {
    return driverId;
  }

  public void setDriverId(long driverId) {
    this.driverId = driverId;
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public boolean isFinish() {
    return isFinish;
  }

  public void setFinish(boolean isFinish) {
    this.isFinish = isFinish;
  }

}
