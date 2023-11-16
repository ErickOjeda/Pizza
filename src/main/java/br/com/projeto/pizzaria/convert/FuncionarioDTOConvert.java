    package br.com.projeto.pizzaria.convert;

    import br.com.projeto.pizzaria.dto.FuncionarioDTO;
    import br.com.projeto.pizzaria.entity.Funcionario;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    @Component
    public class FuncionarioDTOConvert {

        @Autowired
        private ModelMapper modelMapper;

        public FuncionarioDTO convertFuncionarioToFuncionarioDTO(Funcionario funcionario){

            FuncionarioDTO funcionarioDTO = modelMapper.map(funcionario, FuncionarioDTO.class);
            return funcionarioDTO;
        }

        public Funcionario convertFuncionarioDTOToFuncionario(FuncionarioDTO funcionarioDTO){

            Funcionario funcionario = modelMapper.map(funcionarioDTO, Funcionario.class);
            return funcionario;
        }
    }
