package br.com.escola.javamongodbescola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.escola.javamongodbescola.model.Aluno;
import br.com.escola.javamongodbescola.repository.AlunoRepository;

@Controller
public class GeolocalizacaoController {

	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping("/geolocalizacao/iniciarpesquisa")
	public String inicializarPesquisa(Model model) {
		List<Aluno> alunos = alunoRepository.findAll();
		model.addAttribute("alunos", alunos);
		return "geolocalizacao/pesquisar";
	}

	@GetMapping("/geolocalizacao/pesquisar")
	public String pesquisar(@PathVariable String alunoId, Model model) {
		Aluno aluno = alunoRepository.findOne(alunoId);
		List<Aluno> alunosProximos = alunoRepository.pesquisaPorGeolocalizao(aluno);

		model.addAttribute("alunosProximos", alunosProximos);
		return "geolocalizacao/pesquisar";
	}
}
