## Run local image

###Install SqlServer service
```shell
mkdir nova-db
docker run  --restart=always \
      -p 1433:1433 \
      --name sql_server_express_nova \
      --mount type=bind,source="$(pwd)"/nova-db,target=/var/opt/mssql/data \
      --env ACCEPT_EULA=Y \
      --env SA_PASSWORD=esgi_al41 \
      --env MSSQL_PID=Express \
      mcr.microsoft.com/mssql/server:2017-latest
```
###Install Api instance
```shell
mkdir nova-api-upload
docker build -f docker/Dockerfile.prod . -t nova/nova-api 
docker run -p 8002:8001 \
      --name nova-api \
      --mount type=bind,source="$(pwd)"/nova-api-upload,target=/app/upload \
      nova/nova-api
```

