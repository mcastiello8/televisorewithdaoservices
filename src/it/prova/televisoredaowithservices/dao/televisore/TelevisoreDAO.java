package it.prova.televisoredaowithservices.dao.televisore;

import java.time.LocalDate;
import java.util.List;

import it.prova.televisoredaowithservices.dao.IBaseDAO;
import it.prova.televisoredaowithservices.model.Televisore;

public interface TelevisoreDAO extends IBaseDAO<Televisore> {
	
	public Televisore findTelevisorePiuGrande() throws Exception;
	
	public int findHowManyTelevisoriProdottiTraDueDate(LocalDate primaData, LocalDate secondaData) throws Exception;

	public List<String> whichMarcheTelevisoriProdottiNegliUltimiSeiMesi() throws Exception;
	
}
