package br.com.newidea.matchproject.service;

import br.com.newidea.matchproject.domain.ApplicantCharacteristicEntity;
import br.com.newidea.matchproject.domain.ApplicantCharacteristicMetricsEntity;
import br.com.newidea.matchproject.domain.ApplicantEntity;
import br.com.newidea.matchproject.domain.ApplicantProfileTypeEntity;
import br.com.newidea.matchproject.dto.request.*;
import br.com.newidea.matchproject.enums.PersonalityInsightsEnum;
import br.com.newidea.matchproject.enums.ProfileCharacteristicEnum;
import br.com.newidea.matchproject.enums.ProfileTypeEnum;
import br.com.newidea.matchproject.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fabio
 * @since 21/10/17 19:53
 */

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public List<ApplicantEntity> findAllOrderByPercentDesc() {
        return applicantRepository.findAllOrderByPercentDesc();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void process(ApplicantRequestDTO requestDTO) {

        ApplicantEntity applicantEntity = ApplicantEntity.builder()
                .name(requestDTO.getName())
                .percent(new BigDecimal(0))
                .build();

        applicantEntity.setProfiles(getProfiles(applicantEntity, requestDTO));

        applicantEntity = calculatePercents(applicantEntity);
        applicantRepository.save(applicantEntity);
    }

    private ApplicantEntity calculatePercents(ApplicantEntity applicantEntity) {

        BigDecimal applicantPercentSum = new BigDecimal(0);

        for (ApplicantProfileTypeEntity profile : applicantEntity.getProfiles()) {

            BigDecimal characteristicMetricSum = new BigDecimal(0);

            for (ApplicantCharacteristicEntity characteristic : profile.getCharacteristics()) {

                BigDecimal percentMetricSum = new BigDecimal(0);
                for (ApplicantCharacteristicMetricsEntity metric : characteristic.getMetrics())
                    percentMetricSum = percentMetricSum.add(new BigDecimal(metric.getPercent().toString()));


                if (percentMetricSum.compareTo(BigDecimal.ZERO) > 0) {
                    percentMetricSum = percentMetricSum.divide(new BigDecimal(characteristic.getMetrics().size() * 100), 5, RoundingMode.HALF_UP);
                    percentMetricSum = percentMetricSum.multiply(new BigDecimal(100));
                    characteristic.setPercent(percentMetricSum);
                }

                characteristicMetricSum = characteristicMetricSum.add(characteristic.getPercent());
            }

            if (characteristicMetricSum.compareTo(BigDecimal.ZERO) > 0) {
                characteristicMetricSum = characteristicMetricSum.divide(new BigDecimal(profile.getCharacteristics().size() * 100), 5, RoundingMode.HALF_UP);
                characteristicMetricSum = characteristicMetricSum.multiply(new BigDecimal(100));
                profile.setPercent(characteristicMetricSum);
            }

            applicantPercentSum = applicantPercentSum.add(profile.getPercent());
        }

        if (applicantPercentSum.compareTo(BigDecimal.ZERO) > 0) {
            applicantPercentSum = applicantPercentSum.divide(new BigDecimal(applicantEntity.getProfiles().size() * 100));
            applicantPercentSum = applicantPercentSum.multiply(new BigDecimal(100));
            applicantEntity.setPercent(applicantPercentSum);
        }

        return applicantEntity;
    }

    private List<ApplicantProfileTypeEntity> getProfiles(ApplicantEntity applicant, ApplicantRequestDTO requestDTO) {

        final List<ApplicantProfileTypeEntity> profiles = new ArrayList<ApplicantProfileTypeEntity>();

        ApplicantProfileTypeEntity profileTypeEntity = ApplicantProfileTypeEntity.builder()
                .applicant(applicant)
                .name(ProfileTypeEnum.EMPLOYER_BRANDING.getName())
                .percent(new BigDecimal(0))
                .build();
        profileTypeEntity.setCharacteristics(getCharacteristics(ProfileTypeEnum.EMPLOYER_BRANDING, profileTypeEntity, requestDTO));
        profiles.add(profileTypeEntity);

        profileTypeEntity = ApplicantProfileTypeEntity.builder()
                .applicant(applicant)
                .name(ProfileTypeEnum.EXPERIAN_WAY.getName())
                .percent(new BigDecimal(0))
                .build();
        profileTypeEntity.setCharacteristics(getCharacteristics(ProfileTypeEnum.EXPERIAN_WAY, profileTypeEntity, requestDTO));
        profiles.add(profileTypeEntity);

        profileTypeEntity = ApplicantProfileTypeEntity.builder()
                .applicant(applicant)
                .name(ProfileTypeEnum.DIGITAL.getName())
                .percent(new BigDecimal(0))
                .build();
        profileTypeEntity.setCharacteristics(getCharacteristics(ProfileTypeEnum.DIGITAL, profileTypeEntity, requestDTO));
        profiles.add(profileTypeEntity);

        profileTypeEntity = ApplicantProfileTypeEntity.builder()
                .applicant(applicant)
                .name(ProfileTypeEnum.FAST_PLAY.getName())
                .percent(new BigDecimal(0))
                .build();
        profileTypeEntity.setCharacteristics(getCharacteristics(ProfileTypeEnum.FAST_PLAY, profileTypeEntity, requestDTO));
        profiles.add(profileTypeEntity);


        return profiles;
    }

    private List<ApplicantCharacteristicEntity> getCharacteristics(ProfileTypeEnum profileType, ApplicantProfileTypeEntity profileTypeEntity, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicEntity> characteristics = new ArrayList<ApplicantCharacteristicEntity>();
        ApplicantCharacteristicEntity characteristic = null;

        switch (profileType) {
            case DIGITAL:
                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.INCONFORMISMO.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.INCONFORMISMO, characteristic, requestDTO));
                characteristics.add(characteristic);


                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.INOVACAO.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.INOVACAO, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.VELOCIDADE.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.VELOCIDADE, characteristic, requestDTO));
                characteristics.add(characteristic);
                break;


            case EMPLOYER_BRANDING:
                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.COLABORATIVO.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.COLABORATIVO, characteristic, requestDTO));
                characteristics.add(characteristic);


                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.RESILIENTE.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.RESILIENTE, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.TRANSFORMACAO.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.TRANSFORMACAO, characteristic, requestDTO));
                characteristics.add(characteristic);
                break;

            case EXPERIAN_WAY:
                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.ENCANTA_CLIENTES.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.ENCANTA_CLIENTES, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.PROTEGE_NOSSO_FUTURO.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.PROTEGE_NOSSO_FUTURO, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.VALORIZA_AS_PESSOAS.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.VALORIZA_AS_PESSOAS, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.COLABORA_PARA_VENCER.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.COLABORA_PARA_VENCER, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.INOVA_PARA_CRESCER.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.INOVA_PARA_CRESCER, characteristic, requestDTO));
                characteristics.add(characteristic);
                break;

            case FAST_PLAY:
                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.AGILIDADE.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.AGILIDADE, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.VELOCIDADE.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.VELOCIDADE, characteristic, requestDTO));
                characteristics.add(characteristic);

                characteristic = ApplicantCharacteristicEntity.builder()
                        .profile(profileTypeEntity)
                        .name(ProfileCharacteristicEnum.SIMPLICIDADE.getName())
                        .percent(new BigDecimal(0))
                        .build();
                characteristic.setMetrics(getMetrics(ProfileCharacteristicEnum.SIMPLICIDADE, characteristic, requestDTO));
                characteristics.add(characteristic);

                break;

            default:
                break;
        }

        return characteristics;
    }

    private List<ApplicantCharacteristicMetricsEntity> getMetrics(ProfileCharacteristicEnum profileCharacteristic,
                                                                  ApplicantCharacteristicEntity characteristic,
                                                                  ApplicantRequestDTO requestDTO) {

        List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();


        switch (profileCharacteristic) {
            case COLABORATIVO:
                metrics = buildMetricsColaborativo(
                        characteristic,
                        requestDTO
                );
                break;

            case RESILIENTE:
                metrics = buildMetricsResiliente(
                        characteristic,
                        requestDTO
                );
                break;

            case TRANSFORMACAO:
                metrics = buildMetricsTransformacao(
                        characteristic,
                        requestDTO
                );
                break;

            case ENCANTA_CLIENTES:
                metrics = buildMetricsEncantaClientes(
                        characteristic,
                        requestDTO
                );
                break;

            case PROTEGE_NOSSO_FUTURO:
                metrics = buildMetricsProtegeNossoFuturo(
                        characteristic,
                        requestDTO
                );
                break;

            case VALORIZA_AS_PESSOAS:
                metrics = buildMetricsValorizaAsPessoas(
                        characteristic,
                        requestDTO
                );
                break;

            case COLABORA_PARA_VENCER:
                metrics = buildMetricsColaborarParaVencer(
                        characteristic,
                        requestDTO
                );
                break;

            case INOVA_PARA_CRESCER:
                metrics = buildMetricsInovaParaCrescer(
                        characteristic,
                        requestDTO
                );
                break;

            case INCONFORMISMO:
                metrics = buildMetricsInconformismo(
                        characteristic,
                        requestDTO
                );
                break;

            case INOVACAO:
                metrics = buildMetricsInovacao(
                        characteristic,
                        requestDTO
                );
                break;

            case VELOCIDADE:
                metrics = buildMetricsVelocidade(
                        characteristic,
                        requestDTO
                );
                break;

            case AGILIDADE:
                metrics = buildMetricsAgilidade(
                        characteristic,
                        requestDTO
                );
                break;

            case SIMPLICIDADE:
                metrics = buildMetricsSimplicidade(
                        characteristic,
                        requestDTO
                );
                break;
        }
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsSimplicidade(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {
        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_INTELECTO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_CAUTELA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_PROPENSO_A_SE_PREOCUPAR,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_AUTOCONSCIENCIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_RETRAIMENTO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_CONSERVAÇÃO,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsAgilidade(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {
        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_CAUTELA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_CONFIANCA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_AUTOCONSCIENCIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_DESAFIO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_ABERTURA_MUDANCA,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsVelocidade(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_REGULARIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_CONFIANCA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_AUTOCONSCIENCIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_EMPOLGACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_ESTABILIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_ABERTURA_MUDANCA,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsInovacao(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {
        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_IMAGINAÇÃO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_INTELECTO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_AUTO_EFICIENCIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_ALTRUISMO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_CONFIANCA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_AUTOCONSCIENCIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_AUTOCONSCIENCIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_DESAFIO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_CURIOSIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_ABERTURA_MUDANCA,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsInconformismo(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {
        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_DESAFIO_A_AUTORIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_PROPENSO_A_SE_PREOCUPAR,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_SUSCETIVEL_AO_STRESS,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_RETRAIMENTO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_EXPRESSAO_PROPRIA_PERSONALIDADE,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_AUTOTRANSCEDENCIA,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsInovaParaCrescer(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_DESEJO_AVENTURA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_IMAGINAÇÃO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_DESAFIO_A_AUTORIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_CONFIANCA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_DESAFIO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_EMPOLGACAO,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsColaborarParaVencer(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_DESEJO_AVENTURA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_AUTO_DISCIPLINA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_NIVEL_ATIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_BOM_HUMOR,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_ALTRUISMO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_COOPERACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_DESAFIO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_EMPOLGACAO,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsValorizaAsPessoas(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_INTELECTO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_RESPEITO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_ALTRUISMO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_MODESTIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_COOPERACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_SIMPATIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_IDEAL,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsProtegeNossoFuturo(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_DESEJO_AVENTURA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_CAUTELA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_RESPEITO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_AUTO_DISCIPLINA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_NIVEL_ATIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_COOPERACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_DESAFIO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_EMPOLGACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_IDEAL,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_CONSERVAÇÃO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_AUTOCRESCIMENTO,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsEncantaClientes(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_CAUTELA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_RESPEITO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_BOM_HUMOR,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_EXTROVERTIDO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_ALTRUISMO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_COOPERACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_MODESTIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_SIMPATIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_CONFIANCA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.FAIXA_EMOCIONAL_AUTOCONSCIENCIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_HARMONIA,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_IDEAL,
                        characteristic,
                        requestDTO
                )
        );
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.VALORES_ABERTURA_MUDANCA,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsTransformacao(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ABERTURA_DESEJO_AVENTURA,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_CAUTELA,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_ALTRUISMO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_COOPERACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_CONFIANCA,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_DESAFIO,
                        characteristic,
                        requestDTO
                )
        );
        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsResiliente(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_AUTO_EFICIENCIA,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_NIVEL_ATIVIDADE,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_COOPERACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_CONFIANCA,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_EMPOLGACAO,
                        characteristic,
                        requestDTO
                )
        );

        return metrics;
    }

    private List<ApplicantCharacteristicMetricsEntity> buildMetricsColaborativo(ApplicantCharacteristicEntity characteristic, ApplicantRequestDTO requestDTO) {

        final List<ApplicantCharacteristicMetricsEntity> metrics = new ArrayList<ApplicantCharacteristicMetricsEntity>();
        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.ESCRUPULOSIDADE_ESFORCO_REALIZACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.EXTROVERSAO_ASSERTIVIDADE,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_ALTRUISMO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_COOPERACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_DETERMINACAO,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.AMABILIDADE_SIMPATIA,
                        characteristic,
                        requestDTO
                )
        );

        metrics.add(
                getMetric(
                        PersonalityInsightsEnum.NECESSIDADE_EMPOLGACAO,
                        characteristic,
                        requestDTO
                )
        );

        return metrics;
    }

    private ApplicantCharacteristicMetricsEntity getMetric(PersonalityInsightsEnum personalityInsights,
                                                           ApplicantCharacteristicEntity characteristic,
                                                           ApplicantRequestDTO requestDTO) {

        final ApplicantCharacteristicMetricsEntity metric = ApplicantCharacteristicMetricsEntity.builder()
                .characteristic(characteristic)
                .name(personalityInsights.getItem())
                .percent(getRequestPercent(personalityInsights, requestDTO).multiply(new BigDecimal(100)))
                .build();
        return metric;
    }

    private BigDecimal getRequestPercent(PersonalityInsightsEnum personalityInsights, ApplicantRequestDTO requestDTO) {

        for (ApplicantMetricsPersonalityRequestDTO dto : requestDTO.getMetrics().getPersonality()) {


            //Bateu tópico ?
            if (dto.getName().toUpperCase().equals(personalityInsights.getTopic().toUpperCase())) {

                for (ApplicantMetricsPersonalityRequestDTO childreDTO : dto.getChildren()) {

                    //Bateu item do tópico ?
                    if (childreDTO.getName().toUpperCase().equals(personalityInsights.getItem().toUpperCase()))
                        return new BigDecimal(childreDTO.getPercentile().toString());
                }
            }
        }


        for (ApplicantMetricsValuesRequestDTO dto : requestDTO.getMetrics().getValues()) {
            //Bateu tópico ?
            if (dto.getName().toUpperCase().equals(personalityInsights.getItem().toUpperCase())) {
                return new BigDecimal(dto.getPercentile().toString());
            }
        }


        for (ApplicantMetricsNeedsRequestDTO dto : requestDTO.getMetrics().getNeeds()) {
            //Bateu tópico ?
            if (dto.getName().toUpperCase().equals(personalityInsights.getItem().toUpperCase())) {
                return new BigDecimal(dto.getPercentile().toString());
            }
        }

        for (ApplicantMetricsConsPrefRequestADTO dto : requestDTO.getMetrics().getConsumptionPreferences()) {
            //Bateu tópico ?
            if (dto.getName().toUpperCase().equals(personalityInsights.getTopic().toUpperCase())) {

                for (ApplicantMetricsConsPrefRequestBDTO childreDTO : dto.getConsumptionPreferences()) {

                    //Bateu item do tópico ?
                    if (childreDTO.getName().toUpperCase().equals(personalityInsights.getItem().toUpperCase()))
                        return new BigDecimal(childreDTO.getScore().toString());
                }
            }
        }


        return new BigDecimal(0);
    }

}