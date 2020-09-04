package dev.mission.exec;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import dev.mission.entite.Mission;
import dev.mission.repository.MissionRepository;

@Controller
@Profile("listerParTJM")
public class ListerProchainesMissionsParTJM implements Runnable  {

	private static final Logger LOG = LoggerFactory.getLogger(ListerProchainesMissionsParTJM.class);
	private MissionRepository missionRepository;
	
	
	
	public ListerProchainesMissionsParTJM(MissionRepository missionRepository) {
		super();
		this.missionRepository = missionRepository;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Mission> listMissionsTJM = missionRepository.listMissionsTJM(BigDecimal.valueOf(200));
		for (Mission mission : listMissionsTJM){
			LOG.info("id={} libelle={} dateDebut={} dateFin={} tauxJournalier={}", mission.getId(),
					mission.getLibelle(), mission.getDateDebut(), mission.getDateFin(), mission.getTauxJournalier());
		}
	}

}
