package es.etg.psp.dmc.segtri.exmn;

import java.net.ServerSocket;

import es.etg.psp.dmc.segtri.exmn.util.PortData;

public class Server implements PortData{
    public static void main(String[] args) throws Exception{
        try (ServerSocket sv = new ServerSocket(PORT)){
            while (true) { 
                Thread hilo = new Thread(new Operador(sv.accept()));
                hilo.start();
            }
        }
    }
}