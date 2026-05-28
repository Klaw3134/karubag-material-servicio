package cl.karubag.material.service;

import cl.karubag.material.dto.MaterialDTO;
import cl.karubag.material.model.Material;
import cl.karubag.material.model.TipoMaterial;
import cl.karubag.material.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<MaterialDTO> listarTodos() {
        return materialRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MaterialDTO> listarActivos() {
        return materialRepository.findByActivoTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MaterialDTO> listarPorTipo(TipoMaterial tipo) {
        return materialRepository.findByTipo(tipo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MaterialDTO obtenerPorId(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado con id: " + id));
        return toDTO(material);
    }

    public MaterialDTO crear(MaterialDTO dto) {
        if (materialRepository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe un material con el nombre: " + dto.getNombre());
        }
        return toDTO(materialRepository.save(toEntity(dto)));
    }

    public MaterialDTO actualizar(Long id, MaterialDTO dto) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado con id: " + id));
        material.setNombre(dto.getNombre());
        material.setDescripcion(dto.getDescripcion());
        material.setTipo(dto.getTipo());
        material.setPrecioPorKilo(dto.getPrecioPorKilo());
        material.setActivo(dto.getActivo());
        return toDTO(materialRepository.save(material));
    }

    public void eliminar(Long id) {
        materialRepository.deleteById(id);
    }

    private MaterialDTO toDTO(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(material.getId());
        dto.setNombre(material.getNombre());
        dto.setDescripcion(material.getDescripcion());
        dto.setTipo(material.getTipo());
        dto.setPrecioPorKilo(material.getPrecioPorKilo());
        dto.setActivo(material.getActivo());
        return dto;
    }

    private Material toEntity(MaterialDTO dto) {
        Material material = new Material();
        material.setNombre(dto.getNombre());
        material.setDescripcion(dto.getDescripcion());
        material.setTipo(dto.getTipo());
        material.setPrecioPorKilo(dto.getPrecioPorKilo());
        material.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        return material;
    }
}
