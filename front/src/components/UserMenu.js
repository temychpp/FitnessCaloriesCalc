import React from 'react';
import {Menu} from 'antd';
import {Link} from "react-router-dom";
import {getAccount, wipeAccount} from "../core/account";
import {emitCustomEvent} from "react-custom-events";
import {ACCOUNT_UPDATE} from "../core/loadEvents";

export default function UserMenu() {

    const onClick = (key) => {
        if (key.key === "logout") {
            wipeAccount();
            emitCustomEvent(ACCOUNT_UPDATE, 'logout');
        }
    }

    const items = [
        {
            label: (<Link to='/anthro'>Антропометрические данные</Link>),
            key: 'anthro'
        },
        {
            label: (<Link to='/activity'>Активность</Link>),
            key: 'activity'
        },
        {
            label: (<Link to='/calc'>Расчеты</Link>),
            key: 'calc'
        },
        {
            label: (<Link to='/account'>{getAccount().name}</Link>),
            key: 'account',
            children: [
                {
                    label: 'Выйти',
                    key: 'logout'
                }
            ]
        },
    ];
    return (
        <>
            <Menu
                mode="horizontal"
                items={items}
                onClick={onClick}
            />
        </>
    )
}