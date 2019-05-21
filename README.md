# Criptografia de Júlio César
Decifrador de mensagens usando serviço REST com Spring MVC.

#### 1. A seguinte aplicação recebe um json no formato:
```
{
  "numero_casas": 10,
  "token":"token_do_usuario",
  "cifrado": "texto criptografado",
  "decifrado": "aqui vai o texto decifrado",
  "resumo_criptografico": "aqui vai o resumo"
}
```

Para obtê-lo é necessário realizar uma requisição HTTP do tipo abaixo:  
```
https://api.site.com/generate-data??token=SEU_TOKEN
```

#### 2. O json deve ser salvo em arquivo
Nomeie o arquivo como answer.json

#### 3. Decifrar o texto contido no atributo cifrado e atualizar na answer.json. 
Para isso, o atributo numero_casa deve ser usado. A processo se dará da seguinte maneira:  
*As mensagens serão convertidas para minúsculas tanto para a criptografia quanto para descriptografia.*  
*Os números e pontos serão mantidos, ou seja:*  
**Normal:** 1a.a  
**Cifrado:** 1d.d  

Ex. considerando o número de casas = **3**:
```
Normal: a ligeira raposa marrom saltou sobre o cachorro cansado.
Cifrado: d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr.
```
#### 4. Gerar um resumo criptográfico do texto decifrado e atualizar na answer.json
Use o algoritmo **sha1**.  
*OBS: Pode ser usada uma biblioteca de criptografia*

#### 5. Submeter o arquivo atualizado via POST para a API:
```
https://api.site.com/submit-solution?token=SEU_TOKEN
```
A API espera um arquivo sendo enviado como *multipart/form-data*, como se fosse enviado por um formulário HTML, com um campo do tipo file com o nome answer
