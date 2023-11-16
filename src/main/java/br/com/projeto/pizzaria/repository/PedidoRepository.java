    package br.com.projeto.pizzaria.repository;

    import br.com.projeto.pizzaria.entity.Pedido;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;


    @Repository
    public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    }
