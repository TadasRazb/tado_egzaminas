package tado_egz;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "anketa")
public class Anketa {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer anketosData;
	private Integer paslaugosVertinimas;
	private Integer imonesVertinimas;
	
	@OneToMany
	@JoinColumn(name = "idAnketos", referencedColumnName = "id",insertable=false, updatable=false) 
	private List<Klientai_apklausa> klientai_apklausa;
		
	public Anketa() {
			
	}
	
	public Anketa(Integer id, Integer anketos_data, Integer paslaugos_vertinimas, Integer imones_vertinimas) {
		
		this.id = id;
		this.anketosData = anketos_data;
		this.paslaugosVertinimas = paslaugos_vertinimas;
		this.imonesVertinimas = imones_vertinimas;	
	}
	
	public Anketa(String id, String anketos_data, String paslaugos_vertinimas, String imones_vertinimas) {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnketosData() {
		return anketosData;
	}

	public void setAnketosData(Integer anketosData) {
		this.anketosData = anketosData;
	}

	public Integer getPaslaugosVertinimas() {
		return paslaugosVertinimas;
	}

	public void setPaslaugosVertinimas(Integer paslaugosVertinimas) {
		this.paslaugosVertinimas = paslaugosVertinimas;
	}

	public Integer getImonesVertinimas() {
		return imonesVertinimas;
	}

	public void setImonesVertinimas(Integer imonesVertinimas) {
		this.imonesVertinimas = imonesVertinimas;
	}

	public List<Klientai_apklausa> getKlientai_apklausa() {
		return klientai_apklausa;
	}

	public void setKlientai_apklausa(List<Klientai_apklausa> klientai_apklausa) {
		this.klientai_apklausa = klientai_apklausa;
	}

	@Override
	public String toString() {
		return "Anketa [id=" + id + ", anketosData=" + anketosData + ", paslaugosVertinimas=" + paslaugosVertinimas
				+ ", imonesVertinimas=" + imonesVertinimas + "]";
	}
	
	
}
