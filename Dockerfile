# Estágio 1: Build da Aplicação com Maven
# Usamos uma imagem que já vem com Java 17 e Maven instalados
FROM eclipse-temurin:21-jdk-jammy as builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do Maven Wrapper para o container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Copia o código fonte do projeto
COPY src ./src

# Executa o comando para compilar o projeto e gerar o arquivo .jar
# -DskipTests pula os testes para acelerar o build no deploy
RUN ./mvnw clean package -DskipTests


# Estágio 2: Imagem Final de Execução
# Usamos uma imagem JRE (Java Runtime Environment), que é menor e mais segura
FROM eclipse-temurin:17-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build para a imagem final
COPY --from=builder /app/target/grupo_estudos-0.0.1-SNAPSHOT.jar ./app.jar

# Expõe a porta 8080, que é a porta que o Spring Boot usa por padrão
EXPOSE 8080

# Comando para iniciar a aplicação quando o container for executado
ENTRYPOINT ["java", "-jar", "app.jar"]