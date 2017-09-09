package br.com.escola.javamongodbescola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.escola.javamongodbescola.model.Aluno;
import br.com.escola.javamongodbescola.model.Nota;
import br.com.escola.javamongodbescola.repository.AlunoRepository;

@Controller
public class NotaController {

	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping("nota/salvar/{id}")
	public String cadastrar(@PathVariable String id, Model model) {
		Aluno aluno = alunoRepository.findOne(id);
		model.addAttribute("aluno", aluno);
		model.addAttribute("nota", new Nota());

		return "nota/cadastrar";
	}

	@PostMapping("nota/salvar/{id}")
	public String salvar(@PathVariable String id, @ModelAttribute Nota nota) {
		Aluno aluno = alunoRepository.findOne(id);
		List<Nota> notas = aluno.getNotas();
		notas.add(nota);
		aluno.setNotas(notas);
		alunoRepository.save(aluno);
		return "redirect:/aluno/listar";
	}

	@GetMapping("/nota/iniciarpesquisa")
	public String iniciarPesquisa() {
		return "nota/pesquisar";
	}

	@GetMapping("/nota/pesquisar")
	public String pesquisarPor(@RequestParam("notacorte") String notaCorte, Model model) {
		List<Aluno> alunos = alunoRepository.pesquisaPor(Double.parseDouble(notaCorte));
		model.addAttribute("alunos", alunos);
		return "nota/pesquisar";
	}

}
