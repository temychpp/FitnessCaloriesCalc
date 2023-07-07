import {useState} from "react";
import { useCustomEventListener } from 'react-custom-events'
import LoadingBar from "react-top-loading-bar";
import {LOADED, START_LOADING} from "../core/loadEvents";


export default function Loader() {
    const [percent, setPercent] = useState(0);

    useCustomEventListener(START_LOADING, (_) => {
        setPercent(50);
    })

    useCustomEventListener(LOADED, (_) => {
        setPercent(100);
    })

    return (<>
        <LoadingBar color='#f11946'
                    progress={percent}
                    onLoaderFinished={() => setPercent(0)}/>
    </>)
}
