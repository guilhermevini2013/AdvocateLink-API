<!DOCTYPE html>
</head>
<body>

  <h1>Advocate Link API - Role Controller</h1>

  <p>This documentation provides details for the <code>Role</code> management API, specifically focusing on the <code>RoleController</code>. The API allows you to list roles, insert a new role, update an existing role, and delete a role.</p>

  <h2>Endpoints</h2>

  <h3>1. List Roles</h3>

  <p><strong>Endpoint</strong>: <code>GET /advocateLink/api/v1/role</code></p>

  <p><strong>Description</strong>: Retrieves a paginated list of roles.</p>

  <p><strong>Parameters</strong>:</p>
  <ul>
    <li><code>pages</code> (optional, default: 0): Page number</li>
    <li><code>linesPerPages</code> (optional, default: 15): Number of lines per page</li>
    <li><code>direction</code> (optional, default: "ASC"): Sorting direction (ASC or DESC)</li>
    <li><code>orderBy</code> (optional, default: "id"): Field to order by</li>
  </ul>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>

  <p><strong>Example Response</strong>:</p>

  <pre>
  {
    "content": [
      {
        "id": 1,
        "name_Role": "Legal Counsel"
      },
      {
        "id": 2,
        "name_Role": "Senior Legal Counsel"
      }
    ],
    "totalPages": 1,
    "totalElements": 2,
    "size": 15,
    "number": 0,
    "numberOfElements": 2,
    "first": true,
    "last": true,
    "empty": false
  }
  </pre>

  <h3>2. Insert Role</h3>

  <p><strong>Endpoint</strong>: <code>POST /advocateLink/api/v1/role</code></p>

  <p><strong>Description</strong>: Creates a new role.</p>
  <p>HTTP Status Code: <code>201 Created</code></p>

  <p><strong>Example Request</strong>:</p>

  <pre>
  {
    "name_Role": "Legal Assistant"
  }
  </pre>

  <p><strong>Example Response</strong>:</p>

  <pre>
  {
    "id": {new_role_id},
    "name_Role": "Legal Assistant"
  }
  </pre>

  <h3>3. Update Role</h3>

  <p><strong>Endpoint</strong>: <code>PUT /advocateLink/api/v1/role/{id}</code></p>

  <p><strong>Description</strong>: Updates an existing role.</p>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>
  <p>HTTP Status Code: <code>404 Not Found</code></p>

  <p><strong>Example Request</strong>:</p>

  <pre>
  {
    "name_Role": "Updated Legal Assistant"
  }
  </pre>

  <p><strong>Example Response</strong>:</p>

  <pre>
  {
    "id": {existing_role_id},
    "name_Role": "Updated Legal Assistant"
  }
  </pre>

  <h3>4. Delete Role</h3>

  <p><strong>Endpoint</strong>: <code>DELETE /advocateLink/api/v1/role/{id}</code></p>

  <p><strong>Description</strong>: Deletes an existing role.</p>

  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>204 No Content</code></p>
  <p>HTTP Status Code: <code>404 Not Found</code></p>

  <h2>HTTP Status Codes</h2>

  <ul>
    <li><strong>200 OK</strong>: Successful operation (for successful GET and PUT requests).</li>
    <li><strong>201 Created</strong>: Successful creation (for successful POST requests).</li>
    <li><strong>204 No Content</strong>: Successful deletion (for successful DELETE requests).</li>
    <li><strong>400 Bad Request</strong>: Malformed request or invalid input.</li>
    <li><strong>404 Not Found</strong>: Resource not found.</li>
  </ul>

  <h1>Advocate Link API - Employee Controller</h1>

  <p>This documentation provides details for the <code>Employee</code> management API, specifically focusing on the <code>EmployeeController</code>. The API allows you to retrieve employee statistics, list employees, find an employee by ID, insert a new employee, update an existing employee, and delete an employee.</p>

  <h2>Endpoints</h2>

  <h3>1. Employee Statistics</h3>

  <p><strong>Endpoint</strong>: <code>GET /advocateLink/api/v1/employee/statistics</code></p>

  <p><strong>Description</strong>: Retrieves statistics related to employees.</p>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>
  <p><strong>Example Response</strong>:</p>

  <pre>
  {
    "totalEmployees": 100,
    "averageSalary": 50000.0
  }
  </pre>

  <h3>2. List Employees</h3>

  <p><strong>Endpoint</strong>: <code>GET /advocateLink/api/v1/employee</code></p>

  <p><strong>Description</strong>: Retrieves a paginated list of employees.</p>

  <p><strong>Parameters</strong>:</p>
  <ul>
    <li><code>pages</code> (optional, default: 0): Page number</li>
    <li><code>linesPerPages</code> (optional, default: 15): Number of lines per page</li>
    <li><code>direction</code> (optional, default: "ASC"): Sorting direction (ASC or DESC)</li>
    <li><code>orderBy</code> (optional, default: "name"): Field to order by</li>
  </ul>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>

  <p><strong>Example Response</strong>:</p>

  <pre>
  {
    "content": [
      {
        "id": 1,
        "name": "John Doe",
        "cpf": "123.456.789-01",
        "addresses": [...],
        "contacts": [...],
        "urlPhoto": "https://example.com/photo.jpg",
        "role_id": {
          "id": 2,
          "name_Role": "Senior Legal Counsel"
        },
        "salary": 75000.0
      },
      // Additional employees...
    ],
    "totalPages": 2,
    "totalElements": 30,
    "size": 15,
    "number": 0,
    "numberOfElements": 15,
    "first": true,
    "last": false,
    "empty": false
  }
  </pre>

  <h3>3. Find Employee by ID</h3>

  <p><strong>Endpoint</strong>: <code>GET /advocateLink/api/v1/employee/{id}</code></p>

  <p><strong>Description</strong>: Retrieves details of a specific employee by ID.</p>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>
  <p>HTTP Status Code: <code>404 Not Found</code></p>
  <p><strong>Example Response</strong>:</p>

  <pre>
  {
    "id": 1,
    "name": "John Doe",
    "cpf": "123.456.789-01",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/photo.jpg",
    "role_id": {
      "id": 2,
      "name_Role": "Senior Legal Counsel"
    },
    "salary": 75000.0
  }
  </pre>

  <h3>4. Insert Employee</h3>

  <p><strong>Endpoint</strong>: <code>POST /advocateLink/api/v1/employee</code></p>

  <p><strong>Description</strong>: Creates a new employee.</p>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>201 CREATED</code></p>
  
  <p><strong>Example Request</strong>:</p>
  
  <pre>
  {
    "name": "Jane Smith",
    "cpf": "987.654.321-09",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/jane-photo.jpg",
    "role_id": {
      "id": 1,
      "name_Role": "Legal Counsel"
    },
    "salary": 60000.0
  }
  </pre>

  <p><strong>Example Response</strong>:</p>

  <pre>
  {
    "id": {new_employee_id},
    "name": "Jane Smith",
    "cpf": "987.654.321-09",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/jane-photo.jpg",
    "role_id": {
      "id": 1,
      "name_Role": "Legal Counsel"
    },
    "salary": 60000.0
  }
  </pre>

  <h3>5. Update Employee</h3>

  <p><strong>Endpoint</strong>: <code>PUT /advocateLink/api/v1/employee/{id}</code></p>

  <p><strong>Description</strong>: Updates details of an existing employee.</p>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>
  <p>HTTP Status Code: <code>400 BAD REQUEST</code></p>
  <p><strong>Example Request</strong>:</p>

  <pre>
  {
    "name": "Updated John Doe",
    "cpf": "123.456.789-01",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/updated-photo.jpg",
    "role_id": {
      "id": 2,
      "name_Role": "Senior Legal Counsel"
    },
    "salary": 80000.0
  }
  </pre>

  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>

  <h3>6. Delete Employee</h3>

  <p><strong>Endpoint</strong>: <code>DELETE /advocateLink/api/v1/employee/{id}</code></p>

  <p><strong>Description</strong>: Deletes an existing employee.</p>
  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>204 No Content</code></p>
  <p>HTTP Status Code: <code>404 Not Found</code></p>

  <h2>HTTP Status Codes</h2>

  <ul>
    <li><strong>200 OK</strong>: Successful operation (for successful GET and PUT requests).</li>
    <li><strong>201 Created</strong>: Successful creation (for successful POST requests).</li>
    <li><strong>204 No Content</strong>: Successful deletion (for successful DELETE requests).</li>
    <li><strong>400 Bad Request</strong>: Malformed request or invalid input.</li>
    <li><strong>404 Not Found</strong>: Resource not found.</li>
  </ul>
  </pre>

<h1>Advocate Link API - Client Service</h1>

  <p>This documentation provides details for the <code>Client</code> management API, specifically focusing on the <code>ClientService</code>. The API allows you to insert a new client, update an existing client, delete a client, list clients, and find a client by ID.</p>

  <h2>Endpoints</h2>

  <h3>1. Insert Client</h3>

  <p><strong>Endpoint</strong>: <code>POST /advocateLink/api/v1/client</code></p>

  <p><strong>Description</strong>: Creates a new client.</p>

  <p><strong>Example Request</strong>:</p>

  <pre>
  {
    "name": "John Doe",
    "cpf": "123.456.789-01",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/photo.jpg",
    "role_id": {
      "id": 2,
      "name_Role": "Senior Legal Counsel"
    },
    "oab": "XYZ123"
  }
  </pre>

  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>201 Created</code></p>

  <pre>
  {
    "id": {new_client_id},
    "name": "John Doe",
    "cpf": "123.456.789-01",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/photo.jpg",
    "role_id": {
      "id": 2,
      "name_Role": "Senior Legal Counsel"
    },
    "oab": "XYZ123"
  }
  </pre>

  <h3>2. Update Client</h3>

  <p><strong>Endpoint</strong>: <code>PUT /advocateLink/api/v1/client/{id}</code></p>

  <p><strong>Description</strong>: Updates details of an existing client.</p>

  <p><strong>Example Request</strong>:</p>

  <pre>
  {
    "name": "Updated John Doe",
    "cpf": "123.456.789-01",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/updated-photo.jpg",
    "role_id": {
      "id": 2,
      "name_Role": "Senior Legal Counsel"
    },
    "oab": "ABC456"
  }
  </pre>

  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>
  <p>HTTP Status Code: <code>404 Not Found</code></p>
  <h3>3. Delete Client</h3>

  <p><strong>Endpoint</strong>: <code>DELETE /advocateLink/api/v1/client/{id}</code></p>

  <p><strong>Description</strong>: Deletes an existing client.</p>

  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>204 No Content</code></p>
  <p>HTTP Status Code: <code>404 Not Found</code></p>

  <h3>4. List Clients</h3>

  <p><strong>Endpoint</strong>: <code>GET /advocateLink/api/v1/client</code></p>

  <p><strong>Description</strong>: Retrieves a paginated list of clients.</p>

  <p><strong>Parameters</strong>:</p>
  <ul>
    <li><code>page</code> (optional, default: 0): Page number</li>
    <li><code>linesPerPage</code> (optional, default: 15): Number of lines per page</li>
    <li><code>direction</code> (optional, default: "ASC"): Sorting direction ("ASC" or "DESC")</li>
    <li><code>orderBy</code> (optional, default: "name"): Sorting field</li>
  </ul>

  <p><strong>Example Request</strong>: <code>GET /advocateLink/api/v1/client?page=0&amp;linesPerPage=10&amp;direction=ASC&amp;orderBy=name</code></p>

  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>
  <pre>
  {
    "content": [
      {
        "id": 1,
        "name": "John Doe",
        "cpf": "123.456.789-01",
        "addresses": [...],
        "contacts": [...],
        "urlPhoto": "https://example.com/photo.jpg",
        "role_id": {
          "id": 2,
          "name_Role": "Senior Legal Counsel"
        },
        "oab": "ABC456"
      },
      // ... additional clients ...
    ],
    "totalElements": 25,
    "totalPages": 3,
    "number": 0,
    "size": 10
  }
  </pre>

  <h3>5. Find Client by ID</h3>

  <p><strong>Endpoint</strong>: <code>GET /advocateLink/api/v1/client/{id}</code></p>

  <p><strong>Description</strong>: Retrieves details of a specific client by ID.</p>

  <p><strong>Example Response</strong>:</p>
  <p>HTTP Status Code: <code>200 OK</code></p>
  <p>HTTP Status Code: <code>404 Not Found</code></p>
  <pre>
  {
    "id": 1,
    "name": "John Doe",
    "cpf": "123.456.789-01",
    "addresses": [...],
    "contacts": [...],
    "urlPhoto": "https://example.com/photo.jpg",
    "role_id": {
      "id": 2,
      "name_Role": "Senior Legal Counsel"
    },
    "oab": "ABC456"
  }
  </pre>

  <h2>HTTP Status Codes</h2>

  <ul>
    <li><strong>200 OK</strong>: Successful operation (for successful GET and PUT requests).</li>
    <li><strong>201 Created</strong>: Successful creation (for successful POST requests).</li>
    <li><strong>204 No Content</strong>: Successful deletion (for successful DELETE requests).</li>
    <li><strong>400 Bad Request</strong>: Malformed request or invalid input.</li>
    <li><strong>404 Not Found</strong>: Resource not found.</li>
  </ul>


