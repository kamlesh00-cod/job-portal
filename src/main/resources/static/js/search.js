/* =========================================================
   PROJECT: JobPortal JavaScript Actions
   DESCRIPTION: Handles admin/user actions (CRUD + UI events)
   NOTE: Created with AI assistance (verify before production use)
   Git:kamlesh00-cod
========================================================= */
/**
 * Admin Search & Job Search Module
 * ---------------------------------
 * Handles:
 * 1. User search via AJAX (Enter key trigger)
 * 2. Job table client-side filtering
 */
(function () {
    'use strict';

    /**
     * Initializes all features when DOM is ready
     */
    function init() {
        searchUser();
        jobSearch();
    }

    /**
     * USER SEARCH (AJAX + Enter key trigger)
     * ---------------------------------------
     * - Listens to Enter key in search input
     * - Sends request to backend (/admin/searchuser)
     * - Updates user table dynamically
     */
    function searchUser() {

        const search = document.getElementById("searchUser");
        if (!search) return;

        search.addEventListener('keyup', function (e) {

            // Trigger search only on Enter key
            if (e.key === "Enter") {

                e.preventDefault();

                const input = this.value;

                const formData = new FormData();
                formData.append("name", input);

                fetch('/admin/searchuser', {
                    method: "POST",
                    body: formData
                })
                .then(res => res.json())
                .then(data => {

                    const tableBody = document.getElementById("userTableBody");

                    // Clear old rows before inserting new ones
                    tableBody.innerHTML = "";

                    if (data.status === 'success') {

                        document.getElementById("msgP").innerText = data.message;

                        data.searchResult.forEach(user => {

                            // Decide button based on user status
                            let statusButton = "";

                            if (user.status === "ACTIVE") {

                                statusButton =
                                    `<button class="status-btn" data-id="${user.id}">
                                        Block
                                     </button>`;

                            } else {

                                statusButton =
                                    `<button class="status-btn" data-id="${user.id}">
                                        Activate
                                     </button>`;
                            }

                            // Build table row
                            const row = `
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.role}</td>
                                    <td>${user.status}</td>
                                    <td>
                                        ${statusButton}
                                        <button class="deleteUser-btn" data-id="${user.id}">
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            `;

                            tableBody.innerHTML += row;
                        });


                    } else {

                        document.getElementById("msgP").innerText = data.message;

                        tableBody.innerHTML = `
                            <tr>
                                <td colspan="6">No Users Found</td>
                            </tr>
                        `;
                    }

                })
                .catch(error => {
                    console.error('ERROR:', error);
                    alert("Something went wrong");
                });
            }
        });
    }

    /**
     * JOB SEARCH (CLIENT-SIDE FILTERING)
     * -----------------------------------
     * - Filters table rows without server request
     * - Searches in Title, Company, Location columns
     */
    function jobSearch() {

        const searchInput = document.getElementById("jobSearch");
        const table = document.getElementById("jobTable");

        if (!searchInput || !table) return;

        searchInput.addEventListener("keyup", function () {

            const value = this.value.toLowerCase();

            const rows = table.querySelectorAll("tbody tr");

            rows.forEach(function (row) {

                const title = row.cells[0].textContent.toLowerCase();
                const company = row.cells[1].textContent.toLowerCase();
                const location = row.cells[2].textContent.toLowerCase();

                if (
                    title.includes(value) ||
                    company.includes(value) ||
                    location.includes(value)
                ) {
                    row.style.display = "";
                } else {
                    row.style.display = "none";
                }
            });
        });
    }

    /**
     * Safe DOM initialization
     * Ensures script runs after page load
     */
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init);
    } else {
        init();
    }

})();