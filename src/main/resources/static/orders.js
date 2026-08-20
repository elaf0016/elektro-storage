async function loadOrders() {
    const response = await fetch("/orders");
    const orders = await response.json();

    const table = document.getElementById("orderTable");

    table.innerHTML = "";

    orders.forEach(order => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${order.id}</td>
            <td>${order.trackingCode}</td>
            <td>${order.expectedDeliveryDate}</td>
            <td>${order.sentDate}</td>
            <td>${order.receivedDate}</td>
            <td>${order.supplier ? order.supplier.name : ""}</td>
        `;

        table.appendChild(row);
    });
}

document.getElementById("orderForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const order = {
        trackingCode: document.getElementById("trackingCode").value,
        expectedDeliveryDate: document.getElementById("expectedDeliveryDate").value,
        supplier: {
            id: document.getElementById("supplierId").value
        }
    };

    await fetch("/orders", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(order)
    });

    document.getElementById("orderForm").reset();

    loadOrders();
});

async function loadOrderItems() {
    const orderId = document.getElementById("orderId").value;

    const response = await fetch("/orders/" + orderId + "/items");
    const items = await response.json();

    const table = document.getElementById("orderItemTable");

    table.innerHTML = "";

    items.forEach(item => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${item.component.name}</td>
            <td>${item.quantity}</td>
        `;

        table.appendChild(row);
    });
}

async function addOrderItem() {
    const orderId = document.getElementById("addOrderId").value;
    const componentId = document.getElementById("componentId").value;
    const quantity = document.getElementById("quantity").value;

    await fetch("/orders/" + orderId + "/items?componentId=" + componentId + "&quantity=" + quantity, {
        method: "POST"
    });
}

async function sendOrder() {
    const orderId = document.getElementById("sendOrderId").value;

    await fetch("/orders/" + orderId + "/send", {
        method: "PUT"
    });

    loadOrders();
}

loadOrders();
async function receiveOrder() {
    const orderId = document.getElementById("receiveOrderId").value;

    await fetch("/orders/" + orderId + "/receive", {
        method: "PUT"
    });

    loadOrders();
}