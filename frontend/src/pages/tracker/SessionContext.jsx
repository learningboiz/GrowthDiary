import {createContext, useState} from 'react';

export const SessionContext = createContext();

export const SessionContextProvider = ({ children }) => {
    const sessionFormTemplate = {
        topic: "",
        description: "",
        startPeriod: {},
        duration: "",
        obstacle: "",
        productivity: "",
    };

    const [sessionForm, setSessionForm] = useState(sessionFormTemplate);

    return (
        <SessionContext.Provider value={{sessionForm, setSessionForm}}>
            {children}
        </SessionContext.Provider>
    );
};