import {splitPeriodIntoDateTime} from "../../../utility/splitPeriodIntoDateTime.js";
import {getDurationInMinutes} from "./getDurationInMinutes.js";

export default function getSessionAPIFormat(sessionForm) {

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
    const [startDate, startTime] = splitPeriodIntoDateTime(utcStartPeriod);
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