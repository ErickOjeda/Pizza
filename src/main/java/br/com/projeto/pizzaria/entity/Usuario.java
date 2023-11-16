    package br.com.projeto.pizzaria.entity;


    import br.com.projeto.pizzaria.enums.Roles;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.Collection;
    import java.util.Collections;
    import java.util.List;

    @Getter
    @Setter
    @Entity
    public class Usuario implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "nome")
        private String nome;

        @Column(name = "telefone")
        private String telefone;

        @JsonIgnoreProperties("usuario")
        @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
        private List<Endereco> enderecos;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "Roles")
        private Roles roles;

        @Column(name = "Roles")
        private String role;

        public Usuario(){

        }

        public Usuario(Long id, String nome, String telefone, Roles roles) {
            this.id = id;
            this.nome = nome;
            this.telefone = telefone;
            this.roles = roles;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities(){

            String userRole = role;
            String authority = userRole.startsWith("ROLE_") ? userRole : "ROLE_" + userRole;
            return Collections.singletonList(new SimpleGrantedAuthority(authority));
        }


        @Override
        public String getUsername(){
            return email;
        }

        @Override
        public String getPassword(){
            return password;
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    }
