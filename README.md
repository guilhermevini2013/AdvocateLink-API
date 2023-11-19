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

  <h2>HTTP Status Codes</h2>

  <ul>
    <li><strong>200 OK</strong>: Successful operation (for successful GET and PUT requests).</li>
    <li><strong>201 Created</strong>: Successful creation (for successful POST requests).</li>
    <li><strong>204 No Content</strong>: Successful deletion (for successful DELETE requests).</li>
    <li><strong>400 Bad Request</strong>: Malformed request or invalid input.</li>
    <li><strong>404 Not Found</strong>: Resource not found.</li>
  </ul>



