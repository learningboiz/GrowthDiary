import {splitDateTime} from "./splitDateTime.js";
import {getDurationInMinutes} from "./getDurationInMinutes.js";

export default function getAPIFormat(sessionForm) {

    const {
        topic,
        description,
        startPeriod,
        hours,
        minutes,
        obstacle,
        productivity
    } = sessionForm;

    const utcStartPeriod = startPeriod.toISOString();
    const [startDate, startTime] = splitDateTime(utcStartPeriod);
    const duration = getDurationInMinutes(hours, minutes);

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