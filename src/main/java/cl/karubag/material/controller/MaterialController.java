package cl.karubag.material.controller;

import cl.karubag.material.dto.MaterialDTO;
import cl.karubag.material.model.TipoMaterial;
import cl.karubag.material.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Materiales", description = "Gestión de materiales reciclables Karübag")
@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Operation(summary = "Listar todos los materiales", description = "Retorna la lista completa de materiales")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<MaterialDTO>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @Operation(summary = "Listar materiales activos", description = "Retorna solo los materiales activos")
    @ApiResponse(responseCode = "200", description = "Lista de materiales activos")
    @GetMapping("/activos")
    public ResponseEntity<List<MaterialDTO>> listarActivos() {
        return ResponseEntity.ok(materialService.listarActivos());
    }

    @Operation(summary = "Listar por tipo", description = "Retorna materiales filtrados por tipo")
    @ApiResponse(responseCode = "200", description = "Lista filtrada por tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<MaterialDTO>> listarPorTipo(@PathVariable TipoMaterial tipo) {
        return ResponseEntity.ok(materialService.listarPorTipo(tipo));
    }

    @Operation(summary = "Obtener material por ID", description = "Busca un material por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material encontrado"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.obtenerPorId(id));
    }

    @Operation(summary = "Crear material", description = "Crea un nuevo material reciclable")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Material creado exitosamente",
            content = @Content(schema = @Schema(implementation = MaterialDTO.class),
            examples = @ExampleObject(value = "{\"nombre\": \"Cartón\", \"tipo\": \"CARTON\", \"precioPorKilo\": 150.0, \"activo\": true}"))),
        @ApiResponse(responseCode = "409", description = "Ya existe un material con ese nombre")
    })
    @PostMapping
    public ResponseEntity<MaterialDTO> crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del material a crear",
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\"nombre\": \"Cartón\", \"tipo\": \"CARTON\", \"precioPorKilo\": 150.0, \"activo\": true}")))
        @Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.crear(dto));
    }

    @Operation(summary = "Actualizar material", description = "Actualiza los datos de un material")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Material actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Material no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MaterialDTO dto) {
        return ResponseEntity.ok(materialService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar material", description = "Elimina un material por su ID")
    @ApiResponse(responseCode = "204", description = "Material eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        materialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
