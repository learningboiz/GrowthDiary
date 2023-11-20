import HomePage from "../pages/homepage.jsx";
import { render, screen } from '@testing-library/react'
import { expect, test } from 'vitest'

test('renders welcome heading', () => {
    render(<HomePage />)

    const welcomeHeading = screen.getByText('Welcome');

    expect(welcomeHeading).toBeInTheDocument();
})