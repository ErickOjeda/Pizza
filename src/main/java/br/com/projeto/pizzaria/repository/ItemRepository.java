    package br.com.projeto.pizzaria.repository;

    import br.com.projeto.pizzaria.entity.Item;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface ItemRepository extends JpaRepository<Item,Long> {
    }
