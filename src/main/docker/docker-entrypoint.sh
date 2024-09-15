#!/bin/sh
set -e

echo 'Starting default Spring Boot App'

if [ "$DEBUG" = true ]; then
  printf "Running the application in debug mode\n"
  JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:$DEBUG_PORT"
fi
mkdir -p tauseef
# Enables application to take PID 1 and receive SIGTERM sent by Docker stop command.
# See here https://docs.docker.com/engine/reference/builder/#/entrypoint
exec java $JAVA_OPTS \
       -Djava.security.egd=file:/dev/./urandom -jar \
       ${APP_HOME}/$ARTIFACT_NAME
