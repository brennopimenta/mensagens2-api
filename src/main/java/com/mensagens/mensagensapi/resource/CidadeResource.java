package com.mensagens.mensagensapi.resource;

import com.mensagens.mensagensapi.event.RecursoCriadoEvent;
import com.mensagens.mensagensapi.model.Cidade;
import com.mensagens.mensagensapi.repository.CidadeRepository;
import com.mensagens.mensagensapi.repository.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public Optional<Cidade> buscarPeloCodigo(@PathVariable Long codigo) {
        return cidadeRepository.findById(codigo);
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@Valid @RequestBody Cidade cidade, HttpServletResponse response) {
        Cidade cidadeSalva = cidadeRepository.save(cidade);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, cidadeSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cidade cidade){
        Cidade cidadeSalva = cidadeService.atualizar(codigo, cidade);
        return ResponseEntity.ok(cidadeSalva);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo) {
        cidadeRepository.deleteById(codigo);
    }

}
