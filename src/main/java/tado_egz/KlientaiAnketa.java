package tado_egz;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class KlientaiAnketa {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;	
    private Integer idKliento;
    private Integer idAnketos;	
    private String vardas;
    private String pavarde;
    
    @ManyToOne
    @JoinColumn(name="id_anketos", insertable=false, updatable=false)
    private Anketa anketa;
    
    
	@Transient
	private ArrayList<String> errors;    
    
	public KlientaiAnketa() {
		super();
	}  
	
	public KlientaiAnketa(Integer id, Integer id_kliento, Integer id_anketos, String vardas, String pavarde) {
		
		super();
		this.id = id;
		this.idKliento = id_kliento;
		this.idAnketos = id_anketos;
		this.vardas = vardas;
		this.pavarde = pavarde;
	}
	
	public KlientaiAnketa(String id, String id_kliento, String id_anketos, String vardas, String pavarde) {
		
		super();
		this.errors = new ArrayList<String>();
		
		try {
				this.id = null;
			
				if ( id != null ) {
		
					this.id = Integer.parseInt ( id );
					
					if ( this.id == 0 ) {
						
						this.id = null;
					}
				}
				this.idKliento = Integer.parseInt( id_kliento );
				this.idAnketos = Integer.parseInt( id_anketos );				
				this.vardas = vardas;		
				this.pavarde = pavarde;
		
		} catch ( Exception e ) {
			
			this.errors.add( e.getMessage() );
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_kliento() {
		return idKliento;
	}

	public void setId_kliento(Integer id_kliento) {
		this.idKliento = id_kliento;
	}

	public Integer getId_anketos() {
		return idAnketos;
	}

	public void setId_anketos(Integer id_anketos) {
		this.idAnketos = id_anketos;
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

	public Anketa getAnketa() {
		return anketa;
	}

	public void setAnketa(Anketa anketa) {
		this.anketa = anketa;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "KlientaiAnketa [id=" + id + ", id_kliento=" + idKliento + ", id_anketos=" + idAnketos + ", vardas="
				+ vardas + ", pavarde=" + pavarde + ", anketa=" + anketa + ", errors=" + errors + "]";
	}
	
	
}
