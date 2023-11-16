    package br.com.projeto.pizzaria.utils;

    import br.com.projeto.pizzaria.dto.UsuarioDTO;
    import br.com.projeto.pizzaria.entity.Usuario;
    import org.modelmapper.ModelMapper;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.stereotype.Component;

    @Configuration
    public class MapperConfig {
    
        @Bean
        public ModelMapper modelMapper(){
           // return new ModelMapper();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.typeMap(Usuario.class, UsuarioDTO.class)
              .addMapping(src -> src.getEnderecos(), UsuarioDTO::setEnderecos);

            return modelMapper;
        }
    }
