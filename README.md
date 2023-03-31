## IT Book Review
**Review App** is a responsive Spring Boot application based on MVC architecture that supports CRUD operations and allows users to submit new reviews and manage existing ones via browser.

### Frontend Specifications
- Implemented using ReactJS (with React Router, Axios, SessionStorage, Controlled & Uncontrolled Forms)
- Uses Bootstrap for styling
- Made responsive by adding media queries
- Uses [IT Bookstore API](https://api.itbook.store/) to collect books
- Uses secured Backend REST Service API to manage reviews
- Uses Token-based Authentication at user login to access protected resources (Admin Panel)
- Includes Components for CRUD operations

### Backend Specifications
- Implemented using Spring Boot (with Spring Security, Spring Web, Spring Data JPA)
- Supports a REST API that can process GET / POST / PUT / DELETE operations
- Secured through Token-based Authentication (JWT)
- Uses MySql as a SQL database
- Uses Maven as a build tool
- Uses MVC architecture
- Uses Apache Tomcat as a web server
