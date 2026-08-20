package com.example.elektrostorage.order;

import com.example.elektrostorage.component.Component;
import com.example.elektrostorage.component.ComponentRepository;
import com.example.elektrostorage.supplier.Supplier;
import com.example.elektrostorage.supplier.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void cannotAddItemToSentOrder() throws Exception {

        Supplier supplier = supplierRepository.save(
                new Supplier("Test Supplier", "Test Address")
        );

        Component component = componentRepository.save(
                new Component(9001, "Test Component", "TEST-9001", supplier)
        );

        PurchaseOrder order = new PurchaseOrder(
                "TEST-ORDER",
                LocalDate.now().plusDays(5),
                supplier
        );

        order.setSentDate(LocalDate.now());

        order = purchaseOrderRepository.save(order);

        mockMvc.perform(post("/orders/" + order.getId() + "/items")
                        .param("componentId", component.getId().toString())
                        .param("quantity", "10"))
                .andExpect(status().isBadRequest());
    }
}