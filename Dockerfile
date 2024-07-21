# Use a imagem base oficial do Maven para construir a aplicação
FROM ubuntu:latest

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Atualize os pacotes e instale o Maven
RUN apt-get update && apt-get install -y maven openjdk-17-jdk

# Defina JAVA_HOME para garantir que o Maven use o JDK correto
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
RUN export JAVA_HOME

# Copie o arquivo pom.xml e as dependências
COPY Banking-System-Back/pom.xml .
COPY Banking-System-Back/src ./src

# Compile o projeto e construa o JAR
RUN mvn dependency:resolve
RUN mvn clean package

# Copie o JAR da fase de build para a fase de runtime
COPY Banking-System-Back/target/*.jar app.jar

# Exponha a porta 8080 para acessar a aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
