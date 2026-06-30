package com.akib.oms.controller;

import com.akib.oms.dto.CreateOrderRequest;
import com.akib.oms.dto.OrderResponse;
import com.akib.oms.dto.UpdateOrder;
import com.akib.oms.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(createOrderRequest));
    }

    @GetMapping()
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrder();
    }

    @GetMapping("{id}")
    public OrderResponse getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrder updateOrder){
        OrderResponse orderResponse = orderService.updateOrder(updateOrder);
        if(orderResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
