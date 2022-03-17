package infotec.sgva.repository.login;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import infotec.sgva.entities.login.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByEmail(String email);
	Optional<Usuario> findByEmail(String email);

}
