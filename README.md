# alpha-bank-test-task

## Build and run process
### Run server
```bash
cd <path-to-repository>/demo
./gradlew bootRun
```

### Run client
```bash
cd <path-to-repository>/dollar-gif-client
ng serve
```

### Docker container

[Docker hub repo](https://hub.docker.com/repository/docker/lulufw01/alpha-bank-test-task)

Run docker compose

```bash
cd <path-to-repository>/demo
./gradlew build
cd ..
sudo docker-compose up --build -d
```
