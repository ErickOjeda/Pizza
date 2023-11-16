    package br.com.projeto.pizzaria.dto;

    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class LoginDTO {

        private Long id;

        private String email;

        private String senha;

        private UsuarioDTO usuarioDTO;

        private FuncionarioDTO funcionarioDTO;

        public LoginDTO(){

        }

        public LoginDTO(Long id, String email, String senha, UsuarioDTO usuarioDTO, FuncionarioDTO funcionarioDTO) {
            this.id = id;
            this.email = email;
            this.senha = senha;
            this.usuarioDTO = usuarioDTO;
            this.funcionarioDTO = funcionarioDTO;
        }
    }
