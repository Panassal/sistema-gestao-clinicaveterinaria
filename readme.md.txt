====================================================================
SISTEMA DE GESTÃO DE CLÍNICA VETERINÁRIA
Aluno: Tobias Panassal | RA: 14176173-24
====================================================================

1. VISÃO GERAL DO PROJETO
Aplicação web full-stack desenvolvida para o controle de pacientes (animais), registro de atendimentos/serviços clínicos e consulta de histórico com cálculo automático de previsão de próximo retorno.

2. TECNOLOGIAS UTILIZADAS
- Back-end: Java 22, Spring Boot (Spring Web, Spring Data JPA)
- Banco de Dados: H2 Database (In-Memory)
- Front-end: HTML5, CSS3 e JavaScript (Fetch API)
- Gerenciador de Dependências: Maven

3. ESTRUTURA DO PACOTE DE ENTREGA
- frontend/         -> Código da interface web (index.html)
- backend/          -> Código-fonte da API REST em Java/Spring Boot (src/ e pom.xml)
- database/         -> Script SQL de estrutura das tabelas (schema.sql)
- documentacao.pdf  -> Documentação técnica e evidências de funcionamento (prints)
- README.txt        -> Este guia de execução

4. COMO EXECUTAR O SISTEMA LOCALMENTE
1. Abra a pasta "backend" em uma IDE Java (IntelliJ IDEA, Eclipse ou VS Code), importando como projeto Maven através do arquivo "pom.xml".
2. Aguarde o download automático das dependências pelo Maven.
3. Localize e execute a classe principal:
   src/main/java/br/com/clinica/SistemaClinicaVeterinariaApplication.java
4. Aguarde a mensagem "Tomcat started on port 8080" no console da IDE.
5. Abra o navegador de sua preferência e acesse:
   http://localhost:8080

5. ACESSO AO CONSOLE DO BANCO DE DADOS (H2)
O banco de dados relacional é criado automaticamente em memória ao iniciar o sistema. Para inspecionar as tabelas ANIMAL e SERVICO durante a execução:
- URL de acesso: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:clinicadb
- User Name: sa
- Password: (deixar em branco)

6. REGRAS DE NEGÓCIO IMPLEMENTADAS (ALERTA DE RETORNO)
- Serviços que contêm "Vacina" no tipo: previsão automática de reforço em +365 dias.
- Demais serviços (Consulta, Exame, etc.): previsão automática de retorno em +30 dias.
- Etiquetas visuais na tabela de histórico:
  * Verde (Em dia): mais de 7 dias restantes para o retorno.
  * Amarelo (Retorno próximo): 7 dias ou menos para o retorno.
  * Vermelho (Atrasado): data de retorno já ultrapassada.
====================================================================