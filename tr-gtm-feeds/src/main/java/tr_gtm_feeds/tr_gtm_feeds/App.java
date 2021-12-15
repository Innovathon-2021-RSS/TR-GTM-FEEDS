package tr_gtm_feeds.tr_gtm_feeds;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import org.apache.commons.lang.StringEscapeUtils;
import org.jdom.input.JDOMParseException;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.ParsingFeedException;
import com.sun.syndication.io.SyndFeedInput;

/** ***/

public class App extends JFrame{	
	
	/** **/
	
	private static Logger logger = Logger.getLogger("App");
	
	private static final long serialVersionUID = 1L;
	
	public static JEditorPane editorPane = new JEditorPane();
	public static JEditorPane editorPane2 = new JEditorPane();
	public static JEditorPane editorPane3 = new JEditorPane();
	public static JEditorPane editorPane4 = new JEditorPane();
	public static JEditorPane editorPane5 = new JEditorPane();
	public static JEditorPane editorPane6 = new JEditorPane();
	
	public static JTabbedPane tab = new JTabbedPane();
	
	public static String texto_completo;
	
	public static boolean v_b_tem_erro = false;
	
	public static void ler(String url) throws MalformedURLException, IOException, IllegalArgumentException, FeedException {
		
		v_b_tem_erro = false;
		texto_completo = "";
		
		if (url.isEmpty() || url == "") {
			
			url = "http://noticias.gov.br/noticias/rss?id=AAAKK";
				
		}		
		
		String urlstring = url;
		InputStream is = new URL(urlstring).openConnection().getInputStream();
		SyndFeedInput input = new SyndFeedInput();
		
		SyndFeed feed = null;
		
		try {
			feed = (SyndFeed) input.build(new InputStreamReader(is, "UTF-8"));			
		}
		catch (Exception ex) {
                       
            logger.info("[TR] - XML Inválido: " + ex.getMessage());
            texto_completo =  "XML Inválido: " + ex.getMessage();
            v_b_tem_erro = true;
        }
		
		Iterator<?> entries = null;
		
		if (v_b_tem_erro == false){
			entries = feed.getEntries().iterator();
		
		
		while (entries.hasNext() && v_b_tem_erro == false) {
			SyndEntry entry = (SyndEntry) entries.next();
			
			if (texto_completo == null || texto_completo.isEmpty()) {
				texto_completo = "";
			}
			
			texto_completo = texto_completo +  "\n" + "————" + "<br />";
			//System.out.println("————");
			texto_completo = texto_completo +  "\n" + "<span style=\"color:orange;font-weight:bold\"> " + entry.getTitle() + "</span><br />";
			//System.out.println("Title: " + entry.getTitle());
		//	texto_completo = texto_completo +  "\n" + "<span style=\"color:orange;font-weight:bold\">Published: </span>" + entry.getPublishedDate() + "<br />";
			
			if (entry.getDescription() != null) {
				texto_completo = texto_completo +  "\n" + "<span style=\"color:gray;font-weight:bold\">" + StringEscapeUtils.unescapeHtml(entry.getDescription().getValue()) + "</span><br />";
				//System.out.println("Description: " + StringEscapeUtils.unescapeHtml(entry.getDescription().getValue()));
			}
			//
			texto_completo = texto_completo +  "\n" + "<span style=\"color:orange\"><a href=\"url\">" + entry.getLink() + "</a></span><br /><br />";
			//
			if (entry.getContents().size() > 0) {
				@SuppressWarnings("unused")
				SyndContent content = (SyndContent) entry.getContents().get(0);
				//System.out.println("Content type=" + content.getType());
				//System.out.println("Content value=" + content.getValue());
			}
		}
		}
	}		
	  	  
	  JScrollPane scrollPane = new JScrollPane(editorPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  JScrollPane scrollPane2 = new JScrollPane(editorPane2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  JScrollPane scrollPane3 = new JScrollPane(editorPane3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  JScrollPane scrollPane4 = new JScrollPane(editorPane4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  JScrollPane scrollPane5 = new JScrollPane(editorPane5, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  JScrollPane scrollPane6 = new JScrollPane(editorPane6, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	  	  	  	  
	 
	  public App(){
		  
	     //Define o título da janela
		  
	     super("GTM Feeds - Suas Notícias");
	     this.montaJanela();
	  } 
	 
	  private void montaJanela(){
		  
		 // texto.setEditable(false);
		   
		 tab.addTab("1", scrollPane);
		 tab.addTab("2", scrollPane2);
		 tab.addTab("3", scrollPane3);
		 tab.addTab("4", scrollPane4);
		 tab.addTab("5", scrollPane5);
		 tab.addTab("6", scrollPane6); 
	           
		 this.getContentPane().add(tab);
	      
	     editorPane.setContentType("text/html");
	     editorPane2.setContentType("text/html");
	     editorPane3.setContentType("text/html");
	     editorPane4.setContentType("text/html");
	     editorPane5.setContentType("text/html");
	     editorPane6.setContentType("text/html");
	      
	   }
	  
	  public static void process() throws MalformedURLException, IllegalArgumentException, IOException, FeedException {
		  
			 Properties props = new Properties();
		     List<String> a = new ArrayList<String>();
		     List<String> b = new ArrayList<String>();
		        
		  		try{	     	
		  			
		  			System.out.println(System.getProperty("user.dir"));
		  			
		  			String caminho = System.getProperty("user.dir") + "\\GTM_Feeds\\url.properties";
		  			
		  			logger.info("[TR] Caminho da onde deveria estar o properties: " + caminho);
		  			
		  			FileInputStream in = new FileInputStream(caminho);	      			
		            props.load(in);	                                              
		            in.close();
		        }
		            catch(Exception e){
		            	logger.info("[TR] Erro na leitura do properties");
		        }
		  		//
		  		// lendo todos os valores do properties quebrados por virgula
	          for (Map.Entry<Object, Object> entry : props.entrySet()) {                	
	          	
	        	if (entry.getKey().equals("urls")) {  
	          	
	        	String[] values = entry.getValue().toString().split("<#>");               	
	          	               	
	          	a = new ArrayList(Arrays.asList(values));                	               	
	        	  
	        	  }
	          }
	          //
	          for (Map.Entry<Object, Object> entry : props.entrySet()) {         
	        	  
	        	    if (entry.getKey().equals("titles")) {
		          	
		          	String[] values = entry.getValue().toString().split("<#>");               	
		          	               	
		          	b = new ArrayList(Arrays.asList(values));                	               	
		          	
		           }
	          }
	          //
	          //
	          int v_contador = 1;
	          for (String vlr : b) {
	        	  if (v_contador==1) {
		          		tab.setSelectedIndex(0);
		          		tab.setTitleAt(0, vlr);
		          		}
		          	else if (v_contador==2) {
		          		
		          		tab.setSelectedIndex(1);
		          		tab.setTitleAt(1, vlr);
		          		}
		          	else if (v_contador==3) {
		          		tab.setSelectedIndex(2);
		          		tab.setTitleAt(2, vlr);
		          		}
		          	else if (v_contador==4) {
		          		tab.setSelectedIndex(3);
		          		tab.setTitleAt(3, vlr);
		          		}
		          	else if (v_contador==5) {
		          		tab.setSelectedIndex(4);
		          		tab.setTitleAt(4, vlr);
		          	}
		          	else if (v_contador==6) {
		          		tab.setSelectedIndex(5);
		          		tab.setTitleAt(5, vlr);
		          		}
	        	  
	        	  v_contador = v_contador + 1;
	          }
	          //
	          v_contador = 1;
	          for (String vlr : a) {
	          	
	          	
	          	ler(vlr);
					          	
	          	if (v_contador==1) {
	          		tab.setSelectedIndex(0);
	          		String texto1 = texto_completo;
	          		editorPane.setText(texto1);
	          		}
	          	else if (v_contador==2) {
	          		
	          		tab.setSelectedIndex(1);	
	          		String texto2 = texto_completo;
	          		editorPane2.setText(texto2);
	          		}
	          	else if (v_contador==3) {
	          		tab.setSelectedIndex(2);
	          		String texto3 = texto_completo;
	          		editorPane3.setText(texto3);
	          		}
	          	else if (v_contador==4) {
	          		tab.setSelectedIndex(3);
	          		String texto4 = texto_completo;
	          		editorPane4.setText(texto4);
	          		}
	          	else if (v_contador==5) {
	          		tab.setSelectedIndex(4);
	          		String texto5 = texto_completo;
	          		editorPane5.setText(texto5);
	          		}
	          	else if (v_contador==6) {
	          		tab.setSelectedIndex(5);
	          		String texto6 = texto_completo;
	          		editorPane6.setText(texto6);
	          		}	          	
	          	
	          	
	          	v_contador = v_contador + 1;
	          	
	          }
	          
	          tab.setSelectedIndex(0);
	          
	          if (a.isEmpty()) {
	        	  ler("");
	        	  editorPane.setText(texto_completo);
	          }
	          
	          
		}
	  
	public static void main(String[] args) throws MalformedURLException, IOException, IllegalArgumentException, FeedException{

	       App janela = new App();
	       janela.setSize(400,500);
	       janela.setVisible(true);
	       janela.setLocationRelativeTo(null);
	       editorPane.setText("Carregando dados...");
	       //
	       process();
	   
		}
	}


