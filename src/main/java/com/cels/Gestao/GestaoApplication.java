package com.cels.Gestao;

import com.cels.Gestao.entities.Equipe;
import com.cels.Gestao.entities.Projeto;
import com.cels.Gestao.entities.Usuario;
import com.cels.Gestao.enums.Perfil;
import com.cels.Gestao.repositories.EquipeRepository;
import com.cels.Gestao.repositories.ProjetoRepository;
import com.cels.Gestao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class GestaoApplication implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;
	private final ProjetoRepository projetoRepository;
	private final EquipeRepository equipeRepository;



	public GestaoApplication(UsuarioRepository usuarioRepository, ProjetoRepository projetoRepository, EquipeRepository equipeRepository) {
		this.usuarioRepository = usuarioRepository;
		this.projetoRepository = projetoRepository;
		this.equipeRepository = equipeRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(GestaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		usuarioRepository.deleteAll();
		projetoRepository.deleteAll();
		equipeRepository.deleteAll();

		Usuario user1 = new Usuario(null,"Maria Silva", "36378912377", "maria@gmail.com", "desenvolvedora", "maria123", "mar1234", Perfil.ADMIN);
		usuarioRepository.save(user1);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Projeto proj1 =  new Projeto(null, "projeto teste","teste de projeto",LocalDate.parse("10/10/2010", formatter), LocalDate.parse("11/10/2010", formatter) );
		projetoRepository.save(proj1);


		Equipe equipe1 = new Equipe(null,"equipe dev", "equipe dos desenvolvedores");
		equipeRepository.save(equipe1);
	}





}
