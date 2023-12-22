import {productivityDescriptions} from "../../tracker/utility/productivityDescription.js";

export function getDescriptiveProductivity(productivityRating) {

    return productivityDescriptions[productivityRating];
}