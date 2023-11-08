convertAndSubmit();

function calculateDurationInMinutes(startPeriod, endPeriod) {
    const lowerLimit = startPeriod.getTime(); // getTime() represents the time in milliseconds
    const upperLimit = endPeriod.getTime();
    return Math.round((upperLimit - lowerLimit) / 60000); // 1 minute = 60 s = 60 0000ms
}

function convertToDateAndTime(timePeriod) {
    return {
        date: timePeriod.toISOString().split('T')[0],
        time: timePeriod.toISOString().split('T')[1].replace('Z', '')
    }
}

function convertForm() {
    const getValue = (id) => document.getElementById(id).value;
    const startPeriodValue = new Date(getValue("start-period"));
    const endPeriodValue = new Date(getValue("end-period"));
    const startPeriod = convertToDateAndTime(startPeriodValue);
    const endPeriod = convertToDateAndTime(endPeriodValue);

    const formattedForm = {
        details: {
            skill: getValue("skill"),
            description: getValue("description"),
        },
        time: {
            startDate: startPeriod.date,
            startTime: startPeriod.time,
            endDate: endPeriod.date,
            endTime: endPeriod.time,
            duration: calculateDurationInMinutes(startPeriodValue, endPeriodValue).toString(),
        },
        feedback: {
            productivity: parseInt(getValue("productivity")),
            distraction: getValue("distraction"),
        },
    };

        return JSON.stringify(formattedForm);
}
function submitFormViaHTTP(convertedForm) {
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
        console.error("Error occurred:", error);
    });
}

function convertAndSubmit() {

    const sessionForm = document.getElementById("past-session-form");
    sessionForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const convertedForm = convertForm();
        submitFormViaHTTP(convertedForm);
    });
}
