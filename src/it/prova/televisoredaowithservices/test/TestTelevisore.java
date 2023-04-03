package it.prova.televisoredaowithservices.test;

import java.time.LocalDate;
import java.util.List;

import it.prova.televisoredaowithservices.model.Televisore;
import it.prova.televisoredaowithservices.service.MyServiceFactory;
import it.prova.televisoredaowithservices.service.televisore.TelevisoreService;

public class TestTelevisore {

	public static void main(String[] args) {

		// parlo direttamente con il service
		TelevisoreService televisoreService = MyServiceFactory.getTelevisoreServiceImpl();

		try {

			// ora con il service posso fare tutte le invocazioni che mi servono
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//				testInserimentoNuovoTelevisore(televisoreService);
//				System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//				testRimozioneTelevisore(televisoreService);
//				System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//				testFindByExample(televisoreService);
//				System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//				testUpdateTelevisore(televisoreService);
//				System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//				testCercaTelevisorePiuGrande(televisoreService);
//				System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

//				testContaQuantiTelevisoriTraDueDate(televisoreService);
//				System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			testMarcheTelevisoriDegliUltimiSeiMesi(televisoreService);
			System.out.println("In tabella ci sono " + televisoreService.listAll().size() + " elementi.");

			// E TUTTI I TEST VANNO FATTI COSI'

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

//		private static void testInserimentoNuovoTelevisore(TelevisoreService TelevisoreService) throws Exception {
//			System.out.println(".......testInserimentoNuovoTelevisore inizio.............");
//			Televisore newTelevisoreInstance = new Televisore("Samsung", "Marveleous", 50, LocalDate.parse("2020-12-02"));
//			if (TelevisoreService.inserisciNuovo(newTelevisoreInstance) != 1)
//				throw new RuntimeException("testInserimentoNuovoTelevisore FAILED ");
//	
//			System.out.println(".......testInserimentoNuovoTelevisore PASSED.............");
//		}

//		private static void testRimozioneTelevisore(TelevisoreService televisoreService) throws Exception {
//			System.out.println(".......testRimozioneTelevisore inizio.............");
//			// recupero tutti i televisori
//			List<Televisore> interoContenutoTabella = televisoreService.listAll();
//			if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
//				throw new Exception("Non ho nulla da rimuovere");
//	
//			Long idDelPrimo = interoContenutoTabella.get(2).getId();
//			// ricarico per sicurezza con l'id individuato
//			Televisore daRimuovere = televisoreService.findById(idDelPrimo);
//			if (televisoreService.rimuovi(idDelPrimo) != 1)
//				throw new RuntimeException("testRimozioneTelevisore FAILED ");
//	
//			System.out.println(".......testRimozioneTelevisore PASSED.............");
//		}

//		private static void testFindByExample(TelevisoreService televisoreService) throws Exception {
//			System.out.println(".......testFindByExample inizio.............");
//			
//	
//			// preparo un example che ha come marca 'sam' e ricerco
//			List<Televisore> risultatiFindByExample = televisoreService.findByExample(new Televisore("sam"));
//			if (risultatiFindByExample.size() < 1)
//				throw new RuntimeException("testFindByExample FAILED ");
//	
//			System.out.println(risultatiFindByExample);
//			
//			System.out.println(".......testFindByExample PASSED.............");
//		}

//	
//		private static void testUpdateTelevisore(TelevisoreService televisoreService) throws Exception {
//			System.out.println(".......testUpdateTelevisore inizio.............");
//	
//	
//			// recupero col findbyexample e mi aspetto di trovarla
//			List<Televisore> risultatifindByExample = televisoreService.findByExample(new Televisore("sam","5wzp"));
//			if (risultatifindByExample.size()==0)
//				throw new RuntimeException("testUpdateTelevisore: testFindByExample FAILED ");
//	
//			// mi metto da parte l'id su cui lavorare per il test
//			Long idTelevisoreUpdate = risultatifindByExample.get(0).getId();
//	
//			// ricarico per sicurezza con l'id individuato e gli modifico un campo
//			String nuovoModello = "Marveleous";
//			int nuoviPollici=74;
//			Televisore toBeUpdated = televisoreService.findById(idTelevisoreUpdate);
//			toBeUpdated.setModello(nuovoModello);
//			toBeUpdated.setPollici(nuoviPollici);
//			if (televisoreService.aggiorna(toBeUpdated) != 1)
//				throw new RuntimeException("testUpdateTelevisore FAILED ");
//	
//			System.out.println(".......testUpdateTelevisore PASSED.............");
//		}

	private static void testCercaTelevisorePiuGrande(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testUpdateTelevisore inizio.............");
		List<Televisore> listaVociPresenti = televisoreService.listAll();
		if (listaVociPresenti.size() < 1) {
			throw new RuntimeException("Non sono presenti voci nel DB");
		}
		Televisore televisorePiuGrande = televisoreService.cercaTelevisorePiuGrande();
		if (televisorePiuGrande == null) {
			throw new RuntimeException("testUpdateTelevisore: FAILED.");
		}
		System.out.println(televisorePiuGrande);
		System.out.println(".....testUpdateTelevisore PASSED......");

	}

	private static void testContaQuantiTelevisoriTraDueDate(TelevisoreService televisoreService) throws Exception {
		System.out.println(".......testContaQuantiTelevisoriTraDueDate inizio.............");
		List<Televisore> listaVociPresenti = televisoreService.listAll();
		if (listaVociPresenti.size() < 1) {
			throw new RuntimeException("Non sono presenti voci nel DB");
		}

		LocalDate prima = LocalDate.parse("2021-01-01");
		LocalDate seconda = LocalDate.parse("2022-12-31");
		int quantiTelevisoriTraDueDate = televisoreService.quantiTelevisoriProdottiTraDueDate(prima, seconda);
		if (quantiTelevisoriTraDueDate < 0) {
			throw new RuntimeException("testContaQuantiTelevisoriTraDueDate: FAILED.");
		}
		System.out.println(quantiTelevisoriTraDueDate);
		System.out.println(".....testContaQuantiTelevisoriTraDueDate PASSED......");
	}

	public static void testMarcheTelevisoriDegliUltimiSeiMesi(TelevisoreService televisoreService) throws Exception {
		System.out.println(".....testMarcheTelevisoriDegliUltimiSeiMesi inizio......");
		List<Televisore> listaVociPresenti = televisoreService.listAll();
		if (listaVociPresenti.size() < 1) {
			throw new RuntimeException("Non sono presenti voci nel DB");
		}
		List<String> elencoMarcheUltimiSeiMesi = televisoreService.qualiMarcheTelevisoriProdottiNegliUltimiSeiMesi();
		if (elencoMarcheUltimiSeiMesi.size() < 1) {
			throw new RuntimeException("Nessun televisore negli ultimi 6 mesi");
		}
		System.out.println("Gli elementi della lista sono: " + elencoMarcheUltimiSeiMesi.size());
		System.out.println(elencoMarcheUltimiSeiMesi);
		System.out.println(".....testMarcheTelevisoriDegliUltimiSeiMesi PASSED......");
	}

}
