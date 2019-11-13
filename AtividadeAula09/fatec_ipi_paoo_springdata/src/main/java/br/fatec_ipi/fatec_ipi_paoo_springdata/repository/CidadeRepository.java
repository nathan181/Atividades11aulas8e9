package br.fatec_ipi.fatec_ipi_paoo_springdata.repository;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import br.fatec_ipi.fatec_ipi_paoo_springdata.model.beans.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	@Async 
	public Future < List <Cidade> > findByNome (String nome);
	
	//Querys
	
	@Query ("SELECT c FROM Cidade c WHERE lower(c.nome) = ?1") 
	public List <Cidade> buscarPorNomeIgnore (String nome);
	
	@Query ("SELECT c FROM Cidade c WHERE c.nome = ?1%") 
	public List <Cidade> buscarPorNome (String nome);

	@Query ("SELECT c FROM Cidade c WHERE c.latitude = :latitude and c.longitude = :longitude") 
	public List <Cidade> buscarPorLatitudeELongitude (@Param ("latitude") int latitude,@Param("longitude") int longitude);
	
	
	//Named Queries
	
	public Cidade buscarPelaLatitudeEPelaLongitude (@Param ("latitude") int latitude,@Param ("longitude") int longitude);

	public Cidade buscarPeloNome (@Param ("nome") String nome);
	
	public Cidade buscarPeloNomeIgnore (@Param ("nome") String nome);

}
