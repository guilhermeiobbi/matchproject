package br.com.newidea.matchproject.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author fabio
 * @since 22/10/17 00:44
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantProfileTypeResponseDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "Nome do tipo de perfil analisada.", required = false, position = 0)
    private String name;

    @ApiModelProperty(notes = "Percentual analisado.", required = false, position = 0)
    private BigDecimal porcent;


    private List<ApplicantProfileMetricsResponseDTO> metrics;
}
