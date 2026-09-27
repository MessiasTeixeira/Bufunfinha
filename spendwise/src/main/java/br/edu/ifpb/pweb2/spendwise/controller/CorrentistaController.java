package br.edu.ifpb.pweb2.spendwise.controller;

import br.edu.ifpb.pweb2.spendwise.model.Conta;
import br.edu.ifpb.pweb2.spendwise.model.Correntista;
import br.edu.ifpb.pweb2.spendwise.service.ContaService;
import br.edu.ifpb.pweb2.spendwise.service.CorrentistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/correntista")
public class CorrentistaController {

    private final CorrentistaService correntistaService;
    private final ContaService contaService;

    public CorrentistaController(CorrentistaService correntistaService, ContaService contaService) {
        this.correntistaService = correntistaService;
        this.contaService = contaService;
    }

    @GetMapping("/{id}/cadastrar")
    public String cadastrarConta(@PathVariable Long id, Model model) {
        model.addAttribute("contaForm", new Conta());
        model.addAttribute("id", id);

        return "correntista/cadastroConta";
    }

    @PostMapping("/{id}/cadastrar")
    public String cadastrarConta(@PathVariable Long id, Conta conta, Model model) {
        try {
            var correntista = correntistaService.buscarPorId(id);

            conta.setId(null);
            conta.setCorrentista(correntista);

            contaService.salvar(conta);

            return "redirect:/correntista/" + id + "/contas";

        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("contaForm", conta);
            model.addAttribute("id", id);

            return "correntista/cadastroConta";
        }
    }

    @GetMapping("/{id}")
    public String inicio(@PathVariable Long id) {
        List<Conta> contas = contaService.listarPorCorrentista(id);

        if (contas.isEmpty()) {
            return "redirect:/correntista/" + id + "/cadastrar";
        }

        return "redirect:/correntista/" + id + "/contas";
    }

    @GetMapping("/{id}/contas")
    public String listarContas(@PathVariable Long id, Model model) {
        List<Conta> contas = contaService.listarPorCorrentista(id);

        model.addAttribute("contas", contas);
        model.addAttribute("id", id);

        return "correntista/contas";
    }
}