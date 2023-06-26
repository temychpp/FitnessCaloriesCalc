import React from 'react';
import './css/App.css';
import {Route, Routes} from "react-router-dom";
import MainPage from "./components/MainPage";
import Navigation from "./components/Navigation";
import Anthro from "./components/Anthro";
import Activity from "./components/Activity";
import Calc from "./components/Calc";
import Account from "./components/Account";

function App() {
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
