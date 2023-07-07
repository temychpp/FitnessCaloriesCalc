import React from 'react';
import UserMenu from "./UserMenu";
import Login from "./Login";
import {getAccount} from "../core/account";

export default function UserBlock() {
    const account = getAccount();
    if (account !== null) {
        return (<>
            <UserMenu/>
        </>)
    } else {
        return (<>
            <Login/>
        </>)
    }
}