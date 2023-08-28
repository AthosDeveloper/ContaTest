--> adicionando testes no projeto  
/* 

testar se está listando as contas corretamente e se o método chamou pelo menos uma vez.


mockito, quando você pegar a conta service e listar as contas, então me retorna um Array.asList  passando os dois objetos usuários como parâmetro
MockMVC, performe o método get no caminho /api/contas com o tipo de conteúdo aplicação json
conteúdo, receba um mapper.
mapper, escreva valores como string e receba um Array.AsList com os dois DTOS como parâmetro e faça um print.
mockito, verifique se o contasService está listando as contas corretamente
mockito, verifique se o listarContas está chamando pelo menos uma vez.



me retorneOk_quandoEuPegarUmaContaPorId

mockito, quando a contasService exibirAContaPorId e pegar um id, então me retorne um objeto conta do tipo optional como parâmetro.

MockMVC, performe um método get recebendo um caminho "/api/contas/getId{id}, pegue esse id passado pelo objeto.
tipo de conteúdo recebe  mediaTipeJsonApplication
Conteúdo recebe um mapper.
Mapper, escreva os valores como string e receba um DTO como parâmetro e espere que o statos esteja ok e faça um print.



teste se o status foi criado_quando salvar qualquer objeto



quando o contasService cadastrar qualquer objeto conta, então me retorne o valor do id1
MockMVC, performe o método post e receba como caminho "/api/contas"
o tipo de conteúdo recebe um MediaType.jsonApplication
conteúdo recebe um mapper.
mapper, escreve um valor como string e recebe um objeto DTO1 como parâmetro.
e espere que o status esteja criado.
e faça um print
verifique se o contasService  cadastrar contas recebendo qualquer objeto foi chamado pelo menos uma vez. 

teste se o status está sem conteúdo_quando deletar uma conta


mockMVC, performe e receba um MockMVCRequestBuilders.
MockMVCRequestBuilders, delete no caminho "/api/contas/{id}", pegue o valor do id da conta1
e espere que o status seja SemConteúdo.




*/


