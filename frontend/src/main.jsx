import React, {useEffect, useState} from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx';
import './index.css'
import { BrowserRouter } from "react-router-dom";
import {SessionContextProvider} from "./features/sessiontracker/SessionContext.jsx";
import {AuthProvider} from "./features/authentication/AuthContext.jsx";




ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <BrowserRouter>
          <AuthProvider>
              <SessionContextProvider>
                  <App />
              </SessionContextProvider>
          </AuthProvider>
      </BrowserRouter>
  </React.StrictMode>,
)
