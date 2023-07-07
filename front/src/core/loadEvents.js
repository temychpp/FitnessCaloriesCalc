import {emitCustomEvent} from "react-custom-events";

export function emitLoadingError(message) {
    emitCustomEvent('error-event', message);
}