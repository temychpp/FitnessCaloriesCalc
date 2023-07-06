import React from 'react';
import UserBlock from "./UserBlock"
import {Link} from "react-router-dom";
import Loader from "./Loader";
import { message } from 'antd';
import { useCustomEventListener } from 'react-custom-events'

export default function Navigation() {
    const [messageApi, contextHolder] = message.useMessage();

    useCustomEventListener('error-event', (data) => {
        console.log(data)
        messageApi.error(data);
    })

    useCustomEventListener('warn-event', (data) => {
        messageApi.warning(data);
    })

    useCustomEventListener('info-event', (data) => {
        messageApi.info(data);
    })

    return (
        <>
            {contextHolder}
            <Loader/>
            <div className='navigation'>
                <div className='logo'><Link to='/'><img alt='' src='./logo.png'/></Link></div>
                <div className='user-block'>
                    <UserBlock/>
                </div>
            </div>
        </>
    )
}