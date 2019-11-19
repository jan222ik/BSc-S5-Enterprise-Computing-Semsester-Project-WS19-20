FROM openjdk:8
ADD core/target/core-1.0-jar-with-dependencies.jar core.jar
EXPOSE 2345
ENTRYPOINT ["java", "-cp", "core.jar", "at.fhv.itb17.s5.teamb.core.domain.general.CoreMain"]
