function convertForm() {

    const sessionForm = document.getElementById("past-session-form");
    sessionForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(sessionForm);
        const jsonObject = {};
        formData.forEach(function (value,key) {
            jsonObject[key] = value;
        });

        const convertedForm = JSON.stringify(jsonObject);

        fetch("http://localhost:8080/session",
            {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: convertedForm
            }).then(function (response) {
                if (response.status === 201) {
                    alert("Form submitted successfully");
                } else {
                    response.text().then(function (errorText) {
                        alert("Form submission failed" + errorText);
                    })
                }
            }).catch(function (error) {
                console.error("Error occured:", error);
        });
    })
}
convertForm();
