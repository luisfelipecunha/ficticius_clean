FROM adoptopenjdk/openjdk16:latest
WORKDIR /spring

ADD ./build/libs/ficticiusclean-0.0.1-SNAPSHOT.jar /spring/ficticiusclean-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/spring/ficticiusclean-0.0.1-SNAPSHOT.jar"]