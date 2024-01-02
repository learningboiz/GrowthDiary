# Growth Diary Frontend

## Overview

The frontend UI for this application mainly uses the React library 
along with Tailwind CSS for styling. 
The build tool used is Vite which provides a built-in development server.

## File structure

The codebase is organised based on common React project file structures:

* `src`: The primary directory containing the bulk of the code.
* `components`: Reusable UI components used across the application.
* `features`: UI components specific to the core functionality of the app.
* `routes`: Navigation and routing logic.
* `tests`: Unit tests for various components.
* `utility`: Shared helper functions and utilities.

## Getting started

To run the development server locally, kindly follow these steps:

1. Make sure you have Node.js installed
2. Clone the repository:
```
git clone https://github.com/ElijahQuiazon/GrowthDiary.git
```
3. Navigate to the frontend directory:
```
cd frontend
```
4. Install dependencies
```
npm install
```
5. Start the development server
```
npm run dev
```

If using Vite as a build tool, the local server is typically hosted either on http://localhost:5173/ or http://localhost:5174/.
Make sure that the backend server is also running to allow API requests to go through 
(else there won't be data displayed in the history/analytics nor can sessions be submitted)

## Potential areas for contribution/improvement
* Creating more robust tests for components
* Cleaner code base and organisation
* Implementation of front-end auth