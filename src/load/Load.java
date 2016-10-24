/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package load;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

/**
 *
 * @author klots
 */
public class Load{
    Document doc = null;
    ProxyLoad proxyloads =null;
    Proxys proxys = null;
    public Load(int count)
    {
    }

    public Load()
    {
    }    
    
    public Document loading(String url)
    {   doc=null;
        long x = System.currentTimeMillis();
        int iteration=1;
        //додати вибір проксі з бази
       do{         
            proxys = ProxyList.locProxy(iteration);
            proxyloads = new ProxyLoad(url,proxys.ip,proxys.port, this);
            proxyloads.run();
       if (doc==null)
            {
               System.out.println("New proxy group - "+ iteration++ + " time - "+
               (System.currentTimeMillis()-x)/1000  + "c" );
                ProxyList.unlocProxy(proxys, false);}
            else ProxyList.unlocProxy(proxys, true);
       } while (doc==null);
        System.out.println("Load time - "+(System.currentTimeMillis()-x) + "ms");
        return doc;
    }
    
    synchronized  void  setDoc(Document d)
    {
        doc = d;
    }
    
        public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

             String https_url = "https://makler.md/ru/real-estate/real-estate-for-sale/apartments-for-sale/an/865849";
             
//        try {
//            System.out.println( Jsoup.connect(https_url).validateTLSCertificates(false).get());
//        } catch (IOException ex) {
//            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
            
      URL url;
      
      try {

	     url = new URL(https_url);
	     HttpURLConnection con = ((HttpURLConnection)url.openConnection());

             System.out.println("****** Content of the URL ********");
	   BufferedReader br =
		new BufferedReader(
			new InputStreamReader(con.getInputStream()));

	   String input;

	   while ((input = br.readLine()) != null){
	      System.out.println(input);
	   }
	   br.close();

     

        } catch (MalformedURLException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }


        

//        Load l = new Load();
//        System.out.println(l.loading("http://realt.by/brest-region/sale/flats/pruzhany/jantarnaja-ul/"));
//            System.out.println("---------");
//        System.out.println(l.loading("http://realt.by/brest-region/sale/flats/pruzhany/jantarnaja-ul/").baseUri());
//        System.out.println("---------");
//        System.out.println(l.loading("http://realt.by/brest-region/sale/flats/pruzhany/jantarnaja-ul/").baseUri());
//        System.out.println("---------");
//        System.out.println(l.loading("http://realt.by/brest-region/sale/flats/pruzhany/jantarnaja-ul/").baseUri());
//        System.out.println("---------");
//        System.out.println(l.loading("http://realt.by/brest-region/sale/flats/pruzhany/jantarnaja-ul/").baseUri());

        
    }
    
}
