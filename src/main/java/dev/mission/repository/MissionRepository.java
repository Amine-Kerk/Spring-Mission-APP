package dev.mission.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.mission.entite.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

	@Query("SELECT m FROM Mission m WHERE m.dateDebut >= CURRENT_DATE ")
	List<Mission> listMissionsAujourdhuiOuUlterieure();
	
	
	@Query("SELECT m FROM Mission m WHERE m.dateDebut >= CURRENT_DATE and m.tauxJournalier > :tauxJournalier ")
	List<Mission> listMissionsTJM(BigDecimal tauxJournalier);
}
