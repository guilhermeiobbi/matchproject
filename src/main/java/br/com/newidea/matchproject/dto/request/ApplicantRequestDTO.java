package br.com.newidea.matchproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fabio
 * @since 21/10/17 19:54
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRequestDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "Nome do candidato.", example = "Fabio Miranda", required = false, position = 0)
    private String name;


    @JsonProperty("metrics")
    @ApiModelProperty(notes = "Métricas do candidato.", required = false, position = 1)
    private ApplicantMetricsRequestDTO metrics;
}
