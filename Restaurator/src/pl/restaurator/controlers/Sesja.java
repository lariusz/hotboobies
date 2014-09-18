package pl.restaurator.controlers;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.restaurator.dao.UzytkownikDao;
import pl.restaurator.dto.Uzytkownik;

@ManagedBean
@SessionScoped
public class Sesja implements Serializable  {

	private static final long serialVersionUID = 1L;

	private Uzytkownik zalogowany = new Uzytkownik();

	public Uzytkownik getZalogowany() {
		return zalogowany;
	}

	public void setZalogowany(Uzytkownik zalogowany) {
		this.zalogowany = zalogowany;
	}

}
