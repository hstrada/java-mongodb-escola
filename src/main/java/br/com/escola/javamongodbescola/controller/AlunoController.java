package br.com.escola.javamongodbescola.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.maps.errors.ApiException;

import br.com.escola.javamongodbescola.model.Aluno;
import br.com.escola.javamongodbescola.repository.AlunoRepository;
import br.com.escola.javamongodbescola.service.GeolocalizacaoService;

@Controller
@CrossOrigin
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	private GeolocalizacaoService geolocalizacaoService;

	@GetMapping("/aluno/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "aluno/cadastrar";
	}

	@PostMapping("/aluno/salvar")
	public String salvar(@ModelAttribute Aluno aluno) {
		List<Double> latELongPor = null;
		try {
			latELongPor = geolocalizacaoService.obterLatELongPor(aluno.getContato());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    aluno.getContato().setCoordinates(latELongPor);
		alunoRepository.save(aluno);
		return "redirect:/";
	}

	@GetMapping("/aluno/listar")
	public String listar(Model model) {
		List<Aluno> alunos = alunoRepository.findAll();
		model.addAttribute("alunos", alunos);
		return "aluno/listar";
	}

	@GetMapping("/aluno/visualizar/{id}")
	public String visualizar(@PathVariable String id, Model model) {
		Aluno aluno = alunoRepository.findOne(id);
		model.addAttribute("aluno", aluno);

		return "aluno/visualizar";
	}

	@GetMapping("/aluno/pesquisarnome")
	public String pesquisarNome() {
		return "aluno/pesquisarnome";
	}

	@GetMapping("/aluno/pesquisar")
	public String pesquisar(@RequestParam("nome") String nome, Model model) {
		List<Aluno> alunos = alunoRepository.pesquisarPorNome(nome);
		model.addAttribute("alunos", alunos);
		return "aluno/pesquisarnome";
	}

}
