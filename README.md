# Desafio Meli Taller de Programación Avanzada

## Integrantes:
- Jorge Condorí
- Facundo Erbin
- Diego Bustos
- Juan Troncoso
- Andrés Lorenzo

## Introducción

Desarrollo de una api con Java 11 y Spring Boot para el parcial de la materia Taller de Programación Avanzada - UTN FRM. El mismo consiste en el desarrollo de un desafio propuesto por MercadoLibre para contratar desarrolladores backend. [Más información acá](https://github.com/facuerbin/Desafio_Meli_Grupo/blob/main/Examen%20Mercadolibre%20BE.pdf)

## Tecnologías

Para iniciar el proyecto se debe contar con Maven y el JDK de Java 11 instalado. Además preferentemente IntelliJ. Desde IntelliJ se pueden instalar las dependencias del proyecto que se encuentran en el archivo "pom.xml". Una vez instaladas se puede iniciar el proyecto desde el archivo "src/main/java/com/mutant/mutantapi/MutantApiApplication.java".
El proyecto se realizó con Java 11 y Spring Boot. Consta de una API Rest que expone dos endpoints "api/v1/mutants" y "api/v1/mutants/:size". El primero se conecta mediante el verbo HTTP POST y requiere que en el body del request se envie una matriz que represente una secuencia de ADN. Está matriz debe ser de NxN con N mayor o igual a 4. En el documento "Examen Mercadolibre BE.pdf" se podrá encontrar un ejemplo de cómo se espera esté formateada la matriz. Luego se puede comunicar mediante GET con el endpoint "api/mutant/:size" con size siendo un número entero mayor o igual a 4. Este endpoint responderá generando una matriz aleatoria del tamaño solicitado.
También se integró una base de datos H2 al sistema para la cual se deben utilizar las siguientes credenciales: 
- localhost:8080/h2-console
- user: sa
- password:
Se incorporó Swagger para documentar la API, por lo que al acceder a la raiz del proyecto se podrá ver la documentación e incluso probar la API.

## Algunos Ejemplos

Mediante un cliente HTTP como Postman o Insomnia se pueden realizar estás consultas a la aplicación.

GET http://desafio-meli-tpa.herokuapp.com/api/v1/mutant/ => Retorna un JSON con todos los ADN analizados

GET http://desafio-meli-tpa.herokuapp.com/api/v1/mutant/20 => Retorna un array generado aleatoriamente de 20x20

POST http://desafio-meli-tpa.herokuapp.com/api/v1/mutant/ + 
{
"dna": 	[
"ACCCTA",
"TAATGC",
"ACAGTC",
"AAATGT",
"TCAGAC",
"AAAAGT"
]
}
=> Status: 200


POST http://desafio-meli-tpa.herokuapp.com/api/v1/mutant/ +
{
"dna": 	[
"ACCCTATG",
"TAATGCTG",
"ACAGCCTG",
"ATAGGCGG",
"TTCGTTCG",
"ACAAGACG",
"TCAGTTAG",
"ACAAGTAG"
]
}
=> Status: 200

POST http://desafio-meli-tpa.herokuapp.com/api/v1/mutant/ +
{
"dna":  [
  "ATATA",
  "AGTCA",
  "GAACC",
  "CTCAC",
  "GATAT"
]
}
=> Status: 403

GET http://desafio-meli-tpa.herokuapp.com/api/v1/mutant/stats => Retorna un JSON con la cantidad de mutantes analizados, humanos analizados y la proporción.

### URL's del proyecto

http://desafio-meli-tpa.herokuapp.com/

https://github.com/facuerbin/Desafio_Meli_Grupo


