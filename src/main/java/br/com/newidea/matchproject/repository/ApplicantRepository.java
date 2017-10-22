package br.com.newidea.matchproject.repository;

import br.com.newidea.matchproject.domain.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author fabio
 * @since 22/10/17 01:22
 */
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {

    @Query(value = "select * from applicant_app order by app_percent desc", nativeQuery = true)
    List<ApplicantEntity> findAllOrderByPercentDesc();
}
