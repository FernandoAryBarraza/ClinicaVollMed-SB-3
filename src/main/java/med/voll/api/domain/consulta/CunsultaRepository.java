package med.voll.api.domain.consulta;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CunsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existByPcienteIdAndDataBetween(Long aLong, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existByMedicoIdAndData(Long aLong, LocalDateTime fecha);
}
