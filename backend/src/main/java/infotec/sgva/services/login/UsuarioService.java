package infotec.sgva.services.login;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infotec.sgva.entities.login.Usuario;
import infotec.sgva.exception.ErroAutenticacao;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.repository.login.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	private UsuarioRepository repository;	
	private PasswordEncoder encoder;
	
	public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
		super();
		this.repository = repository;
		this.encoder = encoder;
	}
	
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado.");
		}
		boolean senhaBatem = encoder.matches(senha, usuario.get().getSenha());
		if(!senhaBatem) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		return usuario.get();
	}
	
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Ja existe email cadastrado..");
		}
		
	}
	
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		criptografaSenha(usuario);
		return repository.save(usuario);
	}
	
	private void criptografaSenha(Usuario usuario) {
		String senha = usuario.getSenha();
		String senhaCriptografada = encoder.encode(senha);
		usuario.setSenha(senhaCriptografada);
	}
	
	
	public void validar(Usuario usuario) {
		if(usuario.getNome() == null || usuario.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido");
		}
		if(usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
			throw new RegraNegocioException("Informe um email válido");
		}
		if(usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
			throw new RegraNegocioException("Informe uma senha válida");
		}
		if(usuario.getPermissoes() == null) {
			throw new RegraNegocioException("Informe a permisão de acesso do usuário.");
		}
		
	}

}
