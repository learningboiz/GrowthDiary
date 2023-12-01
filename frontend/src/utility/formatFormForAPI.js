import {splitDateTime} from "./splitDateTime.js";

export default function convertToAPIFormat(sessionForm) {

    const {
        topic,
        description,
        startPeriod,
        duration,
        obstacle,
        productivity
    } = sessionForm;

    const utcStartPeriod = startPeriod.toISOString();
    const {startDate, startTime} = splitDateTime(utcStartPeriod);

    return {
        details: {
            topic: topic,
            description: description
        },
        time: {
            startDate: startDate,
            startTime: startTime,
            duration: duration
        },
        feedback: {
            obstacle: obstacle,
            productivity: productivity
        }
    }
}