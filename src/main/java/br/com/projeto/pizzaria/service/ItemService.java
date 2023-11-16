    package br.com.projeto.pizzaria.service;

    import br.com.projeto.pizzaria.convert.UsuarioDTOConvert;
    import br.com.projeto.pizzaria.dto.ItemDTO;
    import br.com.projeto.pizzaria.dto.PedidoDTO;
    import br.com.projeto.pizzaria.entity.Item;
    import br.com.projeto.pizzaria.entity.Pedido;
    import br.com.projeto.pizzaria.repository.ItemRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.util.Assert;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class ItemService {

        @Autowired
        private ItemRepository itemRepository;

        @Autowired
        private UsuarioDTOConvert usuarioDTOConvert;

        public ItemDTO criar(ItemDTO itemDTO){
            Item item = toItem(itemDTO);

            itemRepository.save(item);

            return toItemDTO(item);
        }

        public ItemDTO findById(Long id){
            Item itemBanco = itemRepository.findById(id).orElse(null);

            return toItemDTO(itemBanco);
        }

        public List<ItemDTO> findAllItens(){
            List<Item> itensBanco = itemRepository.findAll();
            List<ItemDTO> itensDTOList = new ArrayList<>();

            for(int i = 0; i < itensBanco.size(); i++){
                itensDTOList.add(toItemDTO(itensBanco.get(i)));
            }
            return itensDTOList;
        }

        public ItemDTO editar(Long id, ItemDTO itemDTO){
            Item item = this.itemRepository.findById(id).orElse(null);

            Assert.isTrue(item != null, "Item nao encontrado");

            this.itemRepository.save(toItem(itemDTO));

            return itemDTO;
        }

        public String deletar(Long id){
            Item item = this.itemRepository.findById(id).orElse(null);

            Assert.isTrue(item != null, "Item nao encontrado");

            this.itemRepository.delete(item);

            return "Item deletado";
        }

        public ItemDTO toItemDTO(Item item){
            ItemDTO itemDTO = new ItemDTO();

            itemDTO.setId(item.getId());
            itemDTO.setNome(item.getNome());
            itemDTO.setTamanho(item.getTamanho());
            itemDTO.setPossuiSabores(item.isPossuiSabores());
            itemDTO.setValor(item.getValor());
            List<PedidoDTO> pedidoDTOList = new ArrayList<>();

            itemDTO.setPedido(pedidoDTOList);
            return itemDTO;
        }

        public Item toItem(ItemDTO itemDTO){
            Item item = new Item();

            item.setId(itemDTO.getId());
            item.setNome(itemDTO.getNome());
            item.setTamanho(itemDTO.getTamanho());
            item.setPossuiSabores(itemDTO.isPossuiSabores());
            item.setValor(itemDTO.getValor());

            List<Pedido> pedidoList = new ArrayList<>();

            return item;    }

        public Pedido toPedido(PedidoDTO pedidoDTO){
            Pedido pedido = new Pedido();

            pedido.setId(pedidoDTO.getId());
            pedido.setNome(pedidoDTO.getNome());
            pedido.setObservacao(pedidoDTO.getObservacao());
            pedido.setUsuario(usuarioDTOConvert.convertUsuarioDTOToUsuario(pedidoDTO.getUsuario()));

            List<Item>  itemList = new ArrayList<>();

            if(pedidoDTO.getItem() != null){
                for(int i = 0; i < pedidoDTO.getItem().size(); i++){
                    itemList.add(toItem(pedidoDTO.getItem().get(i)));
                }
            }

            pedido.setItem(itemList);
            return pedido;
        }

        public PedidoDTO toPedidoDTO(Pedido pedido){
            PedidoDTO pedidoDTO = new PedidoDTO();

            pedidoDTO.setId(pedido.getId());
            pedidoDTO.setNome(pedido.getNome());
            pedidoDTO.setObservacao(pedido.getObservacao());
            pedidoDTO.setUsuario(usuarioDTOConvert.convertUsuarioToUsuarioDTO(pedido.getUsuario()));

            List<ItemDTO> itemsdump = new ArrayList<>();

            if(pedido.getItem() != null){
                for(int i = 0; i < pedido.getItem().size(); i++){
                    itemsdump.add(toItemDTO(pedido.getItem().get(i)));
                }
            }

            pedidoDTO.setItem(itemsdump);
            return pedidoDTO;
        }
    }
