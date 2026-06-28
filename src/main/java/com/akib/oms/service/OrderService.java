package com.akib.oms.service;

import com.akib.oms.dto.CreateOrderRequest;
import com.akib.oms.dto.OrderResponse;
import com.akib.oms.entity.OrderEntity;
import com.akib.oms.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(CreateOrderRequest orderRequest){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProductName(orderRequest.getProductName());
        orderEntity.setQuantity(orderRequest.getQuantity());
        orderEntity.setPrice(orderRequest.getPrice());
        OrderEntity savedEntity = orderRepository.save(orderEntity);

        OrderResponse response = new OrderResponse();
        response.setId(savedEntity.getId());
        response.setProductName(savedEntity.getProductName());
        response.setQuantity(savedEntity.getQuantity());
        response.setPrice(savedEntity.getPrice());

        return response;
    }
}
