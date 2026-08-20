package com.example.elektrostorage.order;

import com.example.elektrostorage.component.Component;
import com.example.elektrostorage.component.ComponentRepository;
import com.example.elektrostorage.exception.BadRequestException;
import com.example.elektrostorage.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ComponentRepository componentRepository;

    public OrderService(PurchaseOrderRepository purchaseOrderRepository,
                        OrderItemRepository orderItemRepository,
                        ComponentRepository componentRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.componentRepository = componentRepository;
    }

    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder createOrder(PurchaseOrder order) {
        return purchaseOrderRepository.save(order);
    }

    public OrderItem addItem(Long orderId, Long componentId, int quantity) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getSentDate() != null) {
            throw new BadRequestException("Cannot add items to a sent order");
        }

        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found"));

        if (component.isDiscontinued()) {
            throw new BadRequestException("Discontinued component cannot be ordered");
        }

        OrderItem item = new OrderItem(quantity, order, component);

        return orderItemRepository.save(item);
    }

    public PurchaseOrder sendOrder(Long orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setSentDate(LocalDate.now());

        return purchaseOrderRepository.save(order);
    }

    public PurchaseOrder receiveOrder(Long orderId) {

        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setReceivedDate(LocalDate.now());

        return purchaseOrderRepository.save(order);
    }

    public List<OrderItem> getItemsByOrderId(Long orderId) {
        return orderItemRepository.findByPurchaseOrderId(orderId);
    }
}