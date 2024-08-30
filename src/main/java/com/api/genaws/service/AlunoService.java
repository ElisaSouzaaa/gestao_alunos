package com.api.genaws.service;

import com.api.genaws.entity.Aluno;
import com.api.genaws.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> getById(Long alunoId) {
        return alunoRepository.findById(alunoId);
    }

    public Aluno atualizarAluno(Long alunoId, Aluno alunoAtualizado) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(alunoId);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNome_aluno(alunoAtualizado.getNome_aluno());
            aluno.setIdade_aluno(alunoAtualizado.getIdade_aluno());
            aluno.setNota_primeiro_semestre(alunoAtualizado.getNota_primeiro_semestre());
            aluno.setNota_segundo_semestre(alunoAtualizado.getNota_segundo_semestre());
            aluno.setNome_professor(alunoAtualizado.getNome_professor());
            aluno.setNumero_sala(alunoAtualizado.getNumero_sala());
            return alunoRepository.save(aluno);
        }
        return null;
    }

    public Double getNotaPorSemestre(Long alunoId, String semestre) {
        Aluno aluno = alunoRepository.findById(alunoId).orElse(null);
        if(aluno!=null) {
            if("primeiro-semestre".equals(semestre)) {
                return aluno.getNota_primeiro_semestre();
            } else if ("segundo-semestre".equals(semestre)) {
                return aluno.getNota_segundo_semestre();
            }
        }
        return null;
    }

    public Aluno atualizarPrimeiroSemestre(Long alunoId, Double notaPrimeiroSemestre) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(alunoId);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNota_primeiro_semestre(notaPrimeiroSemestre);
            return alunoRepository.save(aluno);
        }
        return null;
    }

    public Aluno atualizarPSegundoSemestre(Long alunoId, Double notaSegundoSemestre) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(alunoId);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNota_segundo_semestre(notaSegundoSemestre);
            return alunoRepository.save(aluno);
        }
        return null;
    }

    public boolean deleteById(Long alunoId) {
        if (alunoRepository.existsById(alunoId)) {
            alunoRepository.deleteById(alunoId);
            return true;
        }
        return false;
    }
}
