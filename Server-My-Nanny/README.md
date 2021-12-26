# Server


## To start the local database

    docker run -v ~/docker --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=dev -e MONGO_INITDB_ROOT_PASSWORD=1721 mongo
   
## APIs


### Create new user

    parameters:
    
    curl --location --request POST 'http://127.0.0.1:5000/users' \
    --header 'Authorization: Basic VmFsZGVuaXI6MTIzNDU2Nzg=' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "vald3nir",
        "password": "11111111",
        "email": "dev@gmail.com",
        "language": "pt"
    }'
    
### Update password

    curl --location --request PUT 'http://127.0.0.1:5000/update_password?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidXNlcl9lN2U1MjE5My0wNzZmLTQ5MDAtOWYyNS0xYzUyZjcyZjY3N2UiLCJleHAiOjE2NDA1Mzc5MjV9.JAczfolRuLz5-4YVZRgj3WDlLIiiOj0_lM4W2WdDg00' \
    --header 'Authorization: Basic VmFsZGVuaXI6MTIzNDU2Nzg=' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "password": "1234567890",
        "new_password": "11111111",
        "language": "pt"
    }'
    
### List all users

    curl --location --request GET 'http://127.0.0.1:5000/users' \
    --header 'Authorization: Basic VmFsZGVuaXI6MTIzNDU2Nzg='
    
### Login

    curl --location --request POST 'http://127.0.0.1:5000/login' \
    --header 'Authorization: Basic VmFsZGVuaXI6MTIzNDU2Nzg=' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "password": "1234567890",
        "email": "dev@gmail.com",
        "language": "pt"
    }'


