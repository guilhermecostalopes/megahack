Desafio proposto pela Rede Globo buscando inovação na interação com a TV no Mega Hackathon da Shawee

# Interativa

Aplicação em Java utilizando diversos frameworks do Spring boot. Dentre eles:

1) spring-boot-starter-data-rest;
2) spring-boot-starter-security;
3) spring-boot-starter-web;
4) spring-boot-devtools;
5) spring-boot-starter-data-jpa.

Outros frameworks utilizados:

1) lombok;
2) jjwt;
3) commons-lang3.

## Começando

Faça o download de qualquer versão do ```Eclipse```, no site https://www.eclipse.org/eclipseide/ . Neste desafio utilizamos o ```STS 4```, que pode ser baixado no site: https://spring.io/tools .

Faça o clone deste repositório e importa ele para o eclipse instalado.

## Publicação

Utiilizamos o ```heroku``` para publicação de nosso app ```backend```. Utilizando o endereço: https://timemegahack.herokuapp.com

Alguns endpoints para testes, que pode ser feito no ```Postman```. 

1º) Deve buscar o token, neste link: https://timemegahack.herokuapp.com/api/auth , método POST, utilizandos como objeto ```JSON```, assim retornando o token:

{
	"login":"85655497042",
	"senha":"y|e8HTcAJ9"
}

Conforme imagem:

![Imagem configuração](/img/login_token.png)

2º) Após ter o token, devendo escrever Authorization, no key e colar o token no value, conforme imagem:

![Imagem configuração](/img/token.png)

3º) https://timemegahack.herokuapp.com/chatProgramacaoDias/pesquisarPorPrograma/001/20/1/2020 , método GET, Para visualizar o chat que vários usuários enviaram para um determinado programa por dia.

![Imagem configuração](/img/chat.png)

4º) https://timemegahack.herokuapp.com/enquetes/pesquisarPorPrograma/001/20/1/2020 , método GET, Para visualizar as enquetes de um determinado programa por dia.

![Imagem configuração](/img/login_token.png)

5º) https://timemegahack.herokuapp.com/enquetes/votarFavor/001/20/1/2020/001/001 , método GET, Para fazer votação a favor em uma enquete já cadastrada de um determinado programa por dia.

![Imagem configuração](/img/login_token.png)

6º) https://timemegahack.herokuapp.com/enquetes/votarContra/001/20/1/2020/001/001 , método GET, Para fazer votação contra em uma enquete já cadastrada de um determinado programa por dia.

![Imagem configuração](/img/login_token.png)

7º) DER do banco de dados:

![Imagem configuração](/img/banco_dados.png)


## Autor

- [Guilherme Lopes](https://github.com/guilhermecostalopes)

## Colaboradores

- [Rodrigo Temóteo](https://github.com/rodrigoatemoteo)
- [Josias Furtado](https://github.com/josiasfurtado)
- [Paulo Victor](https://github.com/paulovictorBraw)
- [Fernando Santana](https://github.com/NandoSantana)