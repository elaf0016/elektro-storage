package com.example.elektrostorage.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByPurchaseOrderReceivedDateIsNotNull();
    List<OrderItem> findByPurchaseOrderId(Long orderId);

}
