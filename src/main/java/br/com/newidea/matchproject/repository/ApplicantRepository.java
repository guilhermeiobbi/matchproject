package br.com.newidea.matchproject.repository;

import br.com.newidea.matchproject.domain.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fabio
 * @since 22/10/17 01:22
 */
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {
}
