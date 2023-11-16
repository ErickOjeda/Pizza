    package br.com.projeto.pizzaria.entity;


    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    @Entity
    @Getter
    @Setter
    public class Login {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "email")
        private String email;

        @Column(name = "senha")
        private String senha;

        @OneToOne
        @JoinColumn(name = "usuario_fk", referencedColumnName = "id")
        private Usuario usuario;

        @OneToOne
        @JoinColumn(name = "funcionario_fk", referencedColumnName = "id")
        private Funcionario funcionario;

        public Login(){

        }

        public Login(Long id, String email, String senha, Usuario usuario,Funcionario funcionario) {
            this.id = id;
            this.email = email;
            this.senha = senha;
            this.usuario = usuario;
            this.funcionario = funcionario;
        }
    }
