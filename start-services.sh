#!/bin/bash
set -e

BASE_DIR="$(pwd)"

SERVICES=(
  "Package-Service"
  "Package-Sorting"
  "Package-Transport"
  "Package-Delivery"
  "Package-Notification"
)

for SERVICE in "${SERVICES[@]}"; do
  JAR_FILE=$(find "$BASE_DIR/backend/$SERVICE/target" -maxdepth 1 -name "*.jar" | head -n 1)

  if [ -z "$JAR_FILE" ]; then
    echo "❌ No JAR found for $SERVICE, did you run build-services.sh?"
    continue
  fi

  echo "🚀 Starting $SERVICE..."
  java -jar "$JAR_FILE" > "$BASE_DIR/backend/$SERVICE/$SERVICE.log" 2>&1 &
done

echo "✅ All services started in background!"
