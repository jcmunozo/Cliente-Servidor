package agenciaviajes.acceso;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahurtado, wpantoja, rzambran
 */
public class ServicioRegistraduriaSocket implements IRegistraduria {

    private Socket socket = null;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private final String IP_SERVIDOR = "localhost";
    private final int PUERTO = 5000;
    
    @Override
    public String obtenerClientesDeLaBDRegistraduria()
    {
        String respuesta = null;
        String accion = "obtenerClientes";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServicioRegistraduriaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;    
    }
    
    @Override
    public String obtenerPlanesDeLaBDRegistradutia()
    {
        String respuesta = null;
        String accion = "obtenerPlanes";
        try
        {
            conectar(IP_SERVIDOR, PUERTO);
            respuesta = leerFlujoEntradaSalida(accion);
            cerrarFlujos();
            desconectar();
        }
        catch (IOException ex) {
            Logger.getLogger(ServicioRegistraduriaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;  
    }

    private String leerFlujoEntradaSalida(String accion) throws IOException {
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        // Usando el protocolo de comunicación
        salidaDecorada.println(accion);
        if (entradaDecorada.hasNextLine()) {
            respuesta = entradaDecorada.nextLine();
        }
        return respuesta;
    }

    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }

    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioRegistraduriaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }

}
