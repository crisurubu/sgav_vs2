package infotec.sgva.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infotec.sgva.dto.login.TokenDTO;
import infotec.sgva.dto.login.UsuarioDTO;
import infotec.sgva.entities.login.Usuario;
import infotec.sgva.enums.Permissoes;
import infotec.sgva.exception.ErroAutenticacao;
import infotec.sgva.exception.RegraNegocioException;
import infotec.sgva.services.login.JwtService;
import infotec.sgva.services.login.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private final UsuarioService service;
	
	@Autowired
	private JwtService jwtService;

	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody UsuarioDTO dto) {

		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			String token = jwtService.gerarToken(usuarioAutenticado);
			TokenDTO tokenDTO = new TokenDTO(usuarioAutenticado.getNome(), token);
			return ResponseEntity.ok(tokenDTO);

		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody UsuarioDTO dto) {

		try {
			Usuario entidade = converter(dto);
			entidade = service.salvarUsuario(entidade);
			return new ResponseEntity<>(entidade, HttpStatus.CREATED);
		}

		catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	private Usuario converter(UsuarioDTO dto) {

		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());

		if (dto.getPermissoes() != null) {
			usuario.setPermissoes(Permissoes.valueOf(dto.getPermissoes()));
		}
		return usuario;
	}

}
