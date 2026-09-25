# API Observations (Verified through test automation)

## Restful Booker
Base URL: `https://restful-booker.herokuapp.com`

### POST /auth
- **Request Body:** `{"username": "admin", "password": "password123"}`
- **Headers:** `Content-Type: application/json`
- **Response 200:** Returns token `{"token": "xyz123"}`
- **Invalid Credentials:** Returns 200 OK with `{"reason": "Bad credentials"}` (quirk noted).

### GET /booking
- **Response 200:** Returns list of objects `[{"bookingid": 1}, {"bookingid": 2}]`

### GET /booking/{id}
- **Headers:** `Accept: application/json`
- **Response 200:** Returns booking object with firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds.
- **Non-existent ID:** Returns 404 Not Found.

### POST /booking
- **Headers:** `Content-Type: application/json`, `Accept: application/json`
- **Response 200 (not 201):** Returns created object inside `{"bookingid": 123, "booking": {...}}`.

### PUT /booking/{id}
- **Headers:** `Cookie: token={token}` or `Authorization: Basic ...`
- **Response 200:** Updates and returns booking.
- **Without token:** Returns 403 Forbidden.

### DELETE /booking/{id}
- **Headers:** `Cookie: token={token}`
- **Response 201 (not 204 or 200):** Deletes booking and returns 201 Created (quirk).
- **Subsequent GET:** Returns 404 Not Found.
