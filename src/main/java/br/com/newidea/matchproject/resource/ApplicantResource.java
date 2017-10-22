package br.com.newidea.matchproject.resource;

import br.com.newidea.matchproject.dto.request.ApplicantRequestDTO;
import br.com.newidea.matchproject.dto.response.ApplicantRankingResponseDTO;
import br.com.newidea.matchproject.service.ApplicantService;
import br.com.newidea.matchproject.translator.ApplicantTranslator;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author fabio
 * @since 21/10/17 19:51
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/applicant")
@Api(value = "Candidato", description = "Operações disponíveis para o recurso Applicant")
public class ApplicantResource {

    @Autowired
    private ApplicantTranslator translator;

    @Autowired
    private ApplicantService service;

    @Metered
    @ExceptionMetered
    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de um novo recurso applicant", responseReference = "URI do novo recurso criado.")
    public ResponseEntity<ApplicantRequestDTO> create(@Valid @RequestBody ApplicantRequestDTO requestDTO) {

        log.info("ApplicantResource.create-start");

        service.process(requestDTO);

        log.info("ApplicantResource.create-end");
        return ResponseEntity.ok(requestDTO);
    }


    @Metered
    @ExceptionMetered
    @GetMapping(path = "/ranking", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ranking de candidatos e o percentual do seu perfil comportamentel comparado com a empresa.")
    public ResponseEntity<List<ApplicantRankingResponseDTO>> ranking() {

        log.info("ApplicantResource.ranking-start");

        List<ApplicantRankingResponseDTO> rankingResponseDTO = translator.toRankingDTO(service.findAllOrderByPercentDesc());

        log.info("ApplicantResource.ranking-end");
        return ResponseEntity.ok(rankingResponseDTO);
    }


}
