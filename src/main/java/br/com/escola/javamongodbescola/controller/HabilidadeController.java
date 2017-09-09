package br.com.escola.javamongodbescola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.escola.javamongodbescola.model.Aluno;
import br.com.escola.javamongodbescola.model.Habilidade;
import br.com.escola.javamongodbescola.repository.AlunoRepository;

@Controller
public class HabilidadeController {

	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping("habilidade/cadastrar/{id}")
	public String cadastrar(@PathVariable String id, Model model) {
		Aluno aluno = alunoRepository.findOne(id);
		model.addAttribute("aluno", aluno);
		model.addAttribute("habilidade", new Habilidade());
		return "habilidade/cadastrar";
	}

	@PostMapping("habilidade/cadastrar/{id}")
	public String salvar(@PathVariable String id, @ModelAttribute Habilidade habilidade) {
		Aluno aluno = alunoRepository.findOne(id);
		List<Habilidade> habilidades = aluno.getHabilidades();
		habilidades.add(habilidade);
		aluno.setHabilidades(habilidades);
		alunoRepository.save(aluno);
		return "redirect:/aluno/listar";
	}

}