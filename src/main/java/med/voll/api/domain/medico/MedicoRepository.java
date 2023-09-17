package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    //primer cambio en IntellIj

    @Query("""
            select m from Medico m
            where m.activo= 1 and
            m.especialidad= especialidad and
            m.id not in(
            select c.medico.id from Consulta c
            c.data=:fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query("""
            select m.activo
            from Medicos m
            where m.id=:idMedicos 
            """)
    Boolean findActivoById(Long idMedicos);
}
