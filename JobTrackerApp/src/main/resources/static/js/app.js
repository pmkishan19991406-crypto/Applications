const API = "/api/applications";
let editId = null;   // null = adding a new job, otherwise the id being edited
let jobs = [];       // the jobs currently shown in the table

function get(id) {
    return document.getElementById(id);
}

// Load jobs from the server (all jobs, or a search/filter URL) and show them
async function loadApplications(url = API) {
    const response = await fetch(url);
    jobs = await response.json();
    showTable();
    showCounts();
}

// Fill the table
function showTable() {
    let html = "";
    for (const j of jobs) {
        html += `
            <tr>
                <td>${j.jid}</td>
                <td>${j.companyName}</td>
                <td>${j.jobRole}</td>
                <td>${j.location}</td>
                <td>₹${j.salary}</td>
                <td>${j.status}</td>
                <td>${j.appliedDate || ""}</td>
                <td>${j.followUpDate || ""}</td>
                <td>
                    <button onclick="editApplication(${j.jid})">Edit</button>
                    <button onclick="deleteApplication(${j.jid})">Delete</button>
                </td>
            </tr>`;
    }
    get("applicationTable").innerHTML = html;
}

// Dashboard numbers
function countStatus(status) {
    return jobs.filter(j => j.status === status).length;
}

function showCounts() {
    get("totalCount").innerText = jobs.length;
    get("appliedCount").innerText = countStatus("APPLIED");
    get("interviewCount").innerText = countStatus("INTERVIEW");
    get("offerCount").innerText = countStatus("OFFER");
}

// Search by company name
function searchApplications() {
    const company = get("searchCompany").value.trim();
    if (company === "") {
        loadApplications();
    } else {
        loadApplications(API + "/search?companyName=" + encodeURIComponent(company));
    }
}

// Filter by status
function filterByStatus() {
    const status = get("statusFilter").value;
    if (status === "") {
        loadApplications();
    } else {
        loadApplications(API + "/status/" + status);
    }
}

// Add a new job or update an existing one
async function saveApplication() {
    const job = {
        companyName: get("companyName").value,
        jobRole: get("jobRole").value,
        location: get("location").value,
        salary: Number(get("salary").value),
        status: get("applicationStatus").value,
        appliedDate: get("appliedDate").value,
        followUpDate: get("followUpDate").value
    };

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

// Put a job's details into the form so it can be edited
function editApplication(jid) {
    const job = jobs.find(j => j.jid === jid);

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

// Empty the form
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

// Delete a job
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