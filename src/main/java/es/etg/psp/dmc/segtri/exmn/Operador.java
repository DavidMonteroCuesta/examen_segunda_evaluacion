package es.etg.psp.dmc.segtri.exmn;

import java.net.Socket;

import es.etg.psp.dmc.segtri.exmn.util.DataTransferTCP;

public class Operador implements Runnable, DataTransferTCP{
    private static final String DIVI = "/";
    private static final String MULTI = "*";
    private static final String RESTA = "-";
    private static final String SUMA = "+";
    private static final String SERVER = "SERVER: ";
    private static final String FALLO = "DEBES INTRODUCIR LOS DATOS SEGÚN MARQUE EL PROTOCOLO";
    
    private Socket cliente;

    private int contadorSencilla;
    private int contadorCompleja;
    public Operador(Socket cliente) {
        this.cliente = cliente;
        contadorCompleja = 0;
        contadorSencilla = 0;
    }

    @Override
    public void run() {
        try {
            while (true) { 
                int primerComando = DataTransferTCP.receiveInt(cliente);
                String operador = DataTransferTCP.receiveStr(cliente);
                int segundoComando = DataTransferTCP.receiveInt(cliente);

                if (comprobarComando(operador)){
                    int resultado = calcular(primerComando, segundoComando, operador);
                    DataTransferTCP.send(cliente, SERVER + Integer.toString(resultado) + " " + Integer.toString(getContadorSencilla()) + " " + Integer.toString(getContadorCompleja()));
                } else DataTransferTCP.send(cliente, SERVER + FALLO);
            }
        } catch (Exception e) {
            
        }        
    }

    private int calcular (int primero, int segundo, String comando){
        int respuesta = 0;
        switch (comando) {
            case SUMA -> {
                respuesta = primero + segundo;
                setContadorSencilla();}
            case RESTA -> {
                respuesta = primero - segundo;
                setContadorSencilla();}
            case MULTI -> {
                respuesta = primero * segundo;
                setContadorCompleja();}
            case DIVI -> {
                respuesta = primero / segundo;
                setContadorCompleja();}
            default -> {}
        }

        return respuesta;
    }

    private Boolean comprobarComando(String comando){
        Boolean valido = false;
        if (comando.equals(SUMA)) valido = true;
        if (comando.equals(RESTA)) valido = true;
        if (comando.equals(MULTI)) valido = true;
        if (comando.equals(DIVI)) valido = true;

        return valido;
    }

    public Socket getCliente() {
        return cliente;
    }
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
    public int getContadorSencilla() {
        return contadorSencilla;
    }
    public void setContadorSencilla() {
        this.contadorSencilla++;
    }
    public int getContadorCompleja() {
        return contadorCompleja;
    }
    public void setContadorCompleja() {
        this.contadorCompleja++;
    }
}
