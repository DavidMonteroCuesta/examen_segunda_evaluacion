package es.etg.psp.dmc.segtri.exmn.client;

import java.net.Socket;
import java.util.Scanner;

import es.etg.psp.dmc.segtri.exmn.util.DataTransferTCP;
import es.etg.psp.dmc.segtri.exmn.util.PortData;

public class Cliente implements DataTransferTCP, PortData{

    public static void main(String[] args) throws Exception{
        try (Socket cliente = new Socket(LOCALHOST, PORT);
        Scanner sc = new Scanner(System.in)){
            while (true) { 
                System.out.print("> ");
                DataTransferTCP.send(cliente, sc.nextInt());
                System.out.print("> ");
                DataTransferTCP.send(cliente, sc.next());
                System.out.print("> ");
                DataTransferTCP.send(cliente, sc.nextInt());

                System.out.println(DataTransferTCP.receiveStr(cliente));
            }
        }
    }
}
