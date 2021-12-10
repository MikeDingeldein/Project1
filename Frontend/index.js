window.addEventListener("load", async () => {
  console.log("EXECUTED");

  let res = await fetch("http://localhost:8080/checkloginstatus", {
    method: "GET",
    credentials: "include",
  });
  if (res.status === 200) {
    let userObject = await res.json();

    if (userObject.userRole === "employee") {
      window.location.href = "employee.html"; //loged in employee to employee page
    } else if (userObject.userRole === "manager") {
      window.location.href = "financemanager.html"; //loged in manager to manager page
    }
  }
});

let loginButton = document.querySelector("#login-btn");

loginButton.addEventListener("click", loginButtonCLicked);

function loginButtonCLicked() {
  login();
}

async function login() {
  let usernameInput = document.querySelector("#username");
  let passwordInput = document.querySelector("#password");

  try {
    let res = await fetch("http://localhost:8080/login", {
      method: "POST",
      credentials: "include", // cookie
      body: JSON.stringify({
        username: usernameInput.value,
        password: passwordInput.value,
      }), //JavaScript to JSON
    });

    let data = await res.json();

    if (res.status === 400) {
      //login error
      let loginErrorMessage = document.createElement("p");
      let loginDiv = document.querySelector("#login-info");

      loginErrorMessage.innerHTML = data.message;
      loginErrorMessage.style.color = "red";
      loginDiv.appendChild(loginErrorMessage);
    }

    if (res.status === 200) {
      console.log(data.userRole);
      if (data.userRole === "employee") {
        window.location.href = "employee.html"; //loged in to employee page
      } else if (data.userRole === "manager") {
        window.location.href = "financemanager.html"; //loged in manager to manager page
      }
    }
  } catch (err) {
    console.log(err);
  }
}
