# Reimbursment Application
Application that allows employees to submit reimbursements with receipts and view their existing reimbursemments. The Application also allows the finance manager to view all of the reimbursements and approve or deny them. 

# Technologies Used
- Spring Tool Suite 4 
- Visual Studio Code
- Java 8
- PostgreSQL
- Selenium
- Mockito

# Features
This application allows employees to add reimbursement requests and view their previously submitted reimbursement requests. The Finance Manager can then approve or deny the reimbursement request.

- Passwords are hashed.
- Employees can upload receipts images in JPEG, GIF, and PNG formats.
- Employees can view the uploaded recipts with a modal.
- End to end testing is done with Selenium web automation.

To-do list:

- Improve password hashing with salt
- Allow users to filter by reimbursement status
- Add more unit testing
- Improve asthetics of the front end
# Getting Started
To clone the repository
-- git clone git@github.com:MikeDingeldein/Project1.git

You will need to create SQL tables with the commands below

    create table ers_users (
    user_id serial primary key,
    ers_username varchar(255) NOT null,
    ers_password varchar(255) NOT null,
    user_first_name varchar(255) NOT null,
    user_last_name varchar(255) NOT null,
    user_email varchar(255) NOT null,
    user_role varchar(255) NOT null
    );

    CREATE TABLE ers_reimbursement (
    reimb_id serial PRIMARY KEY,
    reimb_amount decimal,
    reimb_submitted timestamp NOT NULL DEFAULT current_timestamp,
    reimb_resolved timestamp,
    reimb_status varchar(255),
    reimb_type varchar(255) NOT null,
    reimb_description varchar(255),
    reimb_receipt bytea,
    reimb_author int, --user id OF employee
    reimb_resolver int,  --USER id OF manager

    CONSTRAINT fk_ers_users1 FOREIGN KEY(reimb_author)
        REFERENCES ers_users(user_id),
    CONSTRAINT fk_ers_users2 FOREIGN KEY(reimb_resolver)
        REFERENCES ers_users(user_id)
    );


# Usage
Login as employee to add new reimburesment requests and view previously submitteed requests. Ther newest requests will be at the top. The receipt image can be viewed on a modal with the button provided.

Login as a finance manager to view all of the reimbursements. The receipt image can be viewed on a modal with the button provided. The finance manager can  aprove or deny a reimbursement with the buttons provided.



# Contributors
Mike Dingeldein (solo project)

