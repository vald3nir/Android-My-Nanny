# Server


## To start the local database

    docker run -v ~/docker --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=dev -e MONGO_INITDB_ROOT_PASSWORD=1721 mongo
   
## APIs


### Create new user

    curl --location --request POST 'http://127.0.0.1:5000/users' \
    --header 'Authorization: Basic VmFsZGVuaXI6MTIzNDU2Nzg=' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "vald3nir",
        "password": "12345678",
        "email": "vald3nir@gmail.com",
        "language": "pt"
    }'
    
### List all users

    curl --location --request GET 'http://127.0.0.1:5000/users' \
    --header 'Authorization: Basic VmFsZGVuaXI6MTIzNDU2Nzg='

