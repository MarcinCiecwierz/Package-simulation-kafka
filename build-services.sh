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
  echo "🔨 Building $SERVICE..."
  cd "$BASE_DIR/backend/$SERVICE"
  ./mvnw clean package -DskipTests
done

echo "✅ All services built!"