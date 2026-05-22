
/* Fake OTP storage */
let generatedOtp = null;



/* Generate fake OTP */
function sendOtp() {
    if (!validateEmail()) return;

    generatedOtp = Math.floor(100000 + Math.random() * 900000);

    alert("OTP sent (demo): " + generatedOtp);

    
}

/* Verify OTP */
function verifyOtp() {
    const inputOtp = document.getElementById("otpInput").value;
    const msg = document.getElementById("otpMsg");

    if (inputOtp == generatedOtp) {
        msg.style.color = "green";
        msg.innerText = "Email verified successfully ✔";
    } else {
        msg.style.color = "red";
        msg.innerText = "Invalid OTP ❌";
    }
}