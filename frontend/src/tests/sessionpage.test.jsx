import {fireEvent, render, screen} from '@testing-library/react'
import { expect, test } from 'vitest'
import SessionPage from "../pages/sessionpage.jsx";
import {MemoryRouter} from "react-router-dom";

/*
  Wrapped <MemoryRouter> around the tested component to ensure links work
 */

test('renders session page elements', () => {
    render(
        <MemoryRouter>
            <SessionPage />
        </MemoryRouter>)

    const newSessionLink = screen.getByText(/New session/i);
    const oldSessionLink = screen.getByText(/Old Session/i);

    expect(newSessionLink).toBeInTheDocument();
    expect(oldSessionLink).toBeInTheDocument();
})