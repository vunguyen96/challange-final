package vn.axonactive.cloudnative.service.order.repository;

import java.util.List;

import vn.axonactive.cloudnative.service.order.entity.Order;

public interface OrderRepository {

    /**
     * Create new order
     * 
     * @param order the prepared order to create
     * @return the persisted order with order id
     */
    Order createOrder(Order order);

    /**
     * Get all orders
     * 
     * @return list of orders
     */
    List<Order> getAllOrders();

}
