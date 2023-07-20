import {getAccount} from "./account";

const url = 'http://localhost:8080/'

export const anthroUrl = 'person/anthro';
export const activityUrl = 'person/activity';
export const calcUrl = 'calc';
export const loginUrl = 'login';
export const registerUrl = 'register';
export const updatePersonUrl = 'update';
export const createProductUrl = 'product';
export const dayMealUrl = 'meal/mealbyday';

export function get_rq() {
    let auth = ''

    if (getAccount() !== null) auth = 'Bearer ' + getAccount().token

    return {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        }
    }
}

export function post_rq(body) {
    let auth = ''
    if (getAccount() !== null) auth = 'Bearer ' + getAccount().token

    return {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        },
        body: body
    }
}

export function patch_rq(body) {
    let auth = ''
    if (getAccount() !== null) auth = 'Bearer ' + getAccount().token

    return {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        },
        body: body
    }
}


export function getUrl(endpoint, params = '') {
    return url + endpoint + params;
}