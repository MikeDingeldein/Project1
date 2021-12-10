// load correct page
window.addEventListener('load', async  () => {

    let res = await fetch('http://localhost:8080/checkloginstatus', {
        credentials: 'include',
        method: 'GET'
    });

    if (res.status === 200) {
        let userObject = await res.json();
        if (userObject.userRole === 'employee') {
            window.location.href = 'employee.html'; //loged in employee to employee page
        }
        } else if (res.status === 401) {
            window.location.href = 'index.html';
        }

        populateReimbursmentTable();
    });

    let logoutBtn = document.querySelector('#logout');
    logoutBtn.addEventListener('click', async () => { //logout
        let res = await fetch('http://localhost:8080/logout', {
            'method': 'POST',
            'credentials': 'include'
        });
        if (res.status === 200) { //sucessful logout
            window.location.href = 'index.html';
        }
    })

    async function populateReimbursmentTable() {
        let res = await fetch('http://localhost:8080/ers_reimbursement', {
            credentials: 'include',
            method: 'GET'
            
        });
    
        let tbodyElement = document.querySelector('#reimbursement-table tbody');
        tbodyElement.innerHTML = '';
        let reimbursementArray = await res.json();
    
        
        for (let i = 0; i < reimbursementArray.length; i++) {
            let reimbusement = reimbursementArray[i];
             
            let tr = document.createElement('tr');
    
            let td2 = document.createElement('td');
            td2.innerText = reimbusement.reimAmount;

            let td10 = document.createElement('td');
            td10.innerText = reimbusement.reimApprover;

            let td9 = document.createElement('td');
            td9.innerText = reimbusement.reimAuthor;

            let td7 = document.createElement('td');
            td7.innerText = reimbusement.reimDescription;

            let td1 = document.createElement('td');
            td1.innerText = reimbusement.reimId;
    
            let td8 = document.createElement('td');
            let veiwImageButton = document.createElement('button'); 
            veiwImageButton.innerHTML = 'View Receipt';
            td8.appendChild(veiwImageButton);
    
            veiwImageButton.addEventListener('click', () => {
                let receiptImageModal = document.querySelector('#receipt-image-modal');
                let modalCLoseElement = receiptImageModal.querySelector('button');

                modalCLoseElement.addEventListener('click', () => {
                    receiptImageModal.classList.remove('is-active');
                });

                let modalContentElement = receiptImageModal.querySelector('.modal-content');
                modalContentElement.innerHTML = '';

                let imageElement = document.createElement('img');
                imageElement.setAttribute('src', `http://localhost:8080/ers_reimbursement/${reimbusement.reimId}/image`);
                modalContentElement.appendChild(imageElement);
    
                receiptImageModal.classList.add('is-active');
            });
            
            let td4 = document.createElement('td');
            td4.innerText = reimbusement.reimResolveTime;

            let td5 = document.createElement('td');
            td5.innerText = reimbusement.reimStatus;

            let td3 = document.createElement('td');
            td3.innerText = reimbusement.reimTime;

            let td6 = document.createElement('td');
            td6.innerText = reimbusement.reimType;

            let td11 = document.createElement('td');
            let td12 = document.createElement('td');

            if (reimbusement.reimStatus === 'Pending') {
                let approveButton = document.createElement('button');
                approveButton.innerText = 'Approved';
                approveButton.addEventListener('click', async () => {
                    let res = await fetch(`http://localhost:8080/ers_reimbursement/${reimbusement.reimId}`, 
                    {
                        credentials: 'include',
                        method: 'PATCH',
                        body: JSON.stringify({
                            "reimStatus": "Approved"
                        })
                    });
                    if (res.status === 200) { //Repopulate the table
                        populateReimbursmentTable();
                    }
                });

                let denyButton = document.createElement('button');
                denyButton.innerText = 'Denied';
                denyButton.addEventListener('click', async () => {
                    let res = await fetch(`http://localhost:8080/ers_reimbursement/${reimbusement.reimId}`, 
                    {
                        credentials: 'include',
                        method: 'PATCH',
                        body: JSON.stringify({
                            "reimStatus": "Denied"
                        })
                    });
                    if (res.status === 200) { //Repopulate the table
                        populateReimbursmentTable();
                    }

                });

                td11.appendChild(approveButton);
                td12.appendChild(denyButton); 
            }
    
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
            tr.appendChild(td11);
            tr.appendChild(td12);
    
         
            tbodyElement.appendChild(tr);
    
        }
    }
    
