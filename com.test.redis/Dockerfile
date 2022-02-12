FROM openjdk:8
ADD target/redisUtils.jar redisUtils.jar
RUN bash -c 'touch /redisUtils.jar'
ENTRYPOINT ["java", "-jar", "redisUtils.jar", "com.task.redis.DataProcessor"]