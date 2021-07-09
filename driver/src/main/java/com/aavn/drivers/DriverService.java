package com.aavn.drivers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import com.aavn.drivers.model.Driver;

@ApplicationScoped
public class DriverService {

  private final Set<Driver> drivers = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

  {
    drivers.add(new Driver(1, "Micheal"));
    drivers.add(new Driver(2, "Tom"));
    drivers.add(new Driver(3, "John"));
  }

  public Set<Driver> getAll() {
    return drivers;
  }
}
