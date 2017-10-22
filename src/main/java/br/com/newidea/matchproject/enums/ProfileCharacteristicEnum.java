package br.com.newidea.matchproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fabio
 * @since 22/10/17 03:40
 */
@AllArgsConstructor
public enum ProfileCharacteristicEnum {

    COLABORATIVO("Colaborativo"),
    RESILIENTE("Resiliente"),
    TRANSFORMACAO("Transaformação"),


    ENCANTA_CLIENTES("Encanta Clientes"),
    PROTEGE_NOSSO_FUTURO("Protege nosso Futuro"),
    VALORIZA_AS_PESSOAS("Valoriza as Pessoas"),
    COLABORA_PARA_VENCER("Colabora para Vencer"),
    INOVA_PARA_CRESCER("Inova para Crescer"),


    INCONFORMISMO("Inconformismo"),
    INOVACAO("Inovação"),
    VELOCIDADE("Velocidade"),


    AGILIDADE("Agilidade"),
    SIMPLICIDADE("Simplicidade"),;

    @Getter
    private String name;
}
