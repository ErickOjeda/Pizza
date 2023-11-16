    package br.com.projeto.pizzaria.dto;

    import lombok.Getter;
    import lombok.Setter;

    import java.util.List;

    @Getter
    @Setter
    public class ItemDTO {

        private Long id;

        private List<PedidoDTO> pedido;

        private String nome;

        private Float valor;

        private boolean possuiSabores;

        private String tamanho;

        private List<SaboresDTO> sabores;

        public ItemDTO(){

        }

        public ItemDTO(Long id, List<PedidoDTO> pedidoDTO, String tamanho, Boolean entrega, List<SaboresDTO> saboresDTO, Float valor) {
            this.id = id;
            this.pedido = pedidoDTO;
            this.tamanho = tamanho;
            this.sabores = saboresDTO;
            this.valor = valor;
        }
    }
