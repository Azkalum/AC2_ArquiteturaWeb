<h1 align="center"> AC2 - Arquitetura Web </h1>

Nosso projeto se concentra em oferecer uma solução de controle de despesas pessoais intuitiva e prática. Criamos um aplicativo que se torna parte integrante do seu cotidiano, ajudando você a administrar suas finanças com facilidade e, consequentemente, economizar dinheiro. 

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/downloads/installer/)

## Configuração do Projeto

1. **Clone este repositório em seu sistema local**:

   ```bash
   git clone https://github.com/Azkalum/AC2_ArquiteturaWeb.git
   ```

2. **Configuração do banco de dados em**: 
src/main/resources/application.properties:
   ```bash
      spring.datasource.url=jdbc:mysql:// localhost:3306/AC2_
      spring.datasource.username=seu-usuario
      spring.datasource.password=sua-senha
   ```

3. **Build do projeto:**:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
## Documentação
Você pode encontrar a documentação da API em localhost:8080/swagger-ui para abrir o Swagger.

## Contato
Se você tiver alguma dúvida ou precisar de assistência, entre em contato via email em: 
- [Contato CoinTrack](devcointrack@outlook.com)

## Faculdade

Centro universitário Facens, curso: Análise e Desenvolvimento de Sistemas

      
