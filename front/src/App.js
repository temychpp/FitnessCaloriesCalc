import React, {useState} from 'react';
import './css/App.css';
import {Route, Routes, useNavigate} from "react-router-dom";
import MainPage from "./components/MainPage";
import Navigation from "./components/Navigation";
import Anthro from "./components/Anthro";
import Activity from "./components/Activity";
import Calc from "./components/Calc";
import Account from "./components/Account";
import {useCustomEventListener} from "react-custom-events";
import {ACCOUNT_UPDATE} from "./core/loadEvents";

function App() {
    const [lastUpdate, setLastUpdate] = useState(Date.now())
    const navigate = useNavigate();

    useCustomEventListener(ACCOUNT_UPDATE, (data) => {
        setLastUpdate(Date.now)
        if (data === 'logout') {
            navigate('/');
        }
    });

    return (
        <>
            <Navigation/>
            <Routes>
                <Route exact path='/' element={<MainPage/>}/>
                <Route path='/anthro' element={<Anthro/>}/>
                <Route path='/activity' element={<Activity/>}/>
                <Route path='/calc' element={<Calc/>}/>
                <Route path='/account' element={<Account/>}/>
            </Routes>
        </>
    )
}

export default App;
