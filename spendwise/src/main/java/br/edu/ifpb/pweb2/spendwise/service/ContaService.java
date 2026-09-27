package br.edu.ifpb.pweb2.spendwise.service;

import br.edu.ifpb.pweb2.spendwise.model.Conta;
import br.edu.ifpb.pweb2.spendwise.repository.ContaRepository;
import br.edu.ifpb.pweb2.spendwise.model.Correntista;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listarPorCorrentista(Long correntistaId) {
        return contaRepository.findByCorrentistaId(correntistaId);
    }

}