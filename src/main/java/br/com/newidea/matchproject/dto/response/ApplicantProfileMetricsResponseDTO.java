package br.com.newidea.matchproject.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author fabio
 * @since 22/10/17 00:52
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantProfileMetricsResponseDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "Nome da métrica analisada.", required = false, position = 0)
    private String name;

    @JsonProperty("percent")
    @ApiModelProperty(notes = "Percentual obtido nesta métrica pelo candidato.", required = false, position = 1)
    private BigDecimal percent;
}
