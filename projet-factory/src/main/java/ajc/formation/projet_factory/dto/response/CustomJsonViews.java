package ajc.formation.projet_factory.dto.response;

public class CustomJsonViews {

	public static class Common {

	}

	public static class StagiaireWithFiliere extends Common {

	}

	public static class FiliereWithStagiaire extends Common {

	}

	public static class OrdinateurWithStagiaire extends Common {

	}

	public static class StagiaireWithOrdinateur extends StagiaireWithFiliere {

	}
	
	public static class TechnicienWithOrdinateur extends Common{
		
	}
	
	public static class OrdinateurWithTechnicien extends Common{
		
	}
}
