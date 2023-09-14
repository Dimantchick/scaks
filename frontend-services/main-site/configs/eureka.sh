#!/bin/bash
if [ -e /eureka/eureka-payload.json ]
then
  echo "First run."
  IP_ADDR_RESULT=$(hostname -i);
  HOSTNAME_NAME=$(hostname);
  echo "Find IP ${IP_ADDR_RESULT}";
  export IP_ADDR=${IP_ADDR_RESULT};
  echo "Find hostname ${HOSTNAME_NAME}";
  export HOSTNAME=${HOSTNAME_NAME};
  envsubst '${HOSTNAME} ${HOSTNAME} ${IP_ADDR}' < /eureka/eureka-payload.json > /eureka/payload.json;
  rm -f /eureka/eureka-payload.json;
  echo "Payload prepared"
  RESULT=curl -i --request POST --header "Content-Type: application/json" --data @payload.json http://$EUREKA_USERNAME:$EUREKA_PASSWORD@$EUREKA_SERVER_HOST/eureka/apps/main-site | grep error;
  if [ $RESULT ]
  then
    echo "Error curl: ${RESULT}"
    cp /eureka/payload.json /eureka/eureka-payload.json
    exit 1;
  fi;
else
  curl -i --request PUT http://$EUREKA_USERNAME:$EUREKA_PASSWORD@$EUREKA_SERVER_HOST/eureka/apps/main-site/${HOSTNAME}:main-site:8080 || ERROR=true;
fi;


