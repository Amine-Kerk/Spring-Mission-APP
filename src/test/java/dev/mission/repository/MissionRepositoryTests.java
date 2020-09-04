package dev.mission.repository;



import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;



import dev.mission.entite.Mission;



@DataJpaTest
class MissionRepositoryTests {
	private static final Logger LOG = LoggerFactory.getLogger(MissionRepositoryTests.class);
	private Mission mission;
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private MissionRepository missionRepository;

	
	@BeforeEach
	public void init() {
		Mission mission = new Mission();
		mission.setDateDebut(LocalDate.parse("2030-01-01"));
		mission.setDateFin(LocalDate.parse("2031-01-01"));
		mission.setLibelle("tartelette");
		mission.setTauxJournalier(new BigDecimal("112.12"));

		Mission mission1 = new Mission();
		mission1.setDateDebut(LocalDate.parse("2019-01-01"));
		mission1.setDateFin(LocalDate.parse("2031-01-01"));
		mission1.setLibelle("tartelette");
		mission1.setTauxJournalier(new BigDecimal("112.12"));

		Mission mission2 = new Mission();
		mission2.setDateDebut(LocalDate.parse("2028-01-01"));
		mission2.setDateFin(LocalDate.parse("2031-01-01"));
		mission2.setLibelle("tartelette");
		mission2.setTauxJournalier(new BigDecimal("112.12"));

		//persist
		List.of(mission, mission1, mission2)
				.forEach(this.entityManager::persist);

		/*
		this.entityManager.persist(mission);
		this.entityManager.persist(mission1);
		this.entityManager.persist(mission1);
		 */
	}
	
	

	
	@Test 
	void findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqualOK(){
		List<Mission> missions = this.missionRepository.findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual(LocalDate.now(), new BigDecimal("100"));

		assertThat(missions.size()).isEqualTo(2);
	}

	@Test
	void findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqualKO(){
		List<Mission> missions = this.missionRepository.findByDateDebutGreaterThanEqualAndTauxJournalierGreaterThanEqual(LocalDate.now(), new BigDecimal("1000"));

		assertThat(missions.size()).isEqualTo(0);
	}

}
