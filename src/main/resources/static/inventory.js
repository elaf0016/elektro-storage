async function loadInventory() {
    const response = await fetch("/inventory");
    const items = await response.json();

    const table = document.getElementById("inventoryTable");

    table.innerHTML = "";

    items.forEach(item => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${item.component.name}</td>
            <td>${item.quantity}</td>
            <td>${item.purchaseOrder.id}</td>
            <td>${item.purchaseOrder.receivedDate}</td>
        `;

        table.appendChild(row);
    });
}

loadInventory();
async function countComponent() {
    const componentId = document.getElementById("componentId").value;
    const quantity = document.getElementById("quantity").value;
    const countedBy = document.getElementById("countedBy").value;

    await fetch("/inventory/count?componentId=" + componentId
        + "&quantity=" + quantity
        + "&countedBy=" + countedBy, {
        method: "POST"
    });
}