package com.example.elektrostorage;

import com.example.elektrostorage.assembly.Assembly;
import com.example.elektrostorage.assembly.AssemblyItem;
import com.example.elektrostorage.assembly.AssemblyItemRepository;
import com.example.elektrostorage.assembly.AssemblyRepository;
import com.example.elektrostorage.component.Component;
import com.example.elektrostorage.component.ComponentRepository;
import com.example.elektrostorage.order.OrderItem;
import com.example.elektrostorage.order.OrderItemRepository;
import com.example.elektrostorage.order.PurchaseOrder;
import com.example.elektrostorage.order.PurchaseOrderRepository;
import com.example.elektrostorage.supplier.Supplier;
import com.example.elektrostorage.supplier.SupplierRepository;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

@org.springframework.stereotype.Component
public class DataInitializer implements CommandLineRunner {

    private final SupplierRepository supplierRepository;
    private final ComponentRepository componentRepository;
    private final AssemblyRepository assemblyRepository;
    private final AssemblyItemRepository assemblyItemRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderItemRepository orderItemRepository;

    public DataInitializer(SupplierRepository supplierRepository,
                           ComponentRepository componentRepository,
                           AssemblyRepository assemblyRepository,
                           AssemblyItemRepository assemblyItemRepository,
                           PurchaseOrderRepository purchaseOrderRepository,
                           OrderItemRepository orderItemRepository) {

        this.supplierRepository = supplierRepository;
        this.componentRepository = componentRepository;
        this.assemblyRepository = assemblyRepository;
        this.assemblyItemRepository = assemblyItemRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void run(String... args) {

        if (supplierRepository.count() > 0) {
            return;
        }

        // Suppliers
        Supplier supplier1 = supplierRepository.save(
                new Supplier("Electro Parts", "Lyngby Hovedgade 10")
        );

        Supplier supplier2 = supplierRepository.save(
                new Supplier("Nordic Components", "Industrivej 5")
        );

        // Components
        Component led = componentRepository.save(
                new Component(1001, "LED 5 mm red", "LED-R5-001", supplier1)
        );

        Component resistor = componentRepository.save(
                new Component(1002, "Resistor 1kOhm", "RES-1K-001", supplier1)
        );

        Component batteryHolder = componentRepository.save(
                new Component(1003, "9V Battery Holder", "BAT-HOLDER-9V", supplier2)
        );

        Component battery = componentRepository.save(
                new Component(1004, "9V Battery", "BAT-9V", supplier2)
        );

        // Assembly
        Assembly assembly = assemblyRepository.save(
                new Assembly("Lysende LED")
        );

        assemblyItemRepository.save(new AssemblyItem(1, assembly, led));
        assemblyItemRepository.save(new AssemblyItem(1, assembly, resistor));
        assemblyItemRepository.save(new AssemblyItem(1, assembly, batteryHolder));
        assemblyItemRepository.save(new AssemblyItem(1, assembly, battery));

        // Draft order
        PurchaseOrder draftOrder = purchaseOrderRepository.save(
                new PurchaseOrder(
                        "TRK-DEMO-001",
                        LocalDate.now().plusDays(5),
                        supplier1
                )
        );

        orderItemRepository.save(
                new OrderItem(20, draftOrder, resistor)
        );

        // Sent and received order
        PurchaseOrder receivedOrder = new PurchaseOrder(
                "TRK-DEMO-002",
                LocalDate.now().plusDays(3),
                supplier2
        );

        receivedOrder.setSentDate(LocalDate.now().minusDays(2));
        receivedOrder.setReceivedDate(LocalDate.now());

        receivedOrder = purchaseOrderRepository.save(receivedOrder);

        orderItemRepository.save(
                new OrderItem(10, receivedOrder, battery)
        );
    }
}