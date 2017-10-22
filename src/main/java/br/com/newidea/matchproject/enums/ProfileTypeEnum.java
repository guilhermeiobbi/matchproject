package br.com.newidea.matchproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fabio
 * @since 22/10/17 03:41
 */
@AllArgsConstructor
public enum ProfileTypeEnum {

    EMPLOYER_BRANDING("Employer Branding"),
    DIGITAL("Digital"),
    FAST_PLAY("Fast Play"),
    EXPERIAN_WAY("Experian Way");

    @Getter
    private String name;
}
