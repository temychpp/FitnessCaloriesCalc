import {useState} from "react";
import { useCustomEventListener } from 'react-custom-events'
import LoadingBar from "react-top-loading-bar";


export default function Loader() {
    const [percent, setPercent] = useState(0);

    useCustomEventListener('start-loading', (data) => {
        setPercent(50);
    })

    useCustomEventListener('loaded', (data) => {
        setPercent(100);
    })

    return (<>
        <LoadingBar color='#f11946'
                    progress={percent}
                    onLoaderFinished={() => setPercent(0)}/>
    </>)
}
