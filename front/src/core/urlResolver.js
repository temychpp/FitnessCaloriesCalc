import {getAccount} from "./account";

const url = 'http://localhost:8080/'

export const anthroUrl = 'person/anthro';
export const activityUrl = 'person/activity';
export const calcUrl = 'calc';
export const loginUrl = 'login';
export const registerUrl = 'register';

export const GET_REQUEST = {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + getAccount().token
    }
}

export function post_rq(body) {
    return {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + getAccount().token
        },
        body: body
    }
}

export function getUrl(endpoint, params = '') {
    return url + endpoint + params;
}