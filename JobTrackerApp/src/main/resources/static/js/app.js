console.log("Job Application Tracker Started");


// ==============================
// Load All Applications
// ==============================

function loadApplications() {

    fetch("/api/applications")
        .then(response => response.json())
        .then(data => {

            displayApplications(data);
            updateDashboard(data);

        })
        .catch(error => {

            console.error("Error loading applications:", error);

        });
}


// ==============================
// Display Applications
// ==============================

function displayApplications(data) {

    const table =
        document.getElementById("applicationTable");

    table.innerHTML = "";

    data.forEach(job => {

        const row = `
            <tr>

                <td>${job.jid}</td>

                <td>${job.companyName}</td>

                <td>${job.jobRole}</td>

                <td>${job.location}</td>

                <td>₹${job.salary}</td>

                <td>${job.status}</td>

                <td>${job.appliedDate}</td>

                <td>${job.followUpDate}</td>

                <td>

                    <button onclick="editApplication(${job.jid})">
                        Edit
                    </button>

                    <button onclick="deleteApplication(${job.jid})">
                        Delete
                    </button>

                </td>

            </tr>
        `;

        table.innerHTML += row;
    });
}


// ==============================
// Dashboard Counts
// ==============================

function updateDashboard(data) {

    document.getElementById("totalCount").innerText =
        data.length;

    document.getElementById("appliedCount").innerText =
        data.filter(job => job.status === "APPLIED").length;

    document.getElementById("interviewCount").innerText =
        data.filter(job => job.status === "INTERVIEW").length;

    document.getElementById("offerCount").innerText =
        data.filter(job => job.status === "OFFER").length;
}


// ==============================
// Search by Company
// ==============================

function searchApplications() {

    const company =
        document.getElementById("searchCompany").value.trim();

    if (company === "") {

        loadApplications();
        return;
    }

    fetch("/api/applications/search?companyName=" + company)
        .then(response => response.json())
        .then(data => {

            displayApplications(data);
            updateDashboard(data);

        })
        .catch(error => {

            console.error("Error searching applications:", error);

        });
}


// ==============================
// Filter by Status
// ==============================

function filterByStatus() {

    const status =
        document.getElementById("statusFilter").value;

    if (status === "") {

        loadApplications();
        return;
    }

    fetch("/api/applications/status/" + status)
        .then(response => response.json())
        .then(data => {

            displayApplications(data);
            updateDashboard(data);

        })
        .catch(error => {

            console.error("Error filtering applications:", error);

        });
}


// ==============================
// Add Job Application
// ==============================

function addApplication() {

    const jobApplication = {

        companyName:
            document.getElementById("companyName").value,

        jobRole:
            document.getElementById("jobRole").value,

        location:
            document.getElementById("location").value,

        salary:
            Number(document.getElementById("salary").value),

        status:
            document.getElementById("applicationStatus").value,

        appliedDate:
            document.getElementById("appliedDate").value,

        followUpDate:
            document.getElementById("followUpDate").value
    };


    fetch("/api/applications", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(jobApplication)

    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to add application");
        }

        return response.json();

    })
    .then(() => {

        alert("Job application added successfully!");

        clearForm();

        loadApplications();

    })
    .catch(error => {

        console.error("Error adding application:", error);

        alert("Failed to add application");

    });
}


// ==============================
// Clear Form
// ==============================

function clearForm() {

    document.getElementById("companyName").value = "";

    document.getElementById("jobRole").value = "";

    document.getElementById("location").value = "";

    document.getElementById("salary").value = "";

    document.getElementById("applicationStatus").value =
        "APPLIED";

    document.getElementById("appliedDate").value = "";

    document.getElementById("followUpDate").value = "";
}


// ==============================
// Delete Job Application
// ==============================

function deleteApplication(jid) {

    const confirmDelete =
        confirm("Are you sure you want to delete this application?");

    if (!confirmDelete) {
        return;
    }

    fetch("/api/applications/" + jid, {

        method: "DELETE"

    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to delete application");
        }

        return response.text();

    })
    .then(() => {

        alert("Job application deleted successfully!");

        loadApplications();

    })
    .catch(error => {

        console.error("Error deleting application:", error);

        alert("Failed to delete application");

    });
}


// ==============================
// Edit Job Application
// ==============================

function editApplication(jid) {

    fetch("/api/applications/" + jid)

        .then(response => {

            if (!response.ok) {
                throw new Error("Application not found");
            }

            return response.json();

        })

        .then(job => {

            document.getElementById("companyName").value =
                job.companyName;

            document.getElementById("jobRole").value =
                job.jobRole;

            document.getElementById("location").value =
                job.location;

            document.getElementById("salary").value =
                job.salary;

            document.getElementById("applicationStatus").value =
                job.status;

            document.getElementById("appliedDate").value =
                job.appliedDate;

            document.getElementById("followUpDate").value =
                job.followUpDate;


            // Store ID for update

            document.getElementById("saveButton").dataset.jid =
                job.jid;


            // Change button text

            document.getElementById("saveButton").innerText =
                "Update Application";


            // Change button action

            document.getElementById("saveButton").onclick =
                updateApplication;


            // Scroll to form

            window.scrollTo({

                top: 300,

                behavior: "smooth"

            });

        })

        .catch(error => {

            console.error("Error loading application:", error);

            alert("Failed to load application");

        });
}


// ==============================
// Update Job Application
// ==============================

function updateApplication() {

    const button =
        document.getElementById("saveButton");


    const jobApplication = {

        jid:
            Number(button.dataset.jid),

        companyName:
            document.getElementById("companyName").value,

        jobRole:
            document.getElementById("jobRole").value,

        location:
            document.getElementById("location").value,

        salary:
            Number(document.getElementById("salary").value),

        status:
            document.getElementById("applicationStatus").value,

        appliedDate:
            document.getElementById("appliedDate").value,

        followUpDate:
            document.getElementById("followUpDate").value
    };


    fetch("/api/applications", {

        method: "PUT",

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify(jobApplication)

    })

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Failed to update application"
            );

        }

        return response.json();

    })

    .then(() => {

        alert(
            "Job application updated successfully!"
        );

        clearForm();

        resetSaveButton();

        loadApplications();

    })

    .catch(error => {

        console.error(
            "Error updating application:",
            error
        );

        alert(
            "Failed to update application"
        );

    });
}


// ==============================
// Reset Save Button
// ==============================

function resetSaveButton() {

    const button =
        document.getElementById("saveButton");


    button.innerText =
        "Add Application";


    button.onclick =
        addApplication;


    delete button.dataset.jid;
}



// Start Application

loadApplications();