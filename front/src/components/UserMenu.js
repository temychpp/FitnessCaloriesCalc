import React from 'react';
import {Menu} from 'antd';
import {Link} from "react-router-dom";

export default function UserMenu() {

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
            label: (<Link to='/account'>Аккаунт</Link>),
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
            />
        </>
    )
}