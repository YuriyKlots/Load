/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package load;

/**
 *
 * @author user
 */
public class Proxys {
    String ip;
    int port;
    int good;
    int bad;

    public Proxys(String ip, int port, int good, int bad) {
        this.ip = ip;
        this.port = port;
        this.good = good;
        this.bad = bad;
    }
    
    public Proxys(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.good = 0;
        this.bad = 0;
    }
}
