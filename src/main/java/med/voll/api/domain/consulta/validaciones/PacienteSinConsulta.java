package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.CunsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private CunsultaRepository cunsultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        var primerHorario = datosAgendarConsulta.fecha().withHour(7);

        var ultimoHorario = datosAgendarConsulta.fecha().withHour(18);

        var pacienetConConsulta = cunsultaRepository.existByPcienteIdAndDataBetween(datosAgendarConsulta.idPaciente(),
                primerHorario, ultimoHorario);

        if(pacienetConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese día");
        }

    }
}
