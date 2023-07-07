export function getAccount() {
    let accountString = localStorage.getItem('account');
    return typeof accountString !== "undefined" ? JSON.parse(accountString) : null;
}

export function updateAccount(account) {
    localStorage.setItem('account', JSON.stringify(account))
}

export function wipeAccount() {
    localStorage.removeItem('account')
}