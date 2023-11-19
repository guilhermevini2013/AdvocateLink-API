Advocate Link API - Role Controller
This documentation provides details for the Role management API, specifically focusing on the RoleController. The API allows you to list roles, insert a new role, update an existing role, and delete a role.

Endpoints
1. List Roles
Endpoint: GET /advocateLink/api/v1/role
Description: Retrieves a paginated list of roles.
Parameters:
pages (optional, default: 0): Page number
linesPerPages (optional, default: 15): Number of lines per page
direction (optional, default: "ASC"): Sorting direction (ASC or DESC)
orderBy (optional, default: "id"): Field to order by
Example Response:
Status Code: 200 OK
Content:
**
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
**

