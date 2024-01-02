# Growth Diary App - Contributing Guide

Welcome to Growth Diary App! We're thrilled that you're interested in contributing. This guide will help you get started. If you have any questions that are not addressed here, feel free to [create an issue](https://github.com/ElijahQuiazon/GrowthDiary/issues).

## Table of Contents

- [Getting Started](##getting-started)
- [Setting Up Your Environment](#setting-up-your-environment)
  - [Front-end](#front-end)
  - [Back-end](#back-end)
- [Making Changes](#making-changes)
- [Testing](#testing)
- [Submitting Changes](#submitting-changes)
- [Code of Conduct](#code-of-conduct)
- [License](#license)

## Getting Started

1. **Fork the repository:**
   Click on the "Fork" button on the top right corner of the page. This will create a copy of the repository in your GitHub account.

2. **Clone your fork:**
   ```bash
   git clone https://github.com/your-username/GrowthDiary.git
   cd GrowthDiary

## Setting Up Your Environment
### Front-end
Navigate to the front-end directory:
```bash
cd front-end
```

Install dependencies:
```bash
npm install
```

Start the development server:
```bash
npm run dev
```

### Back-end
Navigate to the back-end directory:
```bash
cd backend
```

Install dependencies:
```bash
./mvnw clean install
```
(Note: Make sure you have Maven installed)

Run the backend server:
```bash
./mvnw spring-boot:run
```

## Making Changes
Create a new branch:
```bash
git checkout -b your-branch-name
```

Make your changes and commit:
```bash
git add .
git commit -m "Your descriptive commit message"
```

## Testing
Ensure that your changes are well-tested. If adding new features, include relevant tests.

## Submitting Changes
Push your changes to your fork:
```bash
git push origin your-branch-name
```

Create a pull request:
* Go to your fork on GitHub.
* Click on "New Pull Request."
* Follow the prompts to create your pull request.
* Wait for feedback or approval.

## Code of Conduct
Please review our Code of Conduct to understand the standards we expect in this community.

## License
Growth Diary App is open-source software licensed under the MIT License.
