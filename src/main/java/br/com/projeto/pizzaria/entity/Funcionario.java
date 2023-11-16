    package br.com.projeto.pizzaria.entity;

    import br.com.projeto.pizzaria.enums.Roles;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.List;

    @Entity
    @Getter
    @Setter
    public class Funcionario {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "nome")
        private String nome;

        @Column(name = "telefone")
        private String telefone;

        @Column(name = "CPF")
        private String cpf;

        @OneToOne(mappedBy = "funcionario")
        private Login login;

        @Column(name = "Role")
        private Roles roles;

        public Funcionario(){

        }

        public Funcionario(Long id, String nome, String telefone, String cpf, Roles roles) {
            this.id = id;
            this.nome = nome;
            this.telefone = telefone;
            this.cpf = cpf;
            this.roles = roles;
        }
    }
