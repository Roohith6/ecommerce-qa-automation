# Test Plan

## Objective
To verify the core functionality of the Sauce Demo UI and the Restful Booker API.

## Scope
**In Scope:**
- UI: Login, Add/Remove from cart, Checkout end-to-end.
- API: Authentication, CRUD operations on bookings.

**Out of Scope:**
- Load/Performance testing (except basic exploratory checks).
- Security testing.

## Test Levels and Types
- Functional Testing
- Smoke Testing
- Regression Testing
- Negative Testing
- End-to-End Testing
- API / Integration Testing

## Tools
- Java, Selenium WebDriver, REST Assured, TestNG, Maven.

## Environment
- Local execution with Chrome browser.
- CI execution on Ubuntu with Headless Chrome.

## Entry & Exit Criteria
- **Entry:** Environment is available, dependencies are installed.
- **Exit:** All planned test cases executed, major bugs logged.

## Risks
- Public demo sites can change or go down unexpectedly.
- Shared API data might be reset or modified by others.

## Defect Handling
Defects are logged in `BUG_REPORTS.md`.
