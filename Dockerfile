# JDK Sürümü
FROM openjdk:17

#Bilgilendirme
LABEL authors="kubizao"

#Persist Data (Kalıcı Veri)
VOLUME /tmp

#Port
EXPOSE 4444

ARG JAR_FILE=/target/*.jar

#Değişkeni ekle
ADD ${JAR_FILE} blog_react

ENTRYPOINT ["java", "-jar", "/blog_react"]