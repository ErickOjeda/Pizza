    package br.com.projeto.pizzaria.service;

    import br.com.projeto.pizzaria.dto.UsuarioDTO;
    import br.com.projeto.pizzaria.convert.UsuarioDTOConvert;
    import br.com.projeto.pizzaria.entity.Usuario;
    import br.com.projeto.pizzaria.enums.Roles;
    import br.com.projeto.pizzaria.repository.UsuarioRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    import org.springframework.util.Assert;

    import java.util.ArrayList;
    import java.util.List;


    @Service
    public class UsuarioService implements UserDetailsService {

        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private  EnderecoService enderecoService;
        @Autowired
        private UsuarioDTOConvert usuarioDTOConvert;

        public UsuarioDTO criar(UsuarioDTO usuarioDTO){

            Usuario usuariotemp = usuarioDTOConvert.convertUsuarioDTOToUsuario(usuarioDTO);

            this.usuarioRepository.save(usuariotemp);

           return usuarioDTOConvert.convertUsuarioToUsuarioDTO(usuariotemp);
        }

        public List<UsuarioDTO> findByNome(String nome){
            List<Usuario> usuarioBanco = this.usuarioRepository.findPessoaByNome(nome);
            List<UsuarioDTO> usuarioDTOList = new ArrayList<>();

            for(int i = 0; i < usuarioBanco.size(); i++){
                usuarioDTOList.add(usuarioDTOConvert.convertUsuarioToUsuarioDTO(usuarioBanco.get(i)));
            }

            return usuarioDTOList;
        }

        public List<UsuarioDTO> findByRole(Roles roles){
            List<Usuario> usuarioBanco = this.usuarioRepository.findPessoaByRole(roles);
            List<UsuarioDTO> usuarioDTOList = new ArrayList<>();

            for(int i = 0; i < usuarioBanco.size(); i++){
                usuarioDTOList.add(usuarioDTOConvert.convertUsuarioToUsuarioDTO(usuarioBanco.get(i)));
            }

            return usuarioDTOList;
        }


        public List<UsuarioDTO> findAllUsuarios(){
            List<Usuario> usuariosBanco = usuarioRepository.findAllUsuarios();
            List<UsuarioDTO> usuarioDTOList = new ArrayList<>();

            for(int i = 0; i < usuariosBanco.size(); i++){
                usuarioDTOList.add(usuarioDTOConvert.convertUsuarioToUsuarioDTO(usuariosBanco.get(i)));
            }

            return usuarioDTOList;
        }

        public UsuarioDTO editar(Long id,UsuarioDTO usuarioDTO){
            Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
            Usuario usuarioNew = usuarioDTOConvert.convertUsuarioDTOToUsuario(usuarioDTO);

            Assert.isTrue(usuarioBanco != null, "Usuario nao encontrado");

            usuarioRepository.save(usuarioNew);

            return usuarioDTOConvert.convertUsuarioToUsuarioDTO(usuarioNew);
        }

        public UsuarioDTO deletar(Long id){
            Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

            Assert.isTrue(usuarioBanco != null, "Usuario nao encontrado");
            usuarioRepository.delete(usuarioBanco);

            return usuarioDTOConvert.convertUsuarioToUsuarioDTO(usuarioBanco);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usuario user= usuarioRepository.findByEmail(username);
            if (user != null) {
                return (UserDetails) user;
            }
            throw new UsernameNotFoundException("Não encontrado");
        }
    }
