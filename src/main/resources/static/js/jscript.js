/* =========================
   AUTH PAGE JAVASCRIPT
   Login + Register UI Logic
   ========================= */

/* Toggle Password Show/Hide */
function togglePassword(inputId, icon) {
    const input = document.getElementById(inputId);
	const eyeOpen = `
	       <svg  width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">

	           <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
	           <circle cx="12" cy="12" r="3"/>
			 </svg>`;

	   const eyeClose = `
	       <svg  width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">

	           <path d="M17.94 17.94A10.94 10.94 0 0 1 12 20C5 20 1 12 1 12a21.8 21.8 0 0 1 5.06-5.94"/>
	           <path d="M9.9 4.24A10.94 10.94 0 0 1 12 4c7 0 11 8 11 8a21.8 21.8 0 0 1-4.06 4.94"/>
	           <path d="M1 1l22 22"/>

	       </svg> `;
    if (input.type === "password") {
        input.type = "text";
        icon.innerHTML = eyeClose;
    } else {
        input.type = "password";
        icon.innerHTML = eyeOpen;
    }
}

/* Password Strength Checker */
function checkStrength(password) {
    let strength = 0;

    if (password.length >= 6) strength++;
    if (password.length >= 10) strength++;
    if (/[A-Z]/.test(password)) strength++;
    if (/[0-9]/.test(password)) strength++;
    if (/[^A-Za-z0-9]/.test(password)) strength++;

    const bar = document.getElementById("strengthFill");

    if (!bar) return;

    if (strength <= 1) {
        bar.style.width = "20%";
        bar.style.background = "red";
    } else if (strength <= 3) {
        bar.style.width = "60%";
        bar.style.background = "orange";
    } else {
        bar.style.width = "100%";
        bar.style.background = "green";
    }
}

/* Simple form validation helper */
function showError(message) {
    const box = document.getElementById("errorBox");

    if (!box) return;

    box.innerText = message;
    box.style.display = "block";
}

/* Hide error */
function hideError() {
    const box = document.getElementById("errorBox");

    if (box) {
        box.style.display = "none";
    }
}


/* =========================
   SIDEBAR TOGGLE
   ========================= */
function toggleSidebar() {
    document.getElementById("sidebar").classList.toggle("active");
}

/* =========================
   DARK MODE TOGGLE
   ========================= */
function toggleTheme() {
    document.body.classList.toggle("dark-mode");
}

/* Table search filter */
function filterTable() {
    let input = document.getElementById("searchInput");
    let filter = input.value.toLowerCase();

    let table = document.getElementById("appTable");
    let rows = table.getElementsByTagName("tr");

    for (let i = 1; i < rows.length; i++) {
        let rowText = rows[i].innerText.toLowerCase();

        if (rowText.includes(filter)) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}
/* =========================
   TOGGLE MENU
   ========================= */
function toggleMenu() {
    document.getElementById("navMenu").classList.toggle("active");
}
/* user profile page  with logout*/
function toggleProfileMenu() {

    document
        .getElementById("profileDropdown")
        .classList.toggle("show");
}
/* =========================
		Validation
   ========================= */
/* Email format validation */
function validateEmail() {
    const email = document.getElementById("email").value;
    const msg = document.getElementById("emailMsg");

    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!pattern.test(email)) {
        msg.style.color = "red";
        msg.innerText = "Invalid email format";
        return false;
    }

    msg.style.color = "green";
    msg.innerText = "Valid email";
    return true;
}