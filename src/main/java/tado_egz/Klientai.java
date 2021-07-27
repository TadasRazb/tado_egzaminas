package tado_egz;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Klientai {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;	
    private String vardas;	
    private String pavarde;
    private Integer gimimoData;
    private Integer telefonoNumeris;	
  /*
    @OneToMany
	@JoinColumn(name = "idKliento", referencedColumnName = "id",insertable=false, updatable=false) 
	private List<KlientaiAnketa> klientaiApklausa;
	*/
    
    @Transient
	private ArrayList<String> errors;   
    
	public Klientai() {
		
	}
	
	public Klientai(Integer id, String vardas, String pavarde, Integer gimimo_data, Integer telefono_numeris) {
		
		this.id = id;
		this.vardas = vardas;
		this.pavarde = pavarde;
		this.gimimoData = gimimo_data;	
		this.telefonoNumeris = telefono_numeris;
	}
	
	public Klientai(String id, String vardas, String pavarde, String gimimo_data, String telefono_numeris) {
		
		this.errors = new ArrayList<String>();
		
		try {
				this.id = null;
			
				if ( id != null ) {
		
					this.id = Integer.parseInt ( id );
					
					if ( this.id == 0 ) {
						
						this.id = null;
					}
				}
				this.vardas = vardas;
				this.pavarde = pavarde;		
				this.gimimoData = Integer.parseInt ( gimimo_data );
				this.telefonoNumeris = Integer.parseInt (telefono_numeris );
		
		} catch ( Exception e ) {
			
			this.errors.add( e.getMessage() );
		}
		this.vardas = vardas;
		this.pavarde = pavarde;
		this.gimimoData = gimimoData;
		this.telefonoNumeris = telefonoNumeris;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVardas() {
		return vardas;
	}

	public void setVardas(String vardas) {
		this.vardas = vardas;
	}

	public String getPavarde() {
		return pavarde;
	}

	public void setPavarde(String pavarde) {
		this.pavarde = pavarde;
	}

	public Integer getGimimoData() {
		return gimimoData;
	}

	public void setGimimoData(Integer gimimoData) {
		this.gimimoData = gimimoData;
	}

	public Integer getTelefonoNumeris() {
		return telefonoNumeris;
	}

	public void setTelefonoNumeris(Integer telefonoNumeris) {
		this.telefonoNumeris = telefonoNumeris;
	}
/*
	public List<KlientaiAnketa> getKlientaiApklausa() {
		return klientaiApklausa;
	}

	public void setKlientai_apklausa(List<KlientaiAnketa> klientaiApklausa) {
		this.klientaiApklausa = klientaiApklausa;
	}
*/
	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}   
	
	@Override
	public String toString() {
		return "Klientai [id=" + id + ", vardas=" + vardas + ", pavarde=" + pavarde + ", gimimoData=" + gimimoData
				+ ", telefonoNumeris=" + telefonoNumeris + "]";
	}
}
