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
 * @since 22/10/17 00:35
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRankingResponseDTO {

    @JsonProperty("applicantId")
    @ApiModelProperty(notes = "Id do candidato.", example = "1", required = false, position = 0)
    private Long applicandId;

    @JsonProperty("applicant_name")
    @ApiModelProperty(notes = "Nome do candidato.", example = "Giobbi", required = false, position = 1)
    private String applicantName;

    @JsonProperty("percent")
    @ApiModelProperty(notes = "Percentual de match entre candidato e empresa.", example = "100.00", dataType = "numeric", required = false, position = 2)
    private BigDecimal percent;

    @JsonProperty("profiles")
    @ApiModelProperty(notes = "Perfis analisados do candidato para traçar seu perfil comportamental.", required = false, position = 3)
    private List<ApplicantProfileTypeResponseDTO> profiles;


}
