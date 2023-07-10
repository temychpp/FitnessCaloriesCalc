import React from 'react';
import UserBlock from "./UserBlock"
import {Link} from "react-router-dom";
import Loader from "./Loader";
import { message } from 'antd';
import { useCustomEventListener } from 'react-custom-events'
import {ERROR_EVENT, INFO_EVENT, WARN_EVENT} from "../core/loadEvents";

export default function Navigation() {
    const [messageApi, contextHolder] = message.useMessage();

    useCustomEventListener(ERROR_EVENT, (data) => {
        console.log(data)
        messageApi.error(data);
    })

    useCustomEventListener(WARN_EVENT, (data) => {
        messageApi.warning(data);
    })

    useCustomEventListener(INFO_EVENT, (data) => {
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