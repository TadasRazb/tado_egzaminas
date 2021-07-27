package tado_egz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PageController {
	
	@Autowired
	private KlientaiRepository klientai_repository;
	
	@Autowired
	private AnketaRepository anketa_repository;
	
	@Autowired
	private KlientaiAnketaRepository klientai_anketa_repository;
	
	@Autowired 
	EntityManagerFactory factory;	
	
	public SessionFactory sessionFactory() {

        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return factory.unwrap(SessionFactory.class);
	}
	
	public String vardas_failo = "data.txt";
	
	/**
	 * 
	 * @param id
	 * @param vardas
	 * @param pavarde
	 * @param gimimo_data
	 * @param telefono_numeris
	 * @param send
	 * @param model
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping(path="/klientai")
	public String klientai(
			
		@RequestParam(name="id", required=false, defaultValue="0") String id,
		@RequestParam(name="vardas", required=false, defaultValue="-") String vardas,
		@RequestParam(name="pavarde", required=false, defaultValue="-") String pavarde,
		@RequestParam(name="gimimo_data", required=false, defaultValue="-") String gimimo_data,
		@RequestParam(name="telefono_numeris", required=false, defaultValue="-") String telefono_numeris,
		@RequestParam(name="send", required=false, defaultValue="-") String send,
		Model model
		) throws IOException {
		
		//String url_tpl = "klientai";
	
		System.out.println(send);
		
		if ( ( send != null ) && send.equals ("siųsti") ) {
			rasomIFailaKlientai(id, vardas, pavarde, gimimo_data, telefono_numeris );
			Klientai klientas = new Klientai ( 
			
					( id.equals("0") ? null : Integer.parseInt (id) )
					, vardas
					, pavarde
					, Integer.parseInt (gimimo_data)
					, Integer.parseInt (telefono_numeris)
					);
			System.out.println(klientas.toString());
			klientas = klientai_repository.save(klientas);
		//	url_tpl = "redirect:klientai?id_anketos=" + klientas.getId();   //? get parametrai ieskomi po klaustuko surasomi parametrai kurie imami is get
		}
		model.addAttribute("klientai", klientai_repository.findAll() );
		model.addAttribute( "lst_menu", Menu.values() );
		
		return "klientai";
	}
	
	/**
	 * 
	 * @param id
	 * @param id_kliento
	 * @param id_anketos
	 * @param kliento_vardas
	 * @param kliento_pavarde
	 * @param saugoti
	 * @param model
	 * @return
	 */
	
	@RequestMapping(path="/klientai-anketa", method={ RequestMethod.GET, RequestMethod.POST })
	public String klientaiAnketa(
			// id	id_gaminio	id_zaliavos	kiekis_zaliavos	kiekis_gaminiu	
			  @RequestParam(name="id", required=false, defaultValue="0") String id
			, @RequestParam(name="id_kliento", required=false, defaultValue="-") String id_kliento	
			, @RequestParam(name="id_anketos", required=false, defaultValue="0") String id_anketos
			, @RequestParam(name="kliento_vardas", required=false, defaultValue="") String kliento_vardas	
			, @RequestParam(name="kliento_pavarde", required=false, defaultValue="2") String kliento_pavarde	
			, @RequestParam(name="saugoti", required=false, defaultValue="nesaugoti") String saugoti
			, Model model
			) {
		
		if ( ( saugoti != null ) && saugoti.equals ("saugoti") ) {
			
			KlientaiAnketa kliento_anketa = new KlientaiAnketa (
					id
					, id_kliento	
					, id_anketos
					, kliento_vardas
					, kliento_pavarde
			);

			klientai_anketa_repository.save( kliento_anketa );
		}
		
		Iterable<KlientaiAnketa> lst_klientai_anketa = klientai_anketa_repository.findAll();
		
		model.addAttribute("klientai_anketa", lst_klientai_anketa );
		
		return "klientai-anketa";
	}	
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	
	@RequestMapping(path="/klientas", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Klientai klientas(@RequestParam(name="id", required=true, defaultValue="0") Integer id, Model model) {
		
		Klientai klientas = new Klientai();
		
		if ( id > 0 ) {
			
			klientas = klientai_repository.findById( id ).get();
		}

		return klientas;
	}
	
	/**
	 * 
	 * @return
	 */
	
	@RequestMapping(path="/anketa", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Iterable<Anketa> anketa(/* @RequestParam(name="id", required=true, defaultValue="0") Integer id, Model model */) {
		
		return anketa_repository.findAll();
	}
	
	
	@RequestMapping(path="/salinti-klienta", method={ RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String salintiZaliava(@RequestParam(name="id", required=true, defaultValue="0") Integer id, Model model) {
		
		String res = "0";
		
		if ( id > 0 ) {
			
			klientai_repository. deleteById(id);
			
			if ( ! klientai_repository.existsById( id ) ) {
			
				res = "1";
			}			
		}
		
		return res;
	}	
	private void rasomIFailaKlientai (
			String id,
			String vardas,
			String pavarde,
			String gimimmo_data,
			String telefono_numeris
		
		) throws IOException { 
		
		System.out.println("irasinejam");
		File data_file = new File("data.txt");
		data_file =  createNewFile();
		FileWriter fw = new FileWriter("data.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(id);
		bw.newLine();
		bw.write(vardas);
		bw.newLine();
		bw.write(pavarde);
		bw.newLine();
		bw.write(gimimmo_data);
		bw.newLine();
		bw.write(telefono_numeris);
		bw.newLine();
		bw.close();
	}
	
	private File createNewFile() {
		// TODO Auto-generated method stub
		return null;
	}
}
