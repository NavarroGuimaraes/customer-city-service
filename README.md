<img alt="Desafio" src="https://lh3.googleusercontent.com/proxy/LR8DraROLA6-ISQn57H_U6ITY2lYY0ODtTWbbSu2T5kD9vwnAAEBK_wwbDk7Tn_CevmXRGH8mX56Ybgf_enISuzvnFWkrcQUmS9h2GjCfzBUHQbWiw" />

<h3 align="center">
  Customers City Service
</h3>

<blockquote align="center">“Só deseje as coisas as quais você está disposto a lutar”!</blockquote>

<p align="center">
  <a href="#rocket-sobre-o-desafio">Sobre o desafio</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#preparando-o-ambiente">Ambiente</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#executando-a-api">Executando a API</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#rotas-da-aplicação">Principais Rotas</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#banco-de-dados">Banco de dados</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-licença">Licença</a>
</p>

## :rocket: Sobre o desafio

Nesse desafio, desenvolvi uma API capaz cadastrar cidades e clientes. Toda a API foi construída utilizando o ecossistema Spring. 

### Base do desafio

Todo a API foi desenvolvida com base nos requisitos descritos no repositório disponível na seguinte url: **[Acessar Repositório](https://github.com/gustavodallanora/spring-boot-interview)**.

### Preparando o ambiente

Antes de tudo, é importante ter instalado o **[Maven](http://maven.apache.org/download.cgi)** e no mínimo a versão 8 do **[Java JDK](https://openjdk.java.net/install/)** instalada em sua máquina. Para checar se ambas estão instaladas, é possível executar os comandos a seguir para verificar as versões do java: `java -version` e maven: `mvn -version`.

também é recomendável possuir instalada uma IDE. Durante o desenvolvimento dessa aplicação fiz uso do **[STS](https://spring.io/tools)**, mas você pode utilizar a IDE de sua preferência.

### Executando a API

Antes de executarmos a API, vamos executar o comando `mvn clean install` na pasta onde está o **pom.xml** do projeto. Com esse comando, as dependências necessárias serão instaladas e poderemos inicializar a API.

Para iniciar a API nós podemos fazer uso de nossa IDE. 
No STS, você pode executar pelo seguinte caminho **customer-city-service > navegue pelas pastas src/main/java > Clique com o botão direito em CustomerCityServiceApplication.java > Vá até o menu Run as > Selecione a opção Spring boot app**. 

Alternativamente, você pode executar através da View **Boot Dashboard** disponível no sts. Caso não esteja disponível em seu STS, **Vá até Window > Show View > Other > Digite Boot Dashboard e o selecione**. Após isso o boot dashboard irá estar disponível e exibitá todos os serviços disponíveis para serem inicializados. 

Caso você prefira executar pela linha de comando, você pode executar a API rodando `mvn spring-boot:run` dentro da pasta do projeto.

### Rotas da aplicação

Após a execução do projeto, a documentação das rotas estará disponível em **`http://localhost:8080/swagger-ui.html`**. Porém, aqui vai um breve resumo das principais rotas existentes no projeto:

- **`POST /city`**: Essa rota irá criar uma cidade recebendo o objeto exibido abaixo e retornará os dados caso os mesmos tenham sido criados sem o id.

```json
{
  "name": "Recife",
  "state": "Pernambuco"
}
```

- **`POST /customer`**: Essa rota irá criar um cliente recebendo o objeto descrito abaixo e retornará os dados caso os mesmos tenham sido criados. ⚠️ Os gêneros disponíveis vão de um array de 0 a 4. São eles consecutivamente: Homem, Mulher, Homem trans, Mulher trans, não identificar; ⚠️

```json
{
    "name": "Anderson Almeida",
    "gender": 0,
    "birth_date": "20/04/1998",
    "city_name": "Recife"
}
```

- **`GET /customer/name/:name`**: Essa rota irá receber o nome desejado e irá retornar todos os clientes cujo nome (ignorando caixas altas) contenham a String fornecida. Um exemplo de retorno dos clientes consultados:

```json
[
    {
        "id": 3,
        "name": "Bia Silva",
        "gender": "MULHER_TRANS",
        "age": 21,
        "state": "Pernambuco",
        "birth_date": "20/04/1999",
        "city_name": "Recife"
    },
    {
        "id": 4,
        "name": "Roberta Silva",
        "gender": "MULHER",
        "age": 22,
        "state": "Pernambuco",
        "birth_date": "12/04/1998",
        "city_name": "Olinda"
    }
]
```
- **`DELETE /costuner/:id`**: A rota deve deletar o cliente com o `id` presente nos parâmetros da rota;

## Banco de dados


⚠️ Toda a aplicação foi feita utilizando o banco H2. Isso significa que o banco de dados é inicializado (e criado) novamente sempre que a aplicação sobe. Todos os registros são guardados na memória.

Para visualizar os dados, após subir a aplicação, é necessário acessar o endereço **http://localhost:8080/h2-console/.**  
Para realizar o login na aplicação, basta inserir a url `jdbc:h2:C:/data/example`. O usuário permanece `sa` e a senha permanece vazia (sem nada escrito). ⚠️

## :memo: Licença

Esse projeto está sob a licença MIT. Veja o arquivo [TODO:LICENSE](LICENSE) para mais detalhes.

---

Feito com 💜 by Navarro Silva :wave:
