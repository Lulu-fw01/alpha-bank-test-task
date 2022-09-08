# alpha-bank-test-task

## Build and run process
### Run server
```bash
cd <path-to-repository>/demo
./gradlew bootRun
```

### Run client
```bash
cd <path-to-repository>/client
flutter run -d web-server --web-port 4200
```

### Docker compose
Run docker compose

```bash
cd <path-to-repository>/demo
./gradlew build
cd ..
sudo docker-compose up --build -d
```
