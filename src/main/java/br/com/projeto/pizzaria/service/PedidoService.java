    package br.com.projeto.pizzaria.service;

    import br.com.projeto.pizzaria.convert.FuncionarioDTOConvert;
    import br.com.projeto.pizzaria.convert.UsuarioDTOConvert;
    import br.com.projeto.pizzaria.dto.ItemDTO;
    import br.com.projeto.pizzaria.dto.PedidoDTO;
    import br.com.projeto.pizzaria.entity.Item;
    import br.com.projeto.pizzaria.entity.Pedido;
    import br.com.projeto.pizzaria.repository.PedidoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.util.Assert;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;

        @Autowired
        private UsuarioDTOConvert usuarioDTOConvert;

        @Autowired
        private FuncionarioDTOConvert funcionarioDTOConvert;

        @Autowired
        private ItemService itemService;


        public PedidoDTO criar(PedidoDTO pedidoDTO){

           Pedido pedido = toPedido(pedidoDTO);

            if(pedido.getItem() == null){
               System.out.println("lista nula");
           }else{
               System.out.println(pedido.getItem().size());
           }

           pedidoRepository.save(pedido);
            calcularValorTotalPedido(pedido.getId());

           return toPedidoDTO(pedido);
        }

        public PedidoDTO findById(Long id){
            Pedido pedidoBanco = pedidoRepository.findById(id).orElse(null);

            return toPedidoDTO(pedidoBanco);
        }

        public List<PedidoDTO> findAllPedido(){
            List<Pedido> pedidosBanco = pedidoRepository.findAll();
            List<PedidoDTO> pedidoDTOList = new ArrayList<>();

            for(int i = 0; i < pedidosBanco.size(); i++){
                pedidoDTOList.add(toPedidoDTO(pedidosBanco.get(i)));
            }

            return pedidoDTOList;
        }

        public PedidoDTO editar(Long id, PedidoDTO pedidoDTO){
            Pedido pedido = this.pedidoRepository.findById(id).orElse(null);

            Assert.isTrue(pedido != null, "Pedido nao encontrado");

            this.pedidoRepository.save(toPedido(pedidoDTO));
            calcularValorTotalPedido(id);

            return pedidoDTO;
        }

        public String deletar(Long id){
            Pedido pedido = this.pedidoRepository.findById(id).orElse(null);

            Assert.isTrue(pedido != null, "Pedido nao encontrado");

            this.pedidoRepository.delete(pedido);

            return "Pedido deletado";
        }

        public PedidoDTO calcularValorTotalPedido(Long pedidoId) {
            Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

            Assert.isTrue(pedido != null, "Pedido não encontrado");

            List<Item> itens = pedido.getItem();
            float valorTotal = 0.0f; // Inicialize com 0.0f para garantir que seja float

            if (itens != null) {
                for (Item item : itens) {
                    valorTotal += item.getValor(); // Somando valores float
                }
            }

            pedido.setValorTotal(valorTotal);
            pedidoRepository.save(pedido);

            return toPedidoDTO(pedido);
        }



        public PedidoDTO toPedidoDTO(Pedido pedido){
            PedidoDTO pedidoDTO = new PedidoDTO();

            pedidoDTO.setId(pedido.getId());
            pedidoDTO.setNome(pedido.getNome());
            pedidoDTO.setObservacao(pedido.getObservacao());
            pedidoDTO.setValorTotal(pedido.getValorTotal());
            pedidoDTO.setEntrega(pedido.getEntrega());

            if (pedido.getFuncionario() !=null){
                pedidoDTO.setFuncionario(funcionarioDTOConvert.convertFuncionarioToFuncionarioDTO(pedido.getFuncionario()));
            }

            if(pedido.getUsuario() != null){

                pedidoDTO.setUsuario(usuarioDTOConvert.convertUsuarioToUsuarioDTO(pedido.getUsuario()));
            }

            List<ItemDTO> itemsdump = new ArrayList<>();

            if(pedido.getItem() != null){
                for(int i = 0; i < pedido.getItem().size(); i++){
                    itemsdump.add(itemService.toItemDTO(pedido.getItem().get(i)));
                }
            }

            pedidoDTO.setItem(itemsdump);
            return pedidoDTO;
        }

        public Pedido toPedido(PedidoDTO pedidoDTO){
            Pedido pedido = new Pedido();

            pedido.setId(pedidoDTO.getId());
            pedido.setNome(pedidoDTO.getNome());
            pedido.setObservacao(pedidoDTO.getObservacao());
            pedido.setValorTotal(pedidoDTO.getValorTotal());
            pedido.setEntrega(pedidoDTO.getEntrega());

            if (pedidoDTO.getFuncionario() != null){
                pedido.setFuncionario(funcionarioDTOConvert.convertFuncionarioDTOToFuncionario(pedidoDTO.getFuncionario()));
            }

            if(pedidoDTO.getUsuario() != null){
                pedido.setUsuario(usuarioDTOConvert.convertUsuarioDTOToUsuario(pedidoDTO.getUsuario()));
            }

            List<Item>  itemList = new ArrayList<>();

            if(pedidoDTO.getItem() != null){
                for(int i = 0; i < pedidoDTO.getItem().size(); i++){
                    itemList.add(itemService.toItem(pedidoDTO.getItem().get(i)));
                }
            }

            pedido.setItem(itemList);
            return pedido;
        }
    }
