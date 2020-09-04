package dev.mission.exec;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

@Controller
@Profile("listerNextMissions")
public class ListerProchainesMissions implements Runnable {
	// on créee une instance static de la classe logger pour afficher les infos via
	// log slf4j
	private static final Logger LOG = LoggerFactory.getLogger(ListerProchainesMissions.class);

	// instance de interface afin de pouvoir appeler les methodes
	private MissionRepository missionRepository;

	public ListerProchainesMissions(MissionRepository missionRepository) {
		super();
		this.missionRepository = missionRepository;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Mission> listMissionsAujourdhuiOuUlterieure = missionRepository.listMissionsAujourdhuiOuUlterieure();

		for (Mission mission : listMissionsAujourdhuiOuUlterieure) {

			LOG.info("id={} libelle={} dateDebut={} dateFin={} tauxJournalier={}", mission.getId(),
					mission.getLibelle(), mission.getDateDebut(), mission.getDateFin(), mission.getTauxJournalier());
		}
	}

}
