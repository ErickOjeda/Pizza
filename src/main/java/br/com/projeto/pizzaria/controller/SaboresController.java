    package br.com.projeto.pizzaria.controller;

    import br.com.projeto.pizzaria.dto.SaboresDTO;
    import br.com.projeto.pizzaria.service.SaboresService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;

    @RestController
    @RequestMapping("/api/sabor")
    @CrossOrigin(origins = "http://localhost:4200")
    public class SaboresController {

        @Autowired
        private SaboresService saboresService;

        @PostMapping
        public ResponseEntity<SaboresDTO> criar (@RequestBody SaboresDTO saboresDTO){
            try{
                return ResponseEntity.ok(saboresService.criar(saboresDTO));
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @GetMapping
        public ResponseEntity<List<SaboresDTO>> buscarTodos(){
            try{
                return ResponseEntity.ok(saboresService.findAllSabores());
            } catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @GetMapping("/buscar/{id}")
        public  ResponseEntity<SaboresDTO> buscarId(@RequestParam("id")Long id){
            try{
                return ResponseEntity.ok(saboresService.findById(id));
            } catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @PutMapping
        public ResponseEntity<SaboresDTO> editar(@RequestBody SaboresDTO saboresDTO){
            try{
                return ResponseEntity.ok(saboresService.editar(saboresDTO));
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> deletar(@PathVariable("id") Long id){
            try{
                saboresService.deletar(id);
                return ResponseEntity.ok(HttpStatus.OK);
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
    }
