let urlPixel = "http://localhost:8080/api/pixel/";

const updatePixelGrid = async () => {
    fetch(urlPixel).then(async (resp) => {
        if (resp.ok) {
            json = await resp.json();
            setPixels(json);
        }
    });
};

function setPixels(json) {
    let table = document.getElementById("pixels");
    let num_rows = table.rows.length;

    for (let i = 0; i < num_rows; i++) {
        table.rows[i].innerHTML = "";
    }

    for (i in json) {
        let pixel = json[i];
        let row_id = Math.floor((pixel.id - 1) / num_rows);
        let row = table.rows[row_id];

        row.innerHTML +=
            '<td class="pixel" style="background:#' +
            pixel.color +
            '" onclick="pixelClick(' +
            pixel.id +
            ')">' +
            '<div class="info-pixel">' +
            '<div class="username">' +
            pixel.owner_username +
            "</div>" +
            '<div class="description">' +
            pixel.description +
            "</div>" +
            "</div>" +
            '<div class="patch-shadow"></div>' +
            "</td>";
    }
}

function pixelClick(id) {
    fetch(urlPixel + id).then(async (resp) => {
        if (resp.ok) {
            pixel = await resp.json();

            if (localStorage.getItem("logged") === "true") {
                // si le user est connecté
                fetch(urlUser).then(async (resp) => {
                    if (resp.ok) {
                        // on récupère ses infos
                        user = await resp.json();

                        if (user.pixels.includes(pixel.id)) {
                            // si le pixel lui appartient
                            updateModifyForm(pixel);
                            togglePopup("modify", "unset");
                        } else {
                            // sinon on affiche le form d'achat
                            updateBuyForm(pixel);
                            togglePopup("buy", "unset");
                        }
                    }
                });
            } else {
                // si non connecté, on affiche les infos
                updateBuyForm(pixel);
                togglePopup("buy", "unset");
            }
        }
    });
}

function updateModifyForm(pixel) {
    let form = document.getElementById("modify-form");
    form.action = urlPixel + pixel.id + "/modify";

    let price = document.getElementById("modify-price");
    price.value = pixel.price;

    let color = hexToRgb(pixel.color);

    let red = document.getElementById("modify-red");
    red.value = color.r;

    let green = document.getElementById("modify-green");
    green.value = color.g;

    let blue = document.getElementById("modify-blue");
    blue.value = color.b;

    let description = document.getElementById("modify-description");
    description.value = pixel.description;
}

function updateBuyForm(pixel) {
    owner = document.getElementById("info-owner");
    owner.innerHTML = pixel.owner_username;

    description = document.getElementById("info-description");
    description.innerHTML = pixel.description;

    price = document.getElementById("info-price");
    price.innerHTML = pixel.price + " ₹";

    form = document.getElementById("buy-form");
    form.action = urlPixel + pixel.id + "/buy";
}

function hexToRgb(hex) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result
        ? {
              r: parseInt(result[1], 16),
              g: parseInt(result[2], 16),
              b: parseInt(result[3], 16),
          }
        : null;
}
