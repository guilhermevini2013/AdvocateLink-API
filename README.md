Advocate Link API
This repository contains the backend API for managing roles in an advocate link system. The API provides endpoints for listing roles, inserting a new role, updating an existing role, and deleting a role.

RoleDTO
The API uses a Data Transfer Object (DTO) called RoleDTO to represent role information. Below is the structure of the RoleDTO:

java
Copy code
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
Endpoints
1. List Roles
Endpoint: GET /advocateLink/api/v1/role
Description: Retrieves a paginated list of roles.
Parameters:
pages (optional, default: 0)
linesPerPages (optional, default: 15)
direction (optional, default: "ASC")
orderBy (optional, default: "id")
2. Insert Role
Endpoint: POST /advocateLink/api/v1/role
Description: Creates a new role.
Example Request:
Method: POST
Headers: Content-Type: application/json
Body:
json
Copy code
{
  "name_Role": "Legal Assistant"
}
3. Update Role
Endpoint: PUT /advocateLink/api/v1/role/{id}
Description: Updates an existing role.
Example Request:
Method: PUT
Headers: Content-Type: application/json
Body:
json
Copy code
{
  "name_Role": "Updated Legal Assistant"
}
4. Delete Role
Endpoint: DELETE /advocateLink/api/v1/role/{id}
Description: Deletes an existing role.
HTTP Status Codes
200 OK: Successful operation (for successful GET and PUT requests).
201 Created: Successful creation (for successful POST requests).
204 No Content: Successful deletion (for successful DELETE requests).
400 Bad Request: Malformed request or invalid input.
404 Not Found: Resource not found.
500 Internal Server Error: Unexpected server error.
