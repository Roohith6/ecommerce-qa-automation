# Test Cases

| ID | Scenario | Type | Preconditions | Steps | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|---|---|
| UI-01 | Valid login | smoke, positive | standard_user exists | 1. Enter standard_user/secret_sauce. 2. Click Login | Redirected to Products Page | As expected | Passed |
| UI-02 | Invalid password | negative, regression | standard_user exists | 1. Enter standard_user/wrong_password. 2. Click Login | Displays username and password do not match error | As expected | Passed |
| UI-03 | Empty username | negative, regression |  | 1. Leave username blank. 2. Enter password. 3. Click Login | Displays Username is required error | As expected | Passed |
| UI-04 | Empty password | negative, regression | standard_user exists | 1. Enter username. 2. Leave password blank. 3. Click Login | Displays Password is required error | As expected | Passed |
| UI-05 | Locked out login | negative, regression | locked_out_user exists | 1. Enter locked_out_user/secret_sauce. 2. Click Login | Displays "locked out" error | As expected | Passed |
| UI-06 | Add to cart | smoke, positive | Logged in as standard_user | 1. Add backpack to cart. 2. Check badge | Badge shows 1, cart shows 1 item | As expected | Passed |
| UI-07 | Remove from cart | regression | Logged in as standard_user | 1. Add backpack. 2. Remove backpack | Badge is 0, cart empty | As expected | Passed |
| UI-08 | Checkout item total | regression, functional | Logged in as standard_user | 1. Add two items. 2. Go to checkout. 3. Fill info | Item total matches sum of prices | As expected | Passed |
| UI-09 | Checkout E2E | smoke, e2e | Logged in as standard_user | 1. Add item. 2. Checkout. 3. Finish | Displays "Thank you for your order!" | As expected | Passed |
| UI-10 | Logout | regression, positive | Logged in as standard_user | 1. Open menu. 2. Click logout | Returns to login page | As expected | Passed |
| UI-11 | problem_user flow | exploratory | problem_user exists | Login, add item, checkout | Same behaviour as standard_user | Timeout on Checkout finish (Input ignored in Last Name) | Failed |
| UI-12 | performance_glitch_user | exploratory | performance_glitch_user exists | Login, add item | Completes correctly (handles delay) | Completes correctly | Passed |
| API-01 | Auth valid | auth | Valid credentials | POST /auth | Returns non-empty token | As expected | Passed |
| API-02 | Auth invalid | auth | Invalid credentials | POST /auth | Returns reason | As expected | Passed |
| API-03 | GET Bookings | smoke | Server running | GET /booking | Returns array of objects | As expected | Passed |
| API-04 | GET Booking by ID | smoke | Booking exists | GET /booking/{id} | Returns expected JSON fields | As expected | Passed |
| API-05 | GET non-existent booking | negative | Server running | GET /booking/999999999 | Returns 404 | As expected | Passed |
| API-06 | POST create booking | smoke | Server running | POST /booking | Returns booking ID | As expected | Passed |
| API-07 | PUT update booking | smoke | Valid token, booking exists | PUT /booking/{id} | Updates and returns booking | As expected | Passed |
| API-08 | PUT update without token | negative | Booking exists | PUT /booking/{id} | Returns 403 Forbidden | As expected | Passed |
| API-09 | DELETE booking | smoke | Valid token, booking exists | DELETE /booking/{id} | Deletes booking | As expected (returns 201) | Passed |
| API-10 | POST invalid body | negative | Server running | POST /booking | Returns 500 | As expected | Passed |
| API-11 | Chained API Flow | integration | Server running | POST, GET, PUT, PATCH, DELETE | Each step validates successfully | As expected | Passed |
