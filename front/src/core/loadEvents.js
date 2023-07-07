import {emitCustomEvent} from "react-custom-events";

export const START_LOADING = 'start-loading';
export const LOADED = 'LOADED';
export const ERROR_EVENT = 'error-event';
export const WARN_EVENT = 'warn-event';
export const INFO_EVENT = 'info-event';
export const ACCOUNT_UPDATE = 'account-update';

export function emitLoadingError(message) {
    emitCustomEvent(ERROR_EVENT, message);
}