package ajc.formation.projet_factory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajc.formation.projet_factory.dao.IDAOFormateur;
import ajc.formation.projet_factory.dao.IDAOVideoProjecteur;
import ajc.formation.projet_factory.model.VideoProjecteur;

@Service
public class VideoProjecteurService {

    @Autowired
    private IDAOVideoProjecteur daoVideoProjecteur;
    @Autowired
    private IDAOFormateur daoFormateur;

    public List<VideoProjecteur> getAll(){
		return daoVideoProjecteur.findAll();
	}
	
	public VideoProjecteur getById(Integer id) {
		return daoVideoProjecteur.findById(id).orElseThrow();
	}
	
	public VideoProjecteur insert(VideoProjecteur videoVideoProjecteur) {
		return daoVideoProjecteur.save(videoVideoProjecteur);
	}
	
	public VideoProjecteur update(VideoProjecteur videoVideoProjecteur) {
		if(videoVideoProjecteur.getId() == null) {
			throw new RuntimeException("l'id n'existe pas");
		}
		return daoVideoProjecteur.save(videoVideoProjecteur);
	}

    public void delete(VideoProjecteur videoProjecteur){
        daoFormateur.cascadevideoProjecteurNull(videoProjecteur);
        daoVideoProjecteur.delete(videoProjecteur);
    }
}
