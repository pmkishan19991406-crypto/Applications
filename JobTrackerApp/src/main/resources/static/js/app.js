
console.log("Job Tracker Started");

const $ = id => document.getElementById(id);
const API = "/api/applications";

// 1. Load Applications
async function loadApplications(url = API) {
    try {
        const data = await (await fetch(url)).json();
        displayApplications(data);
        updateDashboard(data);
    } catch (error) {
        console.error(error);
        alert("Failed to load applications");
    }
}

// 2. Display Applications
function displayApplications(data) {
    $("applicationTable").innerHTML = data.map(j => `
        <tr>
            <td>${j.jid}</td>
            <td>${j.companyName}</td>
            <td>${j.jobRole}</td>
            <td>${j.location}</td>
            <td>₹${j.salary}</td>
            <td>${j.status}</td>
            <td>${j.appliedDate ?? ""}</td>
            <td>${j.followUpDate ?? ""}</td>
            <td>
                <button onclick="editApplication(${j.jid})">Edit</button>
                <button onclick="deleteApplication(${j.jid})">Delete</button>
            </td>
        </tr>
    `).join("");
}

// 3. Dashboard
function updateDashboard(data) {
    $("totalCount").innerText = data.length;
    $("appliedCount").innerText =
        data.filter(j => j.status === "APPLIED").length;
    $("interviewCount").innerText =
        data.filter(j => j.status === "INTERVIEW").length;
    $("offerCount").innerText =
        data.filter(j => j.status === "OFFER").length;
}

// 4. Search
function searchApplications() {
    const company = $("searchCompany").value.trim();

    loadApplications(company
        ? API + "/search?companyName=" + encodeURIComponent(company)
        : API);
}

// 5. Filter by Status
function filterByStatus() {
    const status = $("statusFilter").value;
    loadApplications(status ? API + "/status/" + status : API);
}

// 6. Get Form Data
function getFormData() {
    return {
        companyName: $("companyName").value,
        jobRole: $("jobRole").value,
        location: $("location").value,
        salary: Number($("salary").value),
        status: $("applicationStatus").value,
        appliedDate: $("appliedDate").value,
        followUpDate: $("followUpDate").value
    };
}

// 7. Add or Update
async function saveApplication() {
    const jid = $("saveButton").dataset.jid;
    const job = getFormData();

    if (jid) job.jid = Number(jid);

    try {
        const response = await fetch(API, {
            method: jid ? "PUT" : "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(job)
        });

        if (!response.ok) throw new Error("Save failed");

        alert(jid ? "Application updated!" : "Application added!");
        clearForm();
        loadApplications();
    } catch (error) {
        console.error(error);
        alert("Failed to save application");
    }
}

// 8. Edit Application

async function editApplication(jid) {
    try {
        const response = await fetch("/api/applications/" + jid);

        if (!response.ok) {
            throw new Error("HTTP " + response.status);
        }

        const job = await response.json();

        $("companyName").value = job.companyName ?? "";
        $("jobRole").value = job.jobRole ?? "";
        $("location").value = job.location ?? "";
        $("salary").value = job.salary ?? "";
        $("applicationStatus").value = job.status ?? "APPLIED";
        $("appliedDate").value = job.appliedDate ?? "";
        $("followUpDate").value = job.followUpDate ?? "";

        $("saveButton").dataset.jid = jid;
        $("saveButton").innerText = "Update Application";

        window.scrollTo({ top: 300, behavior: "smooth" });

    } catch (error) {
        console.error("Edit error:", error);
        alert("Edit failed: " + error.message);
    }
}


// 9. Clear Form
function clearForm() {
    ["companyName", "jobRole", "location", "salary",
     "appliedDate", "followUpDate"].forEach(id => $(id).value = "");

    $("applicationStatus").value = "APPLIED";
    delete $("saveButton").dataset.jid;
    $("saveButton").innerText = "Add Application";
}

// 10. Delete Application
async function deleteApplication(jid) {
    if (!confirm("Delete this application?")) return;

    try {
        const response = await fetch(API + "/" + jid, {
            method: "DELETE"
        });

        if (!response.ok) throw new Error("Delete failed");

        alert("Application deleted!");
        loadApplications();
    } catch (error) {
        console.error(error);
        alert("Failed to delete application");
    }
}

// Start
loadApplications();
