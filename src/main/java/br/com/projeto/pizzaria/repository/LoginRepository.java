    package br.com.projeto.pizzaria.repository;

    import br.com.projeto.pizzaria.entity.Login;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface LoginRepository extends JpaRepository<Login, Long> {

        @Query(value = "SELECT l.usuario FROM Login l WHERE l.usuario.nome = :nomeDeLogin")
        List<Login> findUserNameByNomeDeLogin(@Param("nomeDeLogin") String nomeDeLogin);

        @Query(value = "SELECT l.funcionario FROM Login l WHERE l.funcionario.nome = :nomeDeLogin")
        List<Login> findUserNameByNomeDeLoginFuncionario(@Param("nomeDeLogin") String nomeDeLogin);
    }
