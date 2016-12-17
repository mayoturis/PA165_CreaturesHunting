#REST API
Implementation of REST API for Weapon entity
### Rest Url
> /pa165/rest

### Entity Url
> /weapon

### GET /weapon
> returns all weapons

### GET /weapon/{id}
> returns weapon with id

### POST /weapon
> creates new weapon

* Requested body
  * Name : String

### DELETE /weapon/{id}
> deletes weapon with id

## Example

Using external program, e.g. curl
> curl -i http://localhost:8080/pa165/rest/weapon/
