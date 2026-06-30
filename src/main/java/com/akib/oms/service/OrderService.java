package com.akib.oms.service;

import com.akib.oms.dto.CreateOrderRequest;
import com.akib.oms.dto.OrderResponse;
import com.akib.oms.dto.UpdateOrder;
import com.akib.oms.entity.OrderEntity;
import com.akib.oms.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse createOrder(CreateOrderRequest orderRequest){

        OrderEntity savedEntity = orderRepository.save(this.mapToEntity(orderRequest));
        
        OrderResponse response = new OrderResponse();
        response.setId(savedEntity.getId());
        response.setProductName(savedEntity.getProductName());
        response.setQuantity(savedEntity.getQuantity());
        response.setPrice(savedEntity.getPrice());

        return response;
    }

    public List<OrderResponse> getAllOrder() {
        List<OrderEntity> orderEntities = orderRepository.findAll();

        return orderEntities.stream().
                map(this::convertToResponse)
                .toList();
    }

    private OrderResponse convertToResponse(OrderEntity entity) {
        OrderResponse response = new OrderResponse();
        response.setId(entity.getId());
        response.setProductName(entity.getProductName());
        response.setQuantity(entity.getQuantity());
        response.setPrice(entity.getPrice());
        return response;
    }

    //Map Request to Entity.
    private OrderEntity mapToEntity(CreateOrderRequest orderRequest){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProductName(orderRequest.getProductName());
        orderEntity.setQuantity(orderRequest.getQuantity());
        orderEntity.setPrice(orderRequest.getPrice());
        return orderEntity;
    }

    public OrderResponse getOrderById(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        OrderEntity orderEntities = orderEntity.get();
        return this.convertToResponse(orderEntities);
    }

    public OrderResponse updateOrder(UpdateOrder updateOrder) {
        return orderRepository.findById(updateOrder.getId())
                .map(order -> {
                    order.setProductName(updateOrder.getProductName());
                    order.setPrice(updateOrder.getPrice());
                    order.setQuantity(updateOrder.getQuantity());

                    orderRepository.save(order);
                    return this.convertToResponse(order);
                })
                // If the order wasn't found, return a fallback or throw an exception
                .orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
