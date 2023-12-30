import {productivityDescriptions} from "../../sessiontracker/utility/productivityDescription.js";

export function getDescriptiveProductivity(productivityRating) {

    return productivityDescriptions[productivityRating];
}