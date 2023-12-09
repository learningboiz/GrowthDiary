import {createContext, useState} from 'react';

/**
 * Context to manage the state of a session form
 */
export const SessionContext = createContext();

/**
 * Provides methods to view and update existing form data in the context, and reset values to default
 * Reset values method created to allow form to re-render after each completion since components can only re-render after changes to state
 *
 * Context provider wrapped around app entry point to ensure context can be used in every route
 */
export const SessionContextProvider = ({ children }) => {
    const initialFormValues = {
        topic: "",
        description: "",
        startPeriod: {},
        duration: "",
        obstacle: "",
        productivity: "",
    };
    const [sessionForm, setSessionForm] = useState(initialFormValues);

    const resetFormValues = () => {
        setSessionForm(initialFormValues)
    }

    return (
        <SessionContext.Provider value={{sessionForm, setSessionForm, resetFormValues}}>
            {children}
        </SessionContext.Provider>
    );
};