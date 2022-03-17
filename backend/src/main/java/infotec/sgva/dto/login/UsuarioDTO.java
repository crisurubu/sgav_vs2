package infotec.sgva.dto.login;

import infotec.sgva.entities.login.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	private Long id;
	private String email;
	private String nome;
	private String senha;
	private String permissoes;
	
	
	public static UsuarioDTO convert(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setSenha(usuario.getSenha());
		usuarioDTO.setPermissoes(usuario.getPermissoes().name());
		return usuarioDTO;
	}

}