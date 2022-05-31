// fonction qui s'execute quand la page a finie de charger
function pageLoaded() {
    document.forms["pay-form"].addEventListener("submit", (event) => {
        event.preventDefault();
        fetch(event.target.action, {
            method: event.target.method,
            body: new URLSearchParams(new FormData(event.target)),
        }).then(async (resp) => {
            if (resp.ok) {
                updateSolde();
            }
        });
    });

    document.forms["modify-form"].addEventListener("submit", (event) => {
        event.preventDefault();
        fetch(event.target.action, {
            method: event.target.method,
            body: new URLSearchParams(new FormData(event.target)),
        }).then(async (resp) => {
            if (resp.ok) {
                updatePixelGrid();
                updateSolde();
                togglePopup("modify");
            }
        });
    });

    document.forms["buy-form"].addEventListener("submit", (event) => {
        event.preventDefault();
        fetch(event.target.action, {
            method: event.target.method,
            body: new URLSearchParams(new FormData(event.target)),
        }).then(async (resp) => {
            if (resp.ok) {
                updatePixelGrid();
                updateSolde();
                pixelClick(event.target.action.split("/")[5]);
            }
        });
    });

    document.forms["login-form"].addEventListener("submit", (event) => {
        event.preventDefault();
        fetch(event.target.action, {
            method: event.target.method,
            body: new URLSearchParams(new FormData(event.target)),
        }).then(async (resp) => {
            if (resp.ok) {
                userLogin();
                togglePopup("login");
            }
        });
    });

    document.forms["signup-form"].addEventListener("submit", (event) => {
        event.preventDefault();
        fetch(event.target.action, {
            method: event.target.method,
            body: new URLSearchParams(new FormData(event.target)),
        }).then(async (resp) => {
            if (resp.ok) {
                userLogin();
                togglePopup("signup");
            }
        });
    });

    updatePixelGrid();
    setInterval(updatePixelGrid, 10000);

    if (localStorage.getItem("logged") === "true") {
        userLogin();
    } else {
        localStorage.setItem("logged", "false");
    }
}
