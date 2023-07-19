import React from 'react';
import {Menu} from 'antd';
import {Link} from "react-router-dom";
import {getAccount, wipeAccount} from "../core/account";
import {emitCustomEvent} from "react-custom-events";
import {ACCOUNT_UPDATE} from "../core/loadEvents";


let reg = ''
// if (getAccount().role === 'ADMIN')
//     reg = {
//         label: (<Link to='/adminRegister'>регистрация</Link>),
//         key: 'adminRegister'
//     }

export default function UserMenu() {

    const onClick = (key) => {
        if (key.key === "logout") {
            wipeAccount();
            emitCustomEvent(ACCOUNT_UPDATE, 'logout');
        }
    }

    const items = [
        {
            label:(<Link to='/home'>Еда</Link>),
            key: 'dayMeal',
        },

        {
            label: (<Link to='/calc'>Расчеты</Link>),
            key: 'calc'
        },
        {
            label: 'Продукты',
            key: 'products',
            children: [
                {
                    label: (<Link to='/product'>Создать продукт</Link>),
                    key: 'product'
                }
            ]
        },
        {
            label: 'Инфо',
            key: 'info',
            children: [
                {
                    label: (<Link to='/anthro'>Антропометрические данные</Link>),
                    key: 'anthro'
                },
                {
                    label: (<Link to='/activity'>Активность</Link>),
                    key: 'activity'
                },
            ]
        },

        {
            label: (<Link to='/account'>{getAccount().name}</Link>),
            key: 'account',
            children: [
                {
                    label: (<Link to='/adminRegister'>регистрация</Link>),
                    key: 'adminRegister'
                },
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