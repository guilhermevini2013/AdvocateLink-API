<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Advocate Link API - Role Controller</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      line-height: 1.6;
      margin: 20px;
    }

    h1 {
      color: #333;
    }

    h2 {
      color: #555;
    }

    code {
      background-color: #f4f4f4;
      padding: 2px 4px;
      border: 1px solid #ddd;
      font-family: 'Courier New', Courier, monospace;
    }

    pre {
      background-color: #f8f8f8;
      padding: 10px;
      border: 1px solid #ddd;
      white-space: pre-wrap;
    }

    table {
      border-collapse: collapse;
      width: 100%;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }

    th {
      background-color: #f2f2f2;
    }

    p {
      margin-bottom: 15px;
    }
  </style>
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

  <h2>RoleDTO</h2>

  <pre>
  <code>
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public class RoleDTO {
      @JsonIgnore
      private Long id;
      private String name_Role;

      public RoleDTO(Role entity){
          this.id = entity.getId();
          this.name_Role = entity.getName_Role();
      }
  }
  </code>
  </pre>


