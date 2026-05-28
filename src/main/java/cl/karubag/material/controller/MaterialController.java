package cl.karubag.material.controller;

import cl.karubag.material.dto.MaterialDTO;
import cl.karubag.material.model.TipoMaterial;
import cl.karubag.material.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<MaterialDTO>> listarActivos() {
        return ResponseEntity.ok(materialService.listarActivos());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<MaterialDTO>> listarPorTipo(@PathVariable TipoMaterial tipo) {
        return ResponseEntity.ok(materialService.listarPorTipo(tipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<MaterialDTO> crear(@Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.ok(materialService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        materialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
