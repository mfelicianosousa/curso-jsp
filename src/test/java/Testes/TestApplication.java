package Testes;

import org.junit.Test;

import dao.DaoGeneric;
import entidades.Carro;
import entidades.Pessoa;

public class TestApplication {

	@Test
	public void InsertApplication() {
		
		Pessoa pessoa = new Pessoa();
		Carro carro = new Carro();
		
		DaoGeneric<Pessoa> daoPessoa = new DaoGeneric<Pessoa>();
		DaoGeneric<Carro> daoCarro = new DaoGeneric<Carro>();
		
		
		pessoa.setNome("Raphael Neves");
		
		carro.setModelo("Mustang");
		carro.setAnoFabricacao(1989);
		
		daoPessoa.saveOrUpdate(pessoa);
		daoCarro.saveOrUpdate(carro);
		
		System.out.println("Entidades salvas com sucesso!");
		
		
	}
	
	@Test
	public void UpdateApplication() {
		
	
			DaoGeneric<Pessoa> daoPessoa = new DaoGeneric<Pessoa>();
			
			Pessoa pessoa = daoPessoa.findById(Pessoa.class, 3L);
			
			pessoa.setNome("Raphael Oliveira Neves");
			daoPessoa.saveOrUpdate(pessoa);
			System.out.println("Entidade atualizada com sucesso.");


	}
	
	@Test
	public void RemoveApplication() {
		
			
			DaoGeneric<Pessoa> daoPessoa = new DaoGeneric<Pessoa>();
			DaoGeneric<Carro> daoCarro = new DaoGeneric<Carro>();
			
			daoPessoa.remove(Pessoa.class, 3L);
			daoCarro.remove(Carro.class, 3L);
			
			System.out.println("Entidades removidas com sucesso!");
			
	
	}
	
}
