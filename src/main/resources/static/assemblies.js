async function loadAssemblies() {
    const response = await fetch("/assemblies");
    const assemblies = await response.json();

    const table = document.getElementById("assemblyTable");

    table.innerHTML = "";

    assemblies.forEach(assembly => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${assembly.id}</td>
            <td>${assembly.name}</td>
        `;

        table.appendChild(row);
    });
}

async function loadAssemblyItems() {
    const assemblyId = document.getElementById("assemblyId").value;

    const response = await fetch("/assemblies/" + assemblyId + "/items");
    const items = await response.json();

    const table = document.getElementById("assemblyItemTable");

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

loadAssemblies();