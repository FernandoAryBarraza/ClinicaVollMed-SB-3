package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository cunsultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        var primerHorario = datosAgendarConsulta.fecha().withHour(7);

        var ultimoHorario = datosAgendarConsulta.fecha().withHour(18);

        var pacienteConConsulta = cunsultaRepository.existsByPacienteIdAndDataBetween(datosAgendarConsulta.idPaciente(),
                primerHorario, ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese día");
        }

    }
}
