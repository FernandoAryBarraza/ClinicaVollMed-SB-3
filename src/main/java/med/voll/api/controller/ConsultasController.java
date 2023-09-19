package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {
    @Autowired
    private AgendaDeConsultaService agendaDeConsultaService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una consulta en la base de datos",
            description = "",
            tags = {"consulta", "post"}
    )
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datosAgendarConsulta) throws ValidacionDeIntegridad {

        var response =  agendaDeConsultaService.agendar(datosAgendarConsulta);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Operation(
            summary = "cancelar una consulta de la agenda",
            description = "requiere motivo",
            tags = {"consulta", "delete"}
    )
    public ResponseEntity cancelar(DatosCancelamientoConsulta datosCancelamientoConsulta){
        agendaDeConsultaService.cancelar(datosCancelamientoConsulta);
        return ResponseEntity.noContent().build();
    }
}
