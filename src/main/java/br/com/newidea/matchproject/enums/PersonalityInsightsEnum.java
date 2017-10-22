package br.com.newidea.matchproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fabio
 * @since 22/10/17 01:58
 */
@AllArgsConstructor
public enum PersonalityInsightsEnum {

    ABERTURA_DESEJO_AVENTURA("Abertura", "Desejo de aventura"),
    ABERTURA_INTELECTO("Abertura", "Intelecto"),
    ABERTURA_IMAGINAÇÃO("Abertura", "Imaginação"),
    ABERTURA_DESAFIO_A_AUTORIDADE("Abertura", "Desafio à autoridade"),
    //

    AMABILIDADE_ALTRUISMO("Amabilidade", "Altruísmo"),
    AMABILIDADE_COOPERACAO("Amabilidade", "Cooperação"),
    AMABILIDADE_CONFIANCA("Amabilidade", "Confiança"),
    AMABILIDADE_DETERMINACAO("Amabilidade", "Determinação"),
    AMABILIDADE_MODESTIA("Amabilidade", "Modéstia"),
    AMABILIDADE_SIMPATIA("Amabilidade", "Simpatia"),
    //


    ESCRUPULOSIDADE_AUTO_DISCIPLINA("Escrupulosidade", "Autodisciplina"),
    ESCRUPULOSIDADE_AUTO_EFICIENCIA("Escrupulosidade", "Autoeficiência"),
    ESCRUPULOSIDADE_CAUTELA("Escrupulosidade", "Cautela"),
    ESCRUPULOSIDADE_ESFORCO_REALIZACAO("Escrupulosidade", "Esforço para realização"),
    ESCRUPULOSIDADE_RESPEITO("Escrupulosidade", "Respeito"),
    ESCRUPULOSIDADE_REGULARIDADE("Escrupulosidade", "Regularidade"),
    //

    EXTROVERSAO_ASSERTIVIDADE("Extroversão", "Assertividade"),
    EXTROVERSAO_BOM_HUMOR("Extroversão", "Bom Humor"),
    EXTROVERSAO_EXTROVERTIDO("Extroversão", "Extrovertido"),
    EXTROVERSAO_NIVEL_ATIVIDADE("Extroversão", "Nível de atividade"),
    //

    FAIXA_EMOCIONAL_AUTOCONSCIENCIA("Faixa Emocional", "Autoconsciência"),
    FAIXA_EMOCIONAL_PROPENSO_A_SE_PREOCUPAR("Faixa Emocional", "Propenso a se preocupar"),
    FAIXA_EMOCIONAL_SUSCETIVEL_AO_STRESS("Faixa Emocional", "Suscetível ao stress"),


    NECESSIDADE_HARMONIA("Necessidade", "Harmonia"),
    NECESSIDADE_EMPOLGACAO("Necessidade", "Empolgação"),
    NECESSIDADE_ESTABILIDADE("Necessidade", "Estabilidade"),
    NECESSIDADE_IDEAL("Necessidade", "Ideal"),
    NECESSIDADE_DESAFIO("Necessidade", "Desafio"),
    NECESSIDADE_RETRAIMENTO("Necessidade", "Retraimento"),
    NECESSIDADE_EXPRESSAO_PROPRIA_PERSONALIDADE("Necessidade", "Expressão da própria personalidade"),
    NECESSIDADE_CURIOSIDADE("Necessidade", "Curiosidade"),


    VALORES_ABERTURA_MUDANCA("", "Abertura à mudança"),
    VALORES_CONSERVAÇÃO("", "Conservação"),

    VALORES_AUTOCRESCIMENTO("", "Autocrescimento"),
    VALORES_AUTOTRANSCEDENCIA("", "Autotranscendência");
    //


    @Getter
    private String topic;

    @Getter
    private String item;
}
