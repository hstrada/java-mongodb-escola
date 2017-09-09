package br.com.escola.javamongodbescola.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.escola.javamongodbescola.model.Aluno;

public interface AlunoRepository extends MongoRepository<Aluno, String> {

	@Query("{ 'nome' : ?0 }")
	List<Aluno> pesquisarPorNome(String nome);

	@Query("{ 'notas.valor' : ?0 }")
	List<Aluno> pesquisaPor(double notaCorte);

	@Query("{ 'aluno.contato.endereco' : ?0 }")
	List<Aluno> pesquisaPorGeolocalizao(Aluno aluno);

}