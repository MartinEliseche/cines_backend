#!/bin/sh

echo "Esperando a que MySQL esté disponible en $DB_HOST:$DB_PORT..."

while ! nc -z "$DB_HOST" "$DB_PORT"; do
  sleep 1
done

echo "MySQL disponible. Iniciando la app."

exec java -jar app.jar
