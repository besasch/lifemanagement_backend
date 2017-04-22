FROM frolvlad/alpine-oraclejdk8:slim

# ARG buildDate
# ARG version

# LABEL com.aucobo.build-date=$buildDate \
LABEL com.aucobo.name="aucobo-frontend" \
          com.aucobo.description="aucobo experienced in the webbrowser by the users and clients." \
          com.aucobo.url="https://www.aucobo.de" \
          # com.aucobo.version=$version \
          com.aucobo.vendor="aucobo GmbH" \
          com.aucobo.maintainer="norman.moeschter-schenck@aucobo.de"

ARG profile="gateway"
ENV PROFILE $profile

ARG port=8090
ENV PORT $port

ARG protocol="http"
ENV PROTOCOL $protocol

ARG restEndpointVersion="v1"
ENV RESTENDPOINTVERSION $restEndpointVersion

# if debug gatewyHost needs to be empty --> TODO 2017/03 Norman
ARG gatewayHost="localhost"
ARG gatewayPort=8079
ENV GATEWAY_HOST $gatewayHost
ENV GATEWAY_PORT $gatewayPort
ENV GATEWAY $PROTOCOL://$GATEWAY_HOST:$GATEWAY_PORT/$RESTENDPOINTVERSION

ARG eurekaHost="aucobo-eureka"
ARG eurekaPort=8761
ENV EUREKA_HOST $eurekaHost
ENV EUREKA_PORT $eurekaPort
ARG eurekaEndpoint="/eureka/"
ENV EUREKA_ENDPOINT $eurekaEndpoint
ENV EUREKA $PROTOCOL://$EUREKA_HOST:$EUREKA_PORT




ARG protocol_mongo="mongodb"
ENV PROTOCOL_MONGO $protocol_mongo

ARG mongoHost="aucobo-mongo"
ARG mongoPort=27017
ENV MONGO_HOST $mongoHost
ENV MONGO_PORT $mongoPort
ENV MONGODB $PROTOCOL_MONGO://$MONGO_HOST:$MONGO_PORT

ENV JAVA_OPTS ""

WORKDIR /data

COPY frontend.war /data/app.war
# add file modification time
RUN sh -c 'touch /data/app.war'

# COPY img /data/
VOLUME ["/data/img"]

EXPOSE $PORT

ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -Dspring.profiles.active=$PROFILE -Deureka.client.serviceUrl.defaultZone=$PROTOCOL://$EUREKA_HOST:$EUREKA_PORT$EUREKA_ENDPOINT -DgatewayHost=$PROTOCOL://$GATEWAY_HOST:$GATEWAY_PORT/$RESTENDPOINTVERSION/ -Dspring.data.mongodb.uri=$PROTOCOL_MONGO://$MONGO_HOST:$MONGO_PORT -Djava.security.egd=file:/dev/./urandom -Xms256m -Xmx512m -server $JAVA_OPTS -jar ./app.war"]

## using environment:property in docker-compose.yml
##ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://${MONGO_URI_DOCKER}", "-Djava.security.egd=file:/dev/./urandom", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,suspend=n", "-jar","./target/interfacemanager.jar"]
## overwriting mongo.uri directly
##ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb:27017", "-Djava.security.egd=file:/dev/./urandom", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,suspend=n", "-jar","./target/interfacemanager.jar"]
## setting parameter
#ENTRYPOINT ["java", "-DGATEWAY_URL=http://54.93.104.166:8079", "-Djava.security.egd=file:/dev/./urandom", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,suspend=n", "-jar","./target/frontend.war"]

#ENTRYPOINT ["java","-DMONGO_URL=mongodb:27017", "-DGATEWAY_URL=http://gateway:8079", "-Dspring.profiles.active=gateway", "-Djava.security.egd=file:/dev/./urandom", "-Xdebug", "-Xrunjdwp:server=y,transport=dt_socket,suspend=n", "-jar","./target/frontend.war"]

# ENTRYPOINT ["java", "-DGATEWAY_URL=http://gateway:8079", "-Dspring.profiles.active=gateway", "-Djava.security.egd=file:/dev/./urandom", "-jar","./target/frontend.war"]

# EXPOSE 8090
