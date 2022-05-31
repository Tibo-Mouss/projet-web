let urlUser = "http://localhost:8080/api/user";
let urlLogout = "http://localhost:8080/api/user/logout";

function togglePopup(id, value = "toggle") {
    popups = document.getElementsByClassName("popup");
    for (popup of popups) {
        if (popup.id != id) {
            popup.style.display = "none";
            continue;
        }

        if (value !== "toggle") {
            popup.style.display = value;
            continue;
        }

        if (popup.style.display === "unset") {
            popup.style.display = "none";
            continue;
        }

        popup.style.display = "unset";
    }
}

async function userLogin() {
    fetch(urlUser).then(async (resp) => {
        if (resp.ok) {
            user = await resp.json();

            username = document.getElementById("username");
            username.innerHTML = user.user;

            let elements = document.getElementsByClassName("logged-out");
            for (element of elements) {
                element.style.display = "none";
            }

            elements = document.getElementsByClassName("logged-in");
            for (element of elements) {
                element.style.display = "flex";
            }

            updateSolde();

            localStorage.setItem("logged", "true");
        }
    });
}

function userLogout() {
    fetch(urlLogout, {
        method: "POST",
    }).then((resp) => {
        if (resp.ok) {
            logouts = document.getElementsByClassName("logged-out");
            for (element of logouts) {
                element.style.display = "flex";
            }

            logins = document.getElementsByClassName("logged-in");
            for (element of logins) {
                element.style.display = "none";
            }

            localStorage.setItem("logged", "false");
        }
    });
}

async function updateSolde() {
    fetch(urlUser).then(async (resp) => {
        if (resp.ok) {
            user = await resp.json();
            let soldes = document.getElementsByClassName("solde");
            for (element of soldes) {
                element.innerHTML = user.balance + " ₹";
            }
        }
    });
}
