// load correct page
window.addEventListener("load", async () => {
    let res = await fetch("http://localhost:8080/checkloginstatus", {
        credentials: "include",
        method: "GET",
    });

    if (res.status === 200) {
        let userObject = await res.json();
        if (userObject.userRole === "manager") {
            window.location.href = "financemanager.html"; //loged in manager to manager page
        }
    } else if (res.status === 401) {
        window.location.href = "index.html";
    }

    populateReimbursmentTable();
});

let logoutBtn = document.querySelector("#logout");
logoutBtn.addEventListener("click", async () => {
    //logout
    let res = await fetch("http://localhost:8080/logout", {
        method: "POST",
        credentials: "include",
    });
    if (res.status === 200) {
        //sucessful logout
        window.location.href = "index.html";
    }
});

async function populateReimbursmentTable() {
    let res = await fetch("http://localhost:8080/ers_reimbursement", {
        credentials: "include",
        method: "GET",
    });

    let tbodyElement = document.querySelector("#reimbursement-table tbody");
    tbodyElement.innerHTML = "";
    let reimbursementArray = await res.json();

    for (let i = 0; i < reimbursementArray.length; i++) {
        let reimbusement = reimbursementArray[i];

        let tr = document.createElement("tr");

        let td2 = document.createElement("td");
        td2.innerText = reimbusement.reimAmount;
        let td10 = document.createElement("td");
        td10.innerText = reimbusement.reimApprover;
        let td9 = document.createElement("td");
        td9.innerText = reimbusement.reimAuthor;
        let td7 = document.createElement("td");
        td7.innerText = reimbusement.reimDescription;
        let td1 = document.createElement("td");
        td1.innerText = reimbusement.reimId;

        let td8 = document.createElement("td");
        let veiwImageButton = document.createElement("button");
        veiwImageButton.innerHTML = "View Receipt";
        td8.appendChild(veiwImageButton);

        veiwImageButton.addEventListener("click", () => {
            let receiptImageModal = document.querySelector("#receipt-image-modal");

            let modalCLoseElement = receiptImageModal.querySelector("button");
            modalCLoseElement.addEventListener("click", () => {
                receiptImageModal.classList.remove("is-active");
            });

            let modalContentElement =
                receiptImageModal.querySelector(".modal-content");
            modalContentElement.innerHTML = "";

            let imageElement = document.createElement("img");
            imageElement.setAttribute(
                "src",
                `http://localhost:8080/ers_reimbursement/${reimbusement.reimId}/image`
            );
            modalContentElement.appendChild(imageElement);

            receiptImageModal.classList.add("is-active");
        });

        let td4 = document.createElement("td");
        td4.innerText = reimbusement.reimResolveTime;
        let td5 = document.createElement("td");
        td5.innerText = reimbusement.reimStatus;
        let td3 = document.createElement("td");
        td3.innerText = reimbusement.reimTime;
        let td6 = document.createElement("td");
        td6.innerText = reimbusement.reimType;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);

        tbodyElement.appendChild(tr);
    }
}

let reimbursementSubmitButton = document.querySelector(
    "#reimbursement-submit-btn"
);
reimbursementSubmitButton.addEventListener("click", submitReimbursement);

async function submitReimbursement() {

    let reimTypeInputElement = document.querySelector("#reimbursmentType");
    let reimAmountInputElement = document.querySelector("#reimbursmentAmount");
    let reimDescriptionInputElement = document.querySelector(
        "#reimbursmentDescription");
    let reimImageInputElement = document.querySelector("#reimbursmentReciept");

    // let reimAuthorInputElement = document.querySelector('#reimAuthor');

    const file = reimImageInputElement.files[0];

    let formData = new FormData();
    formData.append("reimb_type", reimTypeInputElement.value);
    formData.append("reimb_amount", reimAmountInputElement.value);
    formData.append("reimb_description", reimDescriptionInputElement.value);

    formData.append("reimb_receipt", file);

    let res = await fetch("http://localhost:8080/ers_reimbursement", {
        method: "POST",
        credentials: "include",
        body: formData,
    });

    let data = await res.json();

    if (res.status === 201) {
        populateReimbursmentTable(); //refresh table
        reimTypeInputElement.value = " ";
        reimAmountInputElement.value = " ";
        reimDescriptionInputElement.value = " ";
    } else if (res.status === 400) { //error message
        let reimbusementForm = document.querySelector("#reimbursement-submit-form");

        let errorMessage = document.createElement('p');
        errorMessage.innerHTML = data.message;
        errorMessage.style.color = "red";

        reimbusementForm.appendChild(errorMessage);
    }
}
