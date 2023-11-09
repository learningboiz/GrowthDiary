createSession();

function createSession() {

    const sessionForm = document.getElementById("past-session-form");
    sessionForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const convertedForm = convertForm();
        submitFormViaHTTP(convertedForm);
    });
}


/* ----------------------------------- Helper functions ----------------------------------------------------- */
function calculateDurationInMinutes(startPeriod, endPeriod) {
    const lowerLimit = startPeriod.getTime(); // getTime() represents the time in milliseconds
    const upperLimit = endPeriod.getTime();
    return Math.round((upperLimit - lowerLimit) / 60000); // 1 minute = 60 s = 60 0000ms
}

function convertToUTCDateTime(timePeriod) {
    return {
        date: timePeriod.toISOString().split('T')[0],
        time: timePeriod.toISOString().split('T')[1].replace('Z', '')
    }
}

function convertForm() {
    const getValue = (id) => document.getElementById(id).value;

    const localStartPeriod = new Date(getValue("start-period"));
    const localEndPeriod = new Date(getValue("end-period"));
    const utcStartPeriod = convertToUTCDateTime(localStartPeriod);
    const utcEndPeriod = convertToUTCDateTime(localEndPeriod);

    const formattedForm = {
        details: {
            skill: getValue("skill"),
            description: getValue("description"),
        },
        time: {
            startDate: utcStartPeriod.date,
            startTime: utcStartPeriod.time,
            endDate: utcEndPeriod.date,
            endTime: utcEndPeriod.time,
            duration: calculateDurationInMinutes(localStartPeriod, localEndPeriod).toString(),
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
            response.text().then(function (responseBody) {
                const createdSession = JSON.parse(responseBody);
                createSuccessMessage(createdSession);
            })
        } else {
            response.text().then(function (errorText) {
                alert("Form submission failed" + errorText);
            })
        }
    }).catch(function (error) {
        console.error("Error occurred:", error);
    });
}

function createSuccessMessage(sessionJSON) {
    const mainContent = document.getElementById("main-content");
    while (mainContent.firstChild) {
        mainContent.removeChild(mainContent.firstChild); // removes all elements under the section
    }

    const successHeader = document.createElement("h1");
    successHeader.textContent = "Your session has been successfully recorded";

    const sessionReviewDiv = document.createElement("div");
    sessionReviewDiv.setAttribute("id", "session-info");

    const sessionReviewHeader = document.createElement("h2");
    sessionReviewHeader.textContent = "Here's a quick look at your recent session";
    sessionReviewDiv.appendChild(sessionReviewHeader);

    const skillName = sessionJSON.details.skill;
    const timePeriod = convertToLocalDateTime(sessionJSON.time.startDate, sessionJSON.time.startTime);
    const duration = sessionJSON.time.duration;
    const productivity = sessionJSON.feedback.productivity;
    const distraction = sessionJSON.feedback.distraction;

    const sessionReviewDetails = document.createElement("p");
    sessionReviewDetails.textContent = "You have worked on " + skillName;

    const sessionReviewTime = document.createElement("p");
    sessionReviewTime.textContent = "Your session began on " + timePeriod + " and lasted " + duration;

    const sessionReviewFeedback = document.createElement("p");
    sessionReviewFeedback.textContent = "You were most distracted by " + distraction + " and the overall session productivity was " + productivity;

    sessionReviewDiv.appendChild(sessionReviewDetails);
    sessionReviewDiv.appendChild(sessionReviewTime);
    sessionReviewDiv.appendChild(sessionReviewFeedback);

    mainContent.appendChild(successHeader);
    mainContent.appendChild(sessionReviewDiv);

    const sessionButton = document.createElement("BUTTON");
    sessionButton.textContent = "Log another session!";
    sessionButton.addEventListener("click", resetForm);

    mainContent.appendChild(sessionButton);
}

function convertToLocalDateTime(startDate, startTime) {
    const utcDateTime = startDate.concat("T", startTime, "Z");
    const localDateTime = new Date(utcDateTime);
    return localDateTime.toLocaleString();
}

function resetForm() {
    window.location = 'past-session.html';
}