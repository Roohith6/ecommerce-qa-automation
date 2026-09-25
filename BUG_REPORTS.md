# Bug Reports (Verified)

**Severity:** Impact of the defect on the system (e.g., Critical, High, Medium, Low).
**Priority:** Urgency of fixing the defect (e.g., High, Medium, Low).

## Verified Defects

| Bug ID | Title | Environment | Precondition | Steps to Reproduce | Expected Result | Actual Result | Severity | Priority | Status |
|---|---|---|---|---|---|---|---|---|---|
| BUG-01 | problem_user cannot type in Last Name field | Chrome UI | Logged in as problem_user | 1. Add item to cart. 2. Click Checkout. 3. Type "Doe" in Last Name field. | Last name is entered. | Input is ignored, field remains empty, checkout cannot be completed. | High | Medium | Open |

## Observed behaviour, not a defect
- The `performance_glitch_user` introduces an intentional delay on login. The explicit waits (10 seconds) successfully handle this delay without failing.
- API `DELETE /booking/{id}` returns `201 Created` rather than `200 OK` or `204 No Content`. Documented this quirk.
