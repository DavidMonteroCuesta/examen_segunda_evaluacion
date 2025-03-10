package es.etg.psp.dmc.segtri.exmn.server;

import java.net.Socket;

import es.etg.psp.dmc.segtri.exmn.util.DataTransferTCP;
import es.etg.psp.dmc.segtri.exmn.util.Log;

public class Operador implements Runnable, DataTransferTCP, Log{
    private static final String CERO_PARTIDO_CERO = "0/0";
    private static final String FALLO_AL_INTRODUCIR_LOS_DATOS = "FALLO AL INTRODUCIR LOS DATOS";
    private static final String SALTO_DE_LINEA = "\n";
    private static final String OPERACIONES_COMPLEJAS = " OPERACIONES COMPLEJAS -> ";
    private static final String OPERACIONES_SENCILLAS = " OPERACIONES SENCILLAS -> ";
    private static final String RESULTADO = "RESULTADO -> ";
    private static final String OPERACION_DE = "OPERACIÓN -> ";
    private static final String CON_RESULTADO = " CON RESULTADO -> ";
    private static final String DIVI = "/";
    private static final String MULTI = "*";
    private static final String RESTA = "-";
    private static final String SUMA = "+";
    private static final String SERVER = "SERVER: ";
    private static final String FALLO = "DEBES INTRODUCIR LOS DATOS SEGÚN MARQUE EL PROTOCOLO, QUIZÁ LOS DATOS INTRODUCIDOS NO SEAN DEL TODO VÁLIDOS";

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

                if (comprobarComando(operador) && !(primerComando + operador + segundoComando).equals(CERO_PARTIDO_CERO)){
                    int resultado = calcular(primerComando, segundoComando, operador);
                    DataTransferTCP.send(cliente, SERVER + SALTO_DE_LINEA + RESULTADO + Integer.toString(resultado) + SALTO_DE_LINEA + OPERACIONES_SENCILLAS + Integer.toString(getContadorSencilla()) + SALTO_DE_LINEA + OPERACIONES_COMPLEJAS + Integer.toString(getContadorCompleja()));
                } else {
                    DataTransferTCP.send(cliente, SERVER + FALLO);
                    Log.log(FALLO_AL_INTRODUCIR_LOS_DATOS, this.cliente.getInetAddress().getHostName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
    }

    private int calcular (int primero, int segundo, String comando) throws Exception{
        int respuesta = 0;
        switch (comando) {
            case SUMA -> {
                respuesta = primero + segundo;
                setContadorSencilla();
                Log.log(OPERACION_DE + SUMA + CON_RESULTADO + respuesta, this.cliente.getInetAddress().getHostName());}
            case RESTA -> {
                respuesta = primero - segundo;
                setContadorSencilla();
                Log.log(OPERACION_DE + RESTA + CON_RESULTADO + respuesta, this.cliente.getInetAddress().getHostName());}
            case MULTI -> {
                respuesta = primero * segundo;
                setContadorCompleja();
                Log.log(OPERACION_DE + MULTI + CON_RESULTADO + respuesta, this.cliente.getInetAddress().getHostName());}
            case DIVI -> {
                respuesta = primero / segundo;
                setContadorCompleja();
                Log.log(OPERACION_DE + DIVI + CON_RESULTADO + respuesta, this.cliente.getInetAddress().getHostName());}
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
