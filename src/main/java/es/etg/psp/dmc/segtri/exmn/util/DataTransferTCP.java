package es.etg.psp.dmc.segtri.exmn.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public interface DataTransferTCP {
    static String receiveStr (Socket cliente) throws IOException{
        DataInputStream input = new DataInputStream(cliente.getInputStream());
        return input.readUTF();
    }

    static int receiveInt (Socket cliente) throws IOException{
        DataInputStream input = new DataInputStream(cliente.getInputStream());
        return input.readInt();
    }

    static void send (Socket cliente, int msg) throws IOException{
        DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
        output.writeInt(msg);
    }

    static void send (Socket cliente, String msg) throws IOException{
        DataOutputStream output = new DataOutputStream(cliente.getOutputStream());
        output.writeUTF(msg);
    }
}
