package infotec.sgva.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import infotec.sgva.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	Funcionario findByEmail(String email);
	boolean existsByCpf(String cpf);
	Optional<Funcionario> findByCpf(String cpf);
	

}
