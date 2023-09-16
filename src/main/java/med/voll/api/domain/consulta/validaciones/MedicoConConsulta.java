package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.CunsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;

public class MedicoConConsulta {

    private CunsultaRepository cunsultaRepository;

    private void validar(DatosAgendarConsulta datosAgendarConsulta){

        if(datosAgendarConsulta.idMedico() == null)
            return;

        var medicoConConsulta = cunsultaRepository.existByMedicoIdAndData(datosAgendarConsulta.idMedico(),
                datosAgendarConsulta.fecha());
        if (medicoConConsulta){
            throw new ValidationException("Éste médico ya tiene una consulta en ese horario");
        }

    }
}
