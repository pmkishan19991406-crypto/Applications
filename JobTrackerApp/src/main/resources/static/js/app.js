const API = "/api/applications";
let editId = null;   // null = adding a new job, a number = editing that job

function get(id) {
    return document.getElementById(id);
}

// 1. Load all applications and show them
async function loadApplications(url) {
    if (!url) {
        url = API;
    }
    const response = await fetch(url);
    const data = await response.json();
    showTable(data);
    showCounts(data);
}

// 2. Put the rows in the table
function showTable(data) {
    let html = "";
    for (const j of data) {
        html += "<tr>";
        html += "<td>" + j.jid + "</td>";
        html += "<td>" + j.companyName + "</td>";
        html += "<td>" + j.jobRole + "</td>";
        html += "<td>" + j.location + "</td>";
        html += "<td>₹" + j.salary + "</td>";
        html += "<td>" + j.status + "</td>";
        html += "<td>" + (j.appliedDate || "") + "</td>";
        html += "<td>" + (j.followUpDate || "") + "</td>";
        html += "<td>";
        html += "<button onclick='editApplication(" + j.jid + ")'>Edit</button>";
        html += "<button onclick='deleteApplication(" + j.jid + ")'>Delete</button>";
        html += "</td>";
        html += "</tr>";
    }
    get("applicationTable").innerHTML = html;
}

// 3. Dashboard numbers
function showCounts(data) {
    get("totalCount").innerText = data.length;
    get("appliedCount").innerText = data.filter(j => j.status === "APPLIED").length;
    get("interviewCount").innerText = data.filter(j => j.status === "INTERVIEW").length;
    get("offerCount").innerText = data.filter(j => j.status === "OFFER").length;
}

// 4. Search by company
function searchApplications() {
    const company = get("searchCompany").value.trim();
    if (company === "") {
        loadApplications(API);
    } else {
        loadApplications(API + "/search?companyName=" + encodeURIComponent(company));
    }
}

// 5. Filter by status
function filterByStatus() {
    const status = get("statusFilter").value;
    if (status === "") {
        loadApplications(API);
    } else {
        loadApplications(API + "/status/" + status);
    }
}

// 6. Read the form
function getFormData() {
    return {
        companyName: get("companyName").value,
        jobRole: get("jobRole").value,
        location: get("location").value,
        salary: Number(get("salary").value),
        status: get("applicationStatus").value,
        appliedDate: get("appliedDate").value,
        followUpDate: get("followUpDate").value
    };
}

// 7. Add or update
async function saveApplication() {
    const job = getFormData();
    let method = "POST";

    if (editId !== null) {
        method = "PUT";
        job.jid = editId;
    }

    const response = await fetch(API, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(job)
    });

    if (response.ok) {
        alert("Saved!");
        clearForm();
        loadApplications();
    } else {
        alert("Save failed");
    }
}

// 8. Fill the form for editing
async function editApplication(jid) {
    const response = await fetch(API + "/" + jid);
    const job = await response.json();

    get("companyName").value = job.companyName;
    get("jobRole").value = job.jobRole;
    get("location").value = job.location;
    get("salary").value = job.salary;
    get("applicationStatus").value = job.status;
    get("appliedDate").value = job.appliedDate || "";
    get("followUpDate").value = job.followUpDate || "";

    editId = jid;
    get("saveButton").innerText = "Update Application";
}

// 9. Empty the form
function clearForm() {
    get("companyName").value = "";
    get("jobRole").value = "";
    get("location").value = "";
    get("salary").value = "";
    get("applicationStatus").value = "APPLIED";
    get("appliedDate").value = "";
    get("followUpDate").value = "";

    editId = null;
    get("saveButton").innerText = "Add Application";
}

// 10. Delete
async function deleteApplication(jid) {
    if (!confirm("Delete this application?")) {
        return;
    }
    const response = await fetch(API + "/" + jid, { method: "DELETE" });

    if (response.ok) {
        loadApplications();
    } else {
        alert("Delete failed");
    }
}

// Start
loadApplications();