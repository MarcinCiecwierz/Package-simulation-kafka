## DB

```bash
docker run -itd --name package-postgres -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -d postgres
```

## Kafka

```bash
docker pull apache/kafka:4.0.0

docker run -p 9092:9092 apache/kafka:4.0.0
```
