package br.com.newidea.matchproject.resource;

import br.com.newidea.matchproject.dto.request.ApplicantRequestDTO;
import br.com.newidea.matchproject.service.ApplicantService;
import br.com.newidea.matchproject.translator.ApplicantTranslator;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criação de um novo recurso applicant", responseReference = "URI do novo recurso criado.")
    public ResponseEntity<Void> create(@Valid @RequestBody ApplicantRequestDTO requestDTO) {

//        log.info("[ApplicantResource-create] Traduzindo requisição recebida, requestDTO={}", requestDTO);
//        final ApplicantDTO applicantDTO = translator.toDTO(requestDTO);
//
//        final ProposalDTO proposalDTO = proposalTranslator.toDTO(requestDTO.getInitialProposal());
//
//
//        log.info("Acionando service, applicantDTO={}", applicantDTO);
//        final ApplicantEntity entity = service.create(applicantDTO, proposalDTO);
//
//        log.info("Gerando novo URI criado, entity={}", entity);
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{userClientId}/{userTattooistId}/{itemId}")
//                .buildAndExpand(entity.getApplicantId().getUserClientId(),
//                        entity.getApplicantId().getUserTattooistId(),
//                        entity.getApplicantId().getItemId())
//                .toUri();
//
//        log.info("[ApplicantResource-create-end] Retornando novo URI, URI={}", uri);
//        return ResponseEntity.created(uri).build();

        return ResponseEntity.ok().build();
    }
}
