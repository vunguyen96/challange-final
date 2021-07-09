package vn.axonactive.cloudnative.service.order.service;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import vn.axonactive.cloudnative.service.client.payment.WalletClient;
import vn.axonactive.cloudnative.service.order.dto.OrderDto;
import vn.axonactive.cloudnative.service.order.dto.OrderItemDto;

@ApplicationScoped
public class PaymentServiceImpl implements WalletService{

	@Override
	public Response updateWallet(WalletClient paymentClient, OrderDto orderDto) {
		int orderCost = caculateCost(orderDto);
    	Response updateReponse = paymentClient.update(orderDto.getUserName(), orderCost);
    	return updateReponse;
	}
	
	private static int caculateCost(OrderDto order) {
		return order.getOrderItems().stream()
				.map(OrderItemDto::getPrice)
				.reduce(0, (subTotal, next) -> subTotal + next);
	}
}
