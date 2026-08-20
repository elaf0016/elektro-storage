async function loadComponents() {
    const response = await fetch("/components");
    const components = await response.json();

    const table = document.getElementById("componentTable");

    table.innerHTML = "";

    components.forEach(component => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${component.id}</td>
            <td>${component.internalNumber}</td>
            <td>${component.name}</td>
            <td>${component.externalPartNumber}</td>
            <td>${component.discontinued ? "Discontinued" : "Active"}</td>
            <td> <button onclick="discontinueComponent(${component.id})">
        Discontinue
    </button>
    </td>
        `;

        table.appendChild(row);
    });
}

document.getElementById("componentForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const component = {
        internalNumber: document.getElementById("internalNumber").value,
        name: document.getElementById("name").value,
        externalPartNumber: document.getElementById("externalPartNumber").value,
        discontinued: false,
        supplier: {
            id: document.getElementById("supplierId").value
        }
    };

    await fetch("/components", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(component)
    });

    document.getElementById("componentForm").reset();

    loadComponents();
});
async function discontinueComponent(id) {
    await fetch("/components/" + id + "/discontinue", {
        method: "PUT"
    });

    loadComponents();
}

loadComponents();