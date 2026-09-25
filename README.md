# E-Commerce Web & API Quality Engineering Automation Framework

## Overview
This is a portfolio project for a Quality Engineering role. It is an automated test framework that tests two existing public applications to demonstrate Java test automation, web services / API testing, integration-style chains, and CI/CD with GitHub Actions. 
*Note: This project tests existing public applications and does not claim to have developed them.*

## Target Applications
- **UI:** [Sauce Demo](https://www.saucedemo.com)
- **API:** [Restful Booker](https://restful-booker.herokuapp.com)

## Tech Stack
- Java 17
- Maven
- Selenium 4
- TestNG
- REST Assured
- GitHub Actions

## Project Structure
- `src/test/java/com/qa/automation/pages`: Page Object classes for UI.
- `src/test/java/com/qa/automation/tests/ui`: UI tests.
- `src/test/java/com/qa/automation/tests/api`: API tests.
- `src/test/java/com/qa/automation/base`: Base setup classes.
- `src/test/resources/config.properties`: Core configuration for environment URLs and timeouts.
- `TEST_PLAN.md`: Strategic test plan including entry/exit criteria.
- `docs/API_OBSERVATIONS.md`: Notes on actual API behaviour.

## Prerequisites
- Java 17
- Maven

## How to Run
Run the main suite (headless by default in CI):
`./mvnw clean test`

Run headed mode:
`./mvnw clean test -Dheadless=false`

Run the exploratory suite:
`./mvnw clean test -DsuiteXmlFile=testng-exploratory.xml`

**Note on Secrets:**
For ease of demonstration, the passwords for the public sandbox sites are defaulted in code. **These are public demo credentials, not real secrets.** However, the framework is designed to read from environment variables or system properties for CI/CD and secure environments (e.g., `./mvnw clean test -Dui.password=secret_sauce -Dapi.password=password123`).

## Test Reports
Reports are generated in `target/surefire-reports/`. Screenshots for failures are stored in `target/screenshots/`.

To generate a polished HTML report:
`./mvnw surefire-report:report`
Then open `target/site/surefire-report.html`.

## Testing Approach
- UI testing uses the Page Object Model.
- Waits are implemented via Explicit Waits (WebDriverWait).
- API tests use REST Assured for request/response validation.

## Agile Workflow
1. Requirement Analysis
2. User Stories (tracked on GitHub Projects)
3. Test Design (documented in TEST_CASES.md)
4. Automation (development on branches, merged via PR)
5. Execution
6. Defect Logging (documented in BUG_REPORTS.md)

### User Stories
- **US-01:** As a shopper, I can log in so that I can see the products.
- **US-02:** As a shopper, I can add a product to the cart so that I can check out.
- **US-03:** As a shopper, I can complete the checkout so that I can buy items.
- **US-04:** As a client, I can create a booking via API so that a reservation is made.
- **US-05:** As an admin, I can update an existing booking using an auth token.

---
*(Placeholder for GitHub Actions Status Badge / Screenshot)*
