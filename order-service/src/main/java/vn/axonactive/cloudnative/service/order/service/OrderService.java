package vn.axonactive.cloudnative.service.order.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import vn.axonactive.cloudnative.service.order.dto.OrderDto;

/**
 * @author pnqphong
 */
public interface OrderService {

    /**
     * Create new order
     * 
     * @param orderDto request body for creating the order
     * @return the persisted order with order id
     */
    OrderDto createOrder(OrderDto orderDto);

    /**
     * Get all orders
     * 
     * @return list of orders
     */
    List<OrderDto> getAllOrders();
    
    /*
     * status of response is OK
     */
    boolean isStatusOk(Response reponse);
    
    OrderDto convertToOrderDto(String order) throws JsonProcessingException;
}
