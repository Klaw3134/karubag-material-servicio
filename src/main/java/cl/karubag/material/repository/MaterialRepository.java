package cl.karubag.material.repository;

import cl.karubag.material.model.Material;
import cl.karubag.material.model.TipoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByActivoTrue();

    List<Material> findByTipo(TipoMaterial tipo);

    boolean existsByNombre(String nombre);
}
