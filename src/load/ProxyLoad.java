/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package load;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author klots
 */
public class ProxyLoad implements Runnable{
    private String ip;
    private int port;
    Load parent = null;
    private URL url = null;
    Proxy proxy;
    HttpURLConnection uc;
    Document doc = null;

    public ProxyLoad(String url,String ip,int port,  Load p)
    {
        try {
        this.url = new URL(url);// адреса сторінки, яку потрібно відкрити
        } catch (Exception  e) { return;} 
        this.ip = ip;
        this.port = port;
        parent = p;
    }
    @Override
    public void run() {
        long x = System.currentTimeMillis();
        try{
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        uc = (HttpURLConnection)this.url.openConnection(proxy);
        uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20120101 Firefox/29.0");
        uc.setConnectTimeout(20000);
        uc.setReadTimeout(20000);
        uc.setRequestMethod("GET");
        uc.connect();
        doc = Jsoup.parse(uc.getInputStream(),"UTF-8","");
        System.out.println("Після парсингу" + (System.currentTimeMillis()-x));
        parent.setDoc(doc);
        uc.disconnect();
        uc = null;
        }
        catch (Exception e) 
           {System.out.println("Eror in proxy "+ip+"   "+e);
               kill();
           } 
        
    }
    public void kill()
    {uc.disconnect();
        uc = null;
        doc = null;
    }
    
}
