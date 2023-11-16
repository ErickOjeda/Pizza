    package br.com.projeto.pizzaria.controller;

    import br.com.projeto.pizzaria.dto.LoginDTO;
    import br.com.projeto.pizzaria.service.LoginService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;

    @RestController
    @RequestMapping("/api/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public class LoginController {

        @Autowired
        private LoginService loginService;

        @PostMapping("/cadastrar")
        public ResponseEntity<LoginDTO> criar(@RequestBody LoginDTO loginDTO){
            try{
                return ResponseEntity.ok(loginService.criar(loginDTO));
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }


        @GetMapping("/todos")
        public ResponseEntity<List<LoginDTO>> buscarTodos(){
            try{
                return ResponseEntity.ok(loginService.buscarTodos());
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @PutMapping
        public ResponseEntity<LoginDTO> editar(@RequestParam("id") Long id, @RequestBody LoginDTO loginDTO){
            try{
                return ResponseEntity.ok(loginService.editar(id,loginDTO));
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @DeleteMapping("/deletar")
        public ResponseEntity<String> deletar(@RequestParam("id")Long id){
            try{
                return ResponseEntity.ok(loginService.deletar(id));
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

    }
