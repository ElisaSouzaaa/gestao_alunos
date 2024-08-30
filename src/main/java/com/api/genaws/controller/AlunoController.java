package com.api.genaws.controller;

import com.api.genaws.entity.Aluno;
import com.api.genaws.repository.AlunoRepository;
import com.api.genaws.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlunoController {

    @Autowired
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/cadastrar_aluno")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.cadastrarAluno(aluno);
        return ResponseEntity.status(201).body(novoAluno);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.getAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        return alunoService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}/notas/{semestre}")
    public ResponseEntity<Double> getNotasBySemestre(@PathVariable Long id, @PathVariable String semestre){
        Double nota = alunoService.getNotaPorSemestre(id, semestre);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Aluno aluno = alunoService.atualizarAluno(id, alunoAtualizado);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/notas/primeiro-semestre")
    public ResponseEntity<Aluno> atualizarPrimeiroSemestre(@PathVariable Long id, @RequestBody Aluno alunoComNotas) {
        Aluno aluno = alunoService.atualizarPrimeiroSemestre(id, alunoComNotas.getNota_primeiro_semestre());
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/notas/segundo-semestre")
    public ResponseEntity<Aluno> atualizarPSegundoSemestre(@PathVariable Long id, @RequestBody Aluno alunoComNotas) {
        Aluno aluno = alunoService.atualizarPSegundoSemestre(id, alunoComNotas.getNota_segundo_semestre());
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = alunoService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
