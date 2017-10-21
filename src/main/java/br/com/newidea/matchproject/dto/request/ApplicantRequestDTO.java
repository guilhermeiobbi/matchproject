package br.com.newidea.matchproject.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author fabio
 * @since 21/10/17 19:54
 */

@Data
@Builder
public class ApplicantRequestDTO {

    @NotNull(message = "Campo \"name\" precisa ser informado.")
    @ApiModelProperty(notes = "Nome do candidato.", example = "Fabio Miranda", required = true)
    private String name;

    @NotNull(message = "Campo \"metrics\" precisa ser informado.")
    @ApiModelProperty(notes = "Métricas do candidato.", example = "....", required = true)
    private List<ApplicantMetricsRequestDTO> metrics;
}
