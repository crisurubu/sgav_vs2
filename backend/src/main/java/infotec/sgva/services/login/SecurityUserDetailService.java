package infotec.sgva.services.login;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import infotec.sgva.entities.login.Usuario;
import infotec.sgva.repository.login.UsuarioRepository;

@Service
public class SecurityUserDetailService implements UserDetailsService{
	
	private UsuarioRepository usuarioRepository;

	public SecurityUserDetailService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuarioEncontrado =  usuarioRepository.findByEmail(email)
													  .orElseThrow(() -> new UsernameNotFoundException("Email não Cadastrado"));
		
		
		User user = (User) User.builder()
						.username(usuarioEncontrado.getEmail())
						.password(usuarioEncontrado.getSenha())
						.roles("USER")
						.build();
		
		return user;
	}

}
