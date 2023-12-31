package com.factory.contabancaria.controller;

import com.factory.contabancaria.DTOs.CadastrarContaDTO;
import com.factory.contabancaria.DTOs.ExibirContaPeloIdDTO;
import com.factory.contabancaria.model.ContasModel;
import com.factory.contabancaria.model.factory.ContaFactory;

import com.factory.contabancaria.service.ContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contas")
public class ContasController {

    @Autowired
    ContasService contasService;

    //requisições
    //GET - Pegar as informações do nosso banco
    @GetMapping
    public ResponseEntity<List<ContasModel>> listarTodasContas(){
        return ResponseEntity.ok(contasService.listarContas());
    }
//implementando o DTO com os respectivos atributos para get
    @GetMapping("/getId/{id}")
    public ResponseEntity<?> exibeUmaContaPeloId(@PathVariable Long id) {
        Optional<ContasModel> contasModel = contasService.exibeContaPorId(id);

        return ResponseEntity.ok().body(new ExibirContaPeloIdDTO(contasModel.get()));
    }
    //Irei pegar uma conta pelo nome, usando um Optional para validar
     @GetMapping("/getName/{nomeUsuario}")
    public ResponseEntity<?> exibirContasPorNome(@PathVariable String nomeDeUsuario){
Optional<ContasModel> conta = contasService.exibirContasPorNome(nomeDeUsuario);
if (conta.isPresent()){
    return ResponseEntity.ok().body(conta.get());
} else {
    return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("conta não encontrada com esse nome!");
}
    }
    //POST - Cria uma nova conta dentro do banco
//crio um DTO com os respectivos atributos, criando um uri com o path da minha requisição e recebendo no body um novo DTO com uma nova conta como parâmetro.

    @PostMapping

    public ResponseEntity<CadastrarContaDTO> cadastrarConta(@RequestBody ContasModel contasModel, ContaFactory contaFactory){
        ContasModel novaConta = contasService.cadastrar(contasModel, contaFactory);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaConta.getId()).toUri();
        return ResponseEntity.created(uri).body(new CadastrarContaDTO(novaConta));
    }

    //PUT - Alterar uma conta já existente dentro do banco
    @PutMapping(path = "/alterar/{id}")
    public ResponseEntity<ContasModel> AlterarContaPorId(@PathVariable Long id, @RequestBody ContasModel contasModel){
         contasModel = contasService.alterarContaPorId(id, contasModel);
   return ResponseEntity.status(200).body(contasModel);
    }
//irei alterar a minha conta pelo nome:
@PutMapping("/{nomeDeUsuario}")
public ContasModel alterarContaPorNome(@PathVariable String nomeDoUsuario, @RequestBody ContasModel contasModel){
        return  contasService.alterarContaPorNome(nomeDoUsuario, contasModel);
}
//irei alterar apenas o nome de uma conta já existente recebendo um novo String como parâmetro para a requisição.

    @PutMapping("@{id}/nome-usuario")
    public ResponseEntity<Void> atualizarContaPorNome(@PathVariable Long id, @RequestParam String novoUsuario){
  contasService.atualizarNomeUsuarioConta(id, novoUsuario);
   return  ResponseEntity.ok().build();
    }
    //DELETE - Deleta uma conta já existente dentro do banco
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Long id){
        contasService.deletarContaPorId(id);
        return ResponseEntity.noContent().build();
    }

}
