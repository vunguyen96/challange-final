package vn.axonactive.cloudnative.service.order.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import vn.axonactive.cloudnative.service.order.entity.Order;

@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {

    private static Set<Order> mockPersistedOrders = Collections.newSetFromMap(new HashMap<>());

    @Override
    public Order createOrder(Order orderEntity) {
        // TODO Setup data source & persist the order
        if (orderEntity.getOrderId() == null) {
            orderEntity.setOrderId(UUID.randomUUID());
        }
        mockPersistedOrders.add(orderEntity);
        return orderEntity;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(mockPersistedOrders);
    }

}
