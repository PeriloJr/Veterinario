package br.gov.sp.fatec.veterinario;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.veterinario.VeterinarioApplication;
import br.gov.sp.fatec.veterinario.entity.Animal;
import br.gov.sp.fatec.veterinario.entity.Consulta;
import br.gov.sp.fatec.veterinario.entity.Dono;
import br.gov.sp.fatec.veterinario.repository.AnimalRepository;
import br.gov.sp.fatec.veterinario.repository.ConsultaRepository;
import br.gov.sp.fatec.veterinario.repository.DonoRepository;

@SpringBootTest(classes = VeterinarioApplication.class)
@Transactional
@Rollback
class VeterinarioApplicationTests {

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private DonoRepository donoRepository;
	@Autowired
	private ConsultaRepository consultaRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void findByNome(){
		Animal animal = new Animal();
		animal.setNome("Simba");
		animal.setEspecie("Leao");
		animal.setDescricao("Felino grande");
		animalRepository.save(animal);

		assertFalse(animalRepository.findByNomeAndEspecie("Simba", "Leao").isEmpty());
	}

	@Test
	void findByNomeOrEspecie(){
		Animal animal = new Animal();
		animal.setNome("Cuca");
		animal.setEspecie("Jacare");
		animal.setDescricao("Reptil feroz");
		animalRepository.save(animal);
		
		assertFalse(animalRepository.findByNomeOrEspecie("Cuca", "dinossauro").isEmpty());
	}

	@Test
	void findByDescricao(){
		Animal animal = new Animal();
		animal.setNome("Lulu");
		animal.setEspecie("Coala");
		animal.setDescricao("Marsupial");
		animalRepository.save(animal);

		assertTrue(animalRepository.findByDescricaoContains("baleia").isEmpty());
	}

	@Test
	void findByDonoNome(){
		Dono dono = new Dono();
		dono.setNome("Joel");
		dono.setObservacao("observacao");
		dono.setTelefone(123456789);
		
		donoRepository.save(dono);

		Animal animal = new Animal();
		animal.setNome("Liro");
		animal.setEspecie("Coelho");
		animal.setDescricao("Mamifero feroz");
		animal.setDonos(new HashSet<Dono>());
		animal.getDonos().add(dono);
		animalRepository.save(animal);

		assertFalse(animalRepository.findByDonosNome("Joel").isEmpty());
	}

	@Test
	void findByAnimaisNome(){
		Dono dono = new Dono();
		dono.setNome("Joel");
		dono.setObservacao("observacao");
		dono.setTelefone(123456789);
		
		donoRepository.save(dono);

		Animal animal = new Animal();
		animal.setNome("Liro");
		animal.setEspecie("Coelho");
		animal.setDescricao("Mamifero feroz");
		animal.setDonos(new HashSet<Dono>());
		animal.getDonos().add(dono);
		animalRepository.save(animal);

		assertFalse(donoRepository.findByAnimaisNome("Liro").isEmpty());
	}

	@Test
	void findByAnimalNome(){
		Animal animal = new Animal();
		animal.setNome("Liro");
		animal.setEspecie("Coelho");
		animal.setDescricao("Mamifero feroz");
		animalRepository.save(animal);

		Consulta consulta = new Consulta();
		consulta.setProcedimento("Endoscopia");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		consulta.setData(calendar);
		consulta.setAnimal(animal);
		consultaRepository.save(consulta);

		assertFalse(consultaRepository.findByAnimalNome("Liro").isEmpty());
	}

	@Test
	void findByConsultasProcedimento(){
		Animal animal = new Animal();
		animal.setNome("Liro");
		animal.setEspecie("Coelho");
		animal.setDescricao("Mamifero feroz");
		animalRepository.save(animal);

		Consulta consulta = new Consulta();
		consulta.setProcedimento("Endoscopia");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		consulta.setData(calendar);
		consulta.setAnimal(animal);
		consultaRepository.save(consulta);

		assertFalse(animalRepository.findByConsultasProcedimento("Endoscopia").isEmpty());
	}
}
