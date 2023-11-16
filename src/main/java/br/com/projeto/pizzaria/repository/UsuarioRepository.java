    package br.com.projeto.pizzaria.repository;

    import br.com.projeto.pizzaria.entity.Usuario;
    import br.com.projeto.pizzaria.enums.Roles;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

        Usuario findByEmail(String email);

        @Query(value = "SELECT p FROM Usuario p where p.nome = :nome")
        List<Usuario> findPessoaByNome(@Param("nome")final String nome);

        @Query(value = "SELECT p FROM Usuario p where p.roles = :roles")
        List<Usuario> findPessoaByRole(@Param("roles")final Roles roles);

        @Query(value = "SELECT p FROM Usuario p LEFT JOIN FETCH p.enderecos")
        List<Usuario> findAllUsuarios();

    }
