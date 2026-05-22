
/* =========================================================
   PROJECT: JobPortal JavaScript Actions
   DESCRIPTION: Handles admin/user actions (CRUD + UI events)
   NOTE: Created with AI assistance (verify before production use)
   Git:kamlesh00-cod
========================================================= */

(function () {
    'use strict';


    /* =========================================================
       INITIALIZATION CONTROLLER
    ========================================================= */
    function init() {
        initFormSubmission();
        deleteJob();
        updateStatusOfUserApplication();
        deleteUser();
		userApplyJob();
        blockOrActiveUser();
        updateUserInfo();
        pictureView();
        changePicture();
		sendOTP();
		verifyOTP();
		userRegistration();
		forgotPass();
		UserResetLoginPass();
    }


    /* =========================================================
       JOB CREATE / UPDATE FORMS
    ========================================================= */
    function initFormSubmission() {

        const postForm = document.getElementById("jobpostForm");
        const updateForm = document.getElementById("jobupdate");

        /* -----------------------------
           POST NEW JOB
        ----------------------------- */
        if (postForm) {
            postForm.addEventListener("submit", function (e) {
                e.preventDefault();

                const formData = new FormData(postForm);

                fetch('/admin/jobs/postjob', {
                    method: 'POST',
                    body: formData
                })
                .then(res => res.json())
                .then(data => {

                    if (data.status === 'success') {
                        alert(data.message);
                        postForm.reset(); // FIXED BUG (was jobForm.reset)
                    } else {
                        alert(data.message);
                    }

                })
                .catch(err => {
                    console.error('Error:', err);
                    alert('Something went wrong!');
                });
            });
        }

        /* -----------------------------
           UPDATE JOB
        ----------------------------- */
        if (updateForm) {
            updateForm.addEventListener("submit", function (e) {
                e.preventDefault();

                const formData = new FormData(updateForm);

                fetch('/admin/jobs/update', {
                    method: 'POST',
                    body: formData
                })
                .then(res => res.json())
                .then(data => {

                    if (data.status === 'success') {
                        alert(data.message);
                        window.location.href = "/admin/form";
                    } else {
                        alert(data.message);
                    }

                })
                .catch(err => {
                    console.error('Error:', err);
                    alert('Something went wrong!');
                });
            });
        }
    }


    /* =========================================================
       DELETE JOB
    ========================================================= */
    function deleteJob() {

        const deleteButtons = document.querySelectorAll(".delete-btn");

        deleteButtons.forEach(button => {

            button.addEventListener("click", function (e) {
                e.preventDefault();

                const id = this.dataset.id;

                if (!confirm("Delete this job?")) return;

                fetch(`/admin/jobs/delete/${id}`, {
                    method: "DELETE"
                })
                .then(res => res.json())
                .then(data => {

                    alert(data.message);

                    if (data.status === "success") {
                        location.reload();
                    }

                })
                .catch(err => {
                    console.error("Error:", err);
                    alert("Something went wrong!");
                });
            });

        });
    }


    /* =========================================================
       APPROVE / REJECT APPLICATIONS
    ========================================================= */
    function updateStatusOfUserApplication() {

        const approved = document.querySelectorAll(".approve-btn");
        const reject = document.querySelectorAll(".reject-btn");

        function handleStatusButtons(buttons) {
            buttons.forEach(button => {

                button.addEventListener("click", function (e) {
                    e.preventDefault();

                    const id = this.dataset.id;
                    const status = this.dataset.status;

                    fetch(`/admin/applicants/select?id=${id}&status=${status}`, {
                        method: 'POST'
                    })
                    .then(res => res.json())
                    .then(data => {

                        alert(data.message);

                        if (data.status === 'success') {
                            location.reload();
                        }

                    })
                    .catch(err => {
                        console.error('Error:', err);
                        alert('Something went wrong!');
                    });

                });

            });
        }

        if (approved) handleStatusButtons(approved);
        if (reject) handleStatusButtons(reject);
    }

    /*==========================================================
					User Apply Jobs
	============================================================*/
	function userApplyJob(){
		const applyJob=document.getElementById("applyJobForm");
		if(!applyJob) return;
		applyJob.addEventListener('submit',function(e){
			e.preventDefault();
			if(!confirm("are you sure?")) return;
			const formData= new FormData(applyJob);
			fetch('/user/jobs/apply',{
				method:'POST',
				body:formData
			})
			.then(res => res.json())
			.then(data => { 
				alert(data.message);
				if(data.status==="success"){
					window.location.href=data.redirectURL;
				}
			})
			.catch(err => {
				console.error('Error:', err);
				alert('Something went wrong!');
				});
		})
	}
    /* =========================================================
       DELETE USER (ADMIN)
    ========================================================= */
    function deleteUser() {

        const userDelete = document.querySelectorAll(".deleteUser-btn");

        userDelete.forEach(button => {

            button.addEventListener("click", function (e) {
                e.preventDefault();

                const id = this.dataset.id;

                if (!confirm("Delete this User?")) return;

                fetch(`/admin/manageusers/delete/${id}`, {
                    method: 'DELETE'
                })
                .then(res => res.json())
                .then(data => {

                    alert(data.message);

                    if (data.status === 'success') {
                        location.reload();
                    }

                })
                .catch(err => {
                    console.error('Error:', err);
                    alert('Something went wrong!');
                });

            });

        });
    }


    /* =========================================================
       BLOCK / ACTIVATE USER
    ========================================================= */
    function blockOrActiveUser() {

        const users = document.querySelectorAll(".status-btn");

        users.forEach(button => {

            button.addEventListener("click", function (e) {
                e.preventDefault();

                const id = this.dataset.id;

                fetch(`/admin/manageusers/updatestatus/${id}`, {
                    method: 'PUT'
                })
                .then(res => res.json())
                .then(data => {

                    if (data.status === 'success') {
                        alert(data.message + " User " + button.innerHTML);
                        location.reload();
                    } else {
                        alert(data.message);
                    }

                })
                .catch(err => {
                    console.error('Error:', err);
                    alert('Something went wrong!');
                });

            });

        });
    }


    /* =========================================================
       UPDATE USER INFO (USER SIDE)
    ========================================================= */
    function updateUserInfo() {

        const form = document.getElementById("updateUserForm");

        if (!form) return;

        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const formData = new FormData(form);

            fetch('/user/userdetail/updateinfo', {
                method: 'PUT',
                body: formData
            })
            .then(res => res.json())
            .then(data => {

                alert(data.message);

                if (data.status === 'success') {
                    location.reload();
                }

            })
            .catch(err => {
                console.error('Error:', err);
                alert('Something went wrong!');
            });

        });
    }


    /* =========================================================
       PROFILE IMAGE PREVIEW (LIGHTBOX)
    ========================================================= */
    function pictureView() {

        const img = document.getElementById("profileImg");

        if (!img) return;

        img.addEventListener("click", function () {

            const overlay = document.createElement("div");

            overlay.style.cssText = `
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.8);
                display: flex;
                align-items: center;
                justify-content: center;
                z-index: 9999;
            `;

            const preview = document.createElement("img");
            preview.src = this.src;

            preview.style.maxWidth = "90%";
            preview.style.maxHeight = "90%";
            preview.style.borderRadius = "10px";

            overlay.appendChild(preview);

            overlay.addEventListener("click", () => overlay.remove());

            document.body.appendChild(overlay);
        });
    }


    /* =========================================================
       PROFILE PICTURE UPLOAD (AUTO PREVIEW + UPLOAD)
    ========================================================= */
    function changePicture() {

        const input = document.getElementById("pictureFile");
        const userId = document.getElementById("userId");
        const msg = document.getElementById("uploadStatus");

        if (!input) return;

        input.addEventListener("change", function () {

            const file = this.files[0];
            if (!file) return;

            if (!confirm("Do you really want to upload this picture?")) {
                this.value = "";
                return;
            }

            msg.innerText = "Uploading...";

            // Preview
            const reader = new FileReader();
            reader.onload = (e) => {
                document.getElementById("profileImg").src = e.target.result;
            };
            reader.readAsDataURL(file);

            // Upload
            const formData = new FormData();
            formData.append("profilePic", file);
            formData.append("id", userId.value);

            fetch("/user/userdetail/updateprofileimg", {
                method: "PUT",
                body: formData
            })
            .then(res => res.json())
            .then(data => {

                alert(data.message);

                if (data.status === "success") {
                    msg.innerText = "Uploaded!";
                } else {
                    msg.innerText = "Upload failed!";
                    input.value = "";
                }

            })
            .catch(err => {
                console.error(err);
                alert("Upload failed");
            });
        });
    }
	
	/* =========================================================
	       				USER REGISTRATION 
	    ========================================================= */
		//----------------------- Send Otp To Email -----------------------
		function sendOTP(){
			
			const otpBtn= document.getElementById("sendOtp");
			if(!otpBtn) return;
			otpBtn.addEventListener('click',function(e){
				e.preventDefault();
				const email = document.getElementById("email").value;
				const msg = document.getElementById("otpMsg");
				fetch(`/sendotp?email=${email}`,{
					method:"POST",
					
					
				})
				.then(res=> res.json())
				.then(data=> {
					if(data.status==="success"){
						alert(data.message);
						
						msg.innerText = "OTP sent (Valid only 2-Minutes)";
						document.getElementById("otpSection").style.display = "block";
						// Disable button copied
						           otpBtn.disabled = true;

						           let timeLeft = 120; // 2 minutes

						           otpBtn.innerText = `Resend OTP in ${timeLeft}s`;

						           const countdown = setInterval(() => {

						               timeLeft--;

						               otpBtn.innerText = `Resend OTP in ${timeLeft}s`;

						               if (timeLeft <= 0) {

						                   clearInterval(countdown);

						                   otpBtn.disabled = false;

						                   otpBtn.innerText = "Send OTP";
						               }

						           }, 1000);
						
					}else{
						msg.innerText = data.message;
						msg.style.color = "red";
					}
				})
				.catch(error=>{
					console.error('Error:', error);
				 	alert('Something went wrong!');

				})
			})
		}
		//------------------- Verify Otp ----------------------
		function verifyOTP(){
					
					const verifyBtn= document.getElementById("verifyOtp");
					if(!verifyBtn) return;
					verifyBtn.addEventListener('click',function(e){
						e.preventDefault();
						const email = document.getElementById("email").value;
						const inputOtp = parseInt(document.getElementById("otpInput").value);
						if (!inputOtp) {
						    alert("Please enter OTP");
						    return;
						}
						const msg = document.getElementById("otpMsg");
						fetch(`/verifyotp?email=${email}&inputOtp=${inputOtp}`,{
							method:"POST",	
						})
						.then(res=> res.json())
						.then(data=> {
							if(data.status==="success"){
								alert(data.message);
								msg.style.color = "green";
								msg.innerText = "Email verified successfully ✔";
									   document.getElementById("email").readOnly = true;
									   const createBtn = document.getElementById("createAccountBtn");
								if(createBtn != null){
										// Enable Create Account button // CREATE ACCOUNT PAGE
								       document.getElementById("createAccountBtn").disabled = false;
									   document.getElementById("sendOtp").style.display = "none";
								}else{
										// CHANGE PASSWORD PAGE
										document.getElementById("sendOtpPass").style.display = "none";
									    document.getElementById("passwordSection").style.display ="block";
								}
							}else{
								alert(data.message);
								msg.style.color = "red";
								msg.innerText = data.message;
								// Keep button disabled
								      document.getElementById("createAccountBtn").disabled = true;
							}
						})
						.catch(error=>{
							console.error('Error:', error);
						 	alert('Something went wrong!');

						});
					})
				}
		//---------------------- Register User -------------------
		function userRegistration() {

			       const regForm = document.getElementById("registerForm");
					if(!regForm){
						return;
					}
			       regForm.addEventListener('submit', function (e) {

			           e.preventDefault();

			           const formData = new FormData(regForm);

			           fetch('/submitform', {
			               method: 'POST',
			               body: formData
			             
			           })
			           .then(response => response.json())
			           .then(data => {

			               if (data.status === 'success') {
			                   alert(data.message);
								window.location.href="/login";
			               } else {
			                   alert(data.message);
			               }

			           })
			           .catch(error => {

			               console.error('Error:', error);
			               alert('Something went wrong!');

			           });
			       });
				
			   }
		/*=========================================
					USER RESET LOGIN
		===========================================*/
		//--------------- Email Verify ----------------
		function forgotPass(){
			
			const otpbtn= document.getElementById("sendOtpPass");
			if(!otpbtn) return;
						otpbtn.addEventListener('click',function(e){
							e.preventDefault();
							const email = document.getElementById("email").value;
							const msg = document.getElementById("otpMsg");
							const formData= new FormData();
							formData.append("email",email)
							fetch('/forgotpass',{
								method:"POST",
								body: formData
								
							})
							.then(res=> res.json())
							.then(data=> {
								if(data.status==="success"){
									alert(data.message);
									
									msg.innerText = "OTP sent (Valid only 2-Minutes)";
									document.getElementById("otpSection").style.display = "block";
									// Disable button copied
									           otpbtn.disabled = true;

									           let timeLeft = 120; // 2 minutes

									           otpbtn.innerText = `Resend OTP in ${timeLeft}s`;

									           const countdown = setInterval(() => {

									               timeLeft--;

									               otpbtn.innerText = `Resend OTP in ${timeLeft}s`;

									               if (timeLeft <= 0) {

									                   clearInterval(countdown);

									                   otpbtn.disabled = false;

									                   otpbtn.innerText = "Send OTP";
									               }

									           }, 1000);
									
								}else{
									msg.innerText = data.message;
									msg.style.color = "red";
								}
							})
							.catch(error=>{
								console.error('Error:', error);
							 	alert('Something went wrong!');

							})
						})
		}
	// ---------------  Reset Login ------------------
	function UserResetLoginPass(){
		const resetPass= document.getElementById("forgotPassForm");
		if(!resetPass) return;
		resetPass.addEventListener('submit',function(e){
			e.preventDefault();
			const email = document.getElementById("email").value;
			const password =document.getElementById("regPassword") ;
			const formData= new FormData();
			formData.append("email",email);
			formData.append("password",password);
			fetch(`/resetpass`,{
				method: "POST",
				body: formData
				
			})
			.then(res=> res.json())
			.then(data=>{
				if(data.status==='success'){
					alert(data.message)
					window.location.href="/login"
				}else{
					alert(data.message);
				}
				
			})
			.catch(error=>{
				console.error('Error: ',error);
				alert("Something went wrong");
			});
		})
	}
    /* =========================================================
       BOOTSTRAP INITIALIZATION
    ========================================================= */

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init);
    } else {
        init();
    }
})();