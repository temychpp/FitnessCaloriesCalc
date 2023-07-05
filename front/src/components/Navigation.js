import React from 'react';
import UserBlock from "./UserBlock"
import {Link} from "react-router-dom";
import Loader from "./Loader";

export default function Navigation() {
    return (
        <>
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