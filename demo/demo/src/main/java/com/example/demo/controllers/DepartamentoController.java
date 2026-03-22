package controllers;

import model.Departamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.DepartamentoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<Departamento> criar(@RequestBody Departamento departamento) {
        Departamento departamentoCriado = departamentoService.criar(departamento);
        return ResponseEntity
                .created(URI.create("/departamentos/" + departamentoCriado.getId()))
                .body(departamentoCriado);
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> listarTodos() {
        return ResponseEntity.ok(departamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        Optional<Departamento> departamento = departamentoService.buscarPorId(id);
        return departamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        boolean removido = departamentoService.deletarPorId(id);
        if (!removido) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}