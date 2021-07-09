package com.aavn.shipping;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.aavn.drivers.DriverService;
import com.aavn.drivers.model.Driver;
import com.aavn.shipping.model.Shipping;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ShippingService {

  private static final Logger LOGGER = Logger.getLogger(ShippingService.class);
  private static final Random RANDOM = new Random();
  private static final Timer SHIPPING_TIMER = new Timer("Shipping timer");
  private final Set<Shipping> shipping_orders = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

  @Inject
  DriverService driverService;

  public Shipping createOrder() {
    List<Driver> avaiableDrivers = getAvaiableDrivers();
    if (avaiableDrivers.isEmpty()) {
      return null;
    }

    Shipping shipping = new Shipping(avaiableDrivers.get(RANDOM.nextInt(avaiableDrivers.size())).getId(),
        this.shipping_orders.size() + 1, 100);
    this.shipping_orders.add(shipping);
    createTimerTask(shipping);
    return shipping;
  }

  private List<Driver> getAvaiableDrivers() {
    Set<Long> driverBusyIds = this.shipping_orders.stream()
        .filter(item -> !item.isFinish())
        .map(Shipping::getDriverId)
        .collect(Collectors.toSet());
        
    return driverService.getAll().stream()
        .filter(item -> !driverBusyIds.contains(item.getId()))
        .collect(Collectors.toList());
  }

  private void createTimerTask(Shipping shipping) {
    TimerTask task = new TimerTask() {
      public void run() {
        shipping.setFinish(true);
        LOGGER.info("Order " + shipping.getOrderId() + " is finished. Driver " + shipping.getDriverId() + " is free now!");
      }
    };
    long shippingTime = (RANDOM.nextInt(20) + 10) * 1000;
    SHIPPING_TIMER.schedule(task, shippingTime);

    LOGGER.info("Driver " + shipping.getDriverId() + " starts delivery order " + shipping.getOrderId() + " in " + shippingTime);
  }
}
