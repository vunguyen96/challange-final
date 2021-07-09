package vn.axonactive.cloudnative.service.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.axonactive.cloudnative.service.order.dto.OrderDto;
import vn.axonactive.cloudnative.service.order.dto.OrderItemDto;
import vn.axonactive.cloudnative.service.order.entity.Order;
import vn.axonactive.cloudnative.service.order.entity.OrderItem;
import vn.axonactive.cloudnative.service.order.repository.OrderRepository;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderRepository orderRepository;
    
    @Inject
    ObjectMapper objectMapper;
    
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order newOrder = new Order();
        newOrder.setOrderItems(new ArrayList<>());
        if (CollectionUtils.isNotEmpty(orderDto.getOrderItems())) {
            for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
                OrderItem newOrderItem = new OrderItem();
                newOrderItem.setId(orderItemDto.getId());
                newOrderItem.setName(orderItemDto.getName());
                newOrderItem.setPrice(orderItemDto.getPrice());
                newOrderItem.setQuantity(orderItemDto.getQuantity());
                newOrder.getOrderItems().add(newOrderItem);
            }
        }
        newOrder.setDriverId(orderDto.getOrderId());
        newOrder.setUserId(orderDto.getUserId());
        newOrder.setDriverId(orderDto.getDriverId());
        newOrder.setUserName(orderDto.getUserName());
        newOrder.setUserId(orderDto.getUserId());
        Order persistedOrder = orderRepository.createOrder(newOrder);
        orderDto.setOrderId(persistedOrder.getOrderId().toString());
        return orderDto;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderRepository.getAllOrders();
        if (CollectionUtils.isNotEmpty(orders)) {
            for (Order order : orders) {
                OrderDto newDto = new OrderDto();
                newDto.setOrderItems(new ArrayList<>());
                if (CollectionUtils.isNotEmpty(order.getOrderItems())) {
                    for (OrderItem orderItem : order.getOrderItems()) {
                        OrderItemDto newOrderItemDto = new OrderItemDto();
                        newOrderItemDto.setId(orderItem.getId());
                        newOrderItemDto.setName(orderItem.getName());
                        newOrderItemDto.setPrice(orderItem.getPrice());
                        newOrderItemDto.setQuantity(orderItem.getQuantity());
                        newDto.getOrderItems().add(newOrderItemDto);
                    }
                }
                newDto.setDriverId(order.getDriverId());
                newDto.setUserId(order.getUserId());
                newDto.setOrderId(order.getOrderId().toString());
                newDto.setUserName(order.getUserName());
                orderDtos.add(newDto);
            }
        }
        return orderDtos;
    }

	@Override
	public boolean isStatusOk(Response reponse) {
		return reponse.getStatus() == Status.OK.getStatusCode();
	}
	
	@Override
	public OrderDto convertToOrderDto(String order) throws JsonProcessingException{
		OrderDto orderDto = objectMapper.readValue(order, OrderDto.class);
		return orderDto;
	}
}
