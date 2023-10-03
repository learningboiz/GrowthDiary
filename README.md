# Growth Diary Project

## Overview

### Value Proposition
The GrowthDiary is a web-application that tracks individual learning progress. The app primarily targets beginner programmers who often run into issues of tutorial hell and inefficient learning. GrowthDiary solves this problem is by providing users a straightforward method of documenting their progress and reviewing these session.

### Tech Stack
Backend framework: Spring Boot
Database: MySQL
Testing: JUnit, Mockito - Unit/Integration
Design architecture: MVC 

### Features
- Filter: JPA Specifications, Criteria API, JPA Metamodel
- Tracker: MVC architecture

## Core functionalities

### 1. Session tracker
- Requirements:
  - Create a session to log learning progress
  - Be able to input the skill being worked on and its description
  - Track the date, time and duration of each session
  - Provide feedback about productivity levels and distraction

### 2. History log
- Requirements:
  - View all past session details
  - View 10/20/30 sessions at a time (per page)
  - Sort details by skill, time, duration and productivity
  - Filter by:
    - Specific skills
    - Descriptions with keyword
    - Duration under/over/between stipulated range
    - Dates before/after/between stipulated range
    - Productivity under/over/between stipulated range
    - Specific distractions

### 3. Analytics
- Present top (5/10/15) skills, distractions, session timings, durations
- Correlation between distractions/session timings/skill and productivity; 

## Additional features
### 4. Email messaging 

### 5. Tutorial

### 6. Social messaging
