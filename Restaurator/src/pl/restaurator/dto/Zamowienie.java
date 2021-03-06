package pl.restaurator.dto;

import java.util.Date;
import java.util.List;

public class Zamowienie {
	
	private int idZamowienia;
	private String status;
	private int idStatus;
	private Date dataPrzyjecia;
	private Integer nrStolika;
	private int idKucharza;
	private int idKelnera;
	private String uwaga;
	private boolean anulowane;
	private String przyczynaAnulowania;
	private Date dataAnulowania;
	
	private List<Produkt> produkty;
	
	public Zamowienie() {
		super();
	}

	public Zamowienie(int idZamowienia, Integer nrStolika,Date dataPrzyjecia,
			 Date dataAnulowania, String przyczynaAnulowania) {
		this.idZamowienia = idZamowienia;
		this.nrStolika = nrStolika;
		this.dataPrzyjecia = dataPrzyjecia;
		this.dataAnulowania = dataAnulowania;
		this.przyczynaAnulowania = przyczynaAnulowania;
		
	}
	
	public Zamowienie(int idZamowienia, int idStatus, Date dataPrzyjecia,
			Integer nrStolika, int idKelnera, int idKucharza) {
		this.idZamowienia = idZamowienia;
		this.idStatus = idStatus;
		this.dataPrzyjecia = dataPrzyjecia;
		this.nrStolika = nrStolika;
		this.idKelnera = idKelnera;
		this.idKucharza = idKucharza;

	}
	
	public int getIdZamowienia() {
		return idZamowienia;
	}

	public void setIdZamowienia(int idZamowienia) {
		this.idZamowienia = idZamowienia;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDataPrzyjecia() {	
		return dataPrzyjecia;
	}

	public void setDataPrzyjecia(Date dataPrzyjecia) {
		this.dataPrzyjecia = dataPrzyjecia;
	}

	public Integer getNrStolika() {
		return nrStolika;
	}

	public void setNrStolika(Integer nrStolika) {
		this.nrStolika = nrStolika;
	}

	public int getIdKucharza() {
		return idKucharza;
	}

	public void setIdKucharza(int idKucharza) {
		this.idKucharza = idKucharza;
	}

	public int getIdKelnera() {
		return idKelnera;
	}

	public void setIdKelnera(int idKelnera) {
		this.idKelnera = idKelnera;
	}

	public List<Produkt> getProdukty() {
		return produkty;
	}

	public void setProdukty(List<Produkt> produkty) {
		this.produkty = produkty;
	}

	public String getUwaga() {
		return uwaga;
	}

	public void setUwaga(String uwaga) {
		this.uwaga = uwaga;
	}

	public boolean isAnulowane() {
		return anulowane;
	}

	public void setAnulowane(boolean anulowane) {
		this.anulowane = anulowane;
	}

	public String getPrzyczynaAnulowania() {
		return przyczynaAnulowania;
	}

	public void setPrzyczynaAnulowania(String przyczynaAnulowania) {
		this.przyczynaAnulowania = przyczynaAnulowania;
	}

	public Date getDataAnulowania() {
		return dataAnulowania;
	}

	public void setDataAnulowania(Date dataAnulowania) {
		this.dataAnulowania = dataAnulowania;
	}


	
}
