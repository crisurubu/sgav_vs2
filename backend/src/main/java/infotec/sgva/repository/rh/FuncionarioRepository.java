package infotec.sgva.repository.rh;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import infotec.sgva.entities.rh.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	Funcionario findByEmail(String email);
	boolean existsByCpf(String cpf);
	Optional<Funcionario> findByCpf(String cpf);
	

}
