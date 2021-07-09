package vn.axonactive.cloudnative.service.order.service;

import javax.ws.rs.core.Response;

import vn.axonactive.cloudnative.service.client.payment.WalletClient;
import vn.axonactive.cloudnative.service.order.dto.OrderDto;

/**
 * 
 * @author ttlinh
 *
 */
public interface WalletService {
	
	/**
	 * call payment api and update wallet
	 * @param paymentClient
	 * @param orderDto
	 * @return
	 */
	Response updateWallet(WalletClient paymentClient, OrderDto orderDto);
}
