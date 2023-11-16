    package br.com.projeto.pizzaria.dto;


    import lombok.Getter;
    import lombok.Setter;

    import java.util.List;

    @Getter
    @Setter
    public class PedidoDTO {
        private Long id;

        private String nome;

        private String observacao;

        private Float valorTotal;

        private UsuarioDTO usuario;

        private FuncionarioDTO funcionario;

        private Boolean entrega;

        private List<ItemDTO> item;

        public PedidoDTO(){

        }

        public PedidoDTO(Long id, String nome, String observacao,Float valorTotal, UsuarioDTO usuarioDTO, FuncionarioDTO funcionario) {
            this.id = id;
            this.nome = nome;
            this.valorTotal = valorTotal;
            this.observacao = observacao;
            this.usuario = usuarioDTO;
            this.funcionario = funcionario;
        }
    }
