package com.example.elektrostorage.order;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<PurchaseOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public PurchaseOrder createOrder(@RequestBody PurchaseOrder order) {
        return orderService.createOrder(order);
    }

    @PostMapping("/{orderId}/items")
    public OrderItem addItem(@PathVariable Long orderId,
                             @RequestParam Long componentId,
                             @RequestParam int quantity) {

        return orderService.addItem(orderId, componentId, quantity);
    }

    @PutMapping("/{orderId}/send")
    public PurchaseOrder sendOrder(@PathVariable Long orderId) {
        return orderService.sendOrder(orderId);
    }

    @PutMapping("/{orderId}/receive")
    public PurchaseOrder receiveOrder(@PathVariable Long orderId) {
        return orderService.receiveOrder(orderId);
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItem> getItems(@PathVariable Long orderId) {
        return orderService.getItemsByOrderId(orderId);
    }
}