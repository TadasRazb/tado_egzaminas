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
	private AnketaRepository KlientaiApklausa_repository;
	
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
