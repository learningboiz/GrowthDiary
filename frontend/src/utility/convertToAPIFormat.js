import {splitDateTime} from "./splitDateTime.js";

export default function convertToAPIFormat(sessionForm) {

    const utcTimePeriod = sessionForm.startPeriod.toISOString();
    const {startDate, startTime} = splitDateTime(utcTimePeriod);

    return {
        details: {
            topic: sessionForm.topic,
            description: sessionForm.description
        },
        time: {
            startDate: startDate,
            startTime: startTime,
            duration: sessionForm.duration
        },
        feedback: {
            obstacle: sessionForm.obstacle,
            productivity: sessionForm.productivity
        }
    }
}