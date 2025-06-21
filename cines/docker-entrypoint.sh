#!/bin/sh

echo "Esperando que MySQL esté disponible..."

until nc -z -v -w30 $DB_HOST $DB_PORT
do
  echo "Esperando conexión a MySQL en $DB_HOST:$DB_PORT..."
  sleep 2
done

echo "MySQL está listo, arrancando la app"
exec java -jar app.jar
