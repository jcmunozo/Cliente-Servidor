package registraduria.servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import registraduria.logicanegocio.Cliente;
import registraduria.logicanegocio.GestorBaseDatos;
import registraduria.logicanegocio.Plan;

public class RegistraduriaServicios implements Runnable {

    
    private final GestorBaseDatos myGestor;
    
    private static ServerSocket serverSocket;
    private static Socket socket;
    
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private static final int PUERTO = 5000;
    
    /**
     * Constructor
     */
    public RegistraduriaServicios()
    {
        myGestor = new GestorBaseDatos();
    }
    
     /**
     * Logica completa del servidor
     */
    public void iniciar() {
        abrirPuerto();

        while (true) {
            esperarAlCliente();
            lanzarHilo();
        }
    }
    
     /**
     * Lanza el hilo
     */
    private static void lanzarHilo() {
        new Thread(new RegistraduriaServicios()).start();
    }
    
    private static void abrirPuerto()
    {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Escuchando por el puerto " + PUERTO);
        } catch (IOException ex) {
            Logger.getLogger(RegistraduriaServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Espera que el cliente se conecta y le devuelve un socket
     */
    private static void esperarAlCliente() {
        try {
            socket = serverSocket.accept();
            System.out.println("Cliente conectado");
        } catch (IOException ex) {
            Logger.getLogger(RegistraduriaServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
     * Cuerpo del hilo
     */
    @Override
    public void run() {
        try {
            crearFlujos();
            leerFlujos();
            cerrarFlujos();

        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistraduriaServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Crea los flujos con el socket
     *
     * @throws IOException
     */
    private void crearFlujos() throws IOException {
        salidaDecorada = new PrintStream(socket.getOutputStream());
        entradaDecorada = new Scanner(socket.getInputStream());
    }
    
     /**
     * Lee los flujos del socket
     */
    private void leerFlujos() throws ClassNotFoundException, SQLException {
        if (entradaDecorada.hasNextLine()) {
            // Extrae el flujo que envía el cliente
            String peticion = entradaDecorada.nextLine();
            decodificarPeticion(peticion);

        } else {
            salidaDecorada.flush();
            salidaDecorada.println("NO_ENCONTRADO");
        }
    }
    
     /**
     * Decodifica la petición, extrayeno la acción y los parámetros
     *
     * @param peticion petición completa al estilo
     * "consultarCiudadano,983932814"
     */
    private void decodificarPeticion(String peticion) throws ClassNotFoundException, SQLException {
       
        String accion = peticion;
        procesarAccion(accion);

    }
    
     /**
     * Segun el protocolo decide qué accion invocar
     *
     * @param accion acción a procesar
     * @param parametros parámetros de la acción
     */
    private void procesarAccion(String accion) throws ClassNotFoundException, SQLException {
        switch (accion) {
            case "obtenerClientes":
                ArrayList<Cliente> misClientes = myGestor.obtenerClientes();
                if (misClientes == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    salidaDecorada.println(serializarClientes(misClientes));
                }
                break;
                
            case "obtenerPlanes":
                ArrayList<Plan> misPlanes = myGestor.obtenerPlanes();
                if (misPlanes == null) {
                    salidaDecorada.println("NO_ENCONTRADO");
                } else {
                    salidaDecorada.println(serializarPlanes(misPlanes));
                }
                break;
        }
    }
    
     /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void cerrarFlujos() throws IOException {
        salidaDecorada.close();
        entradaDecorada.close();
        socket.close();
    }
   
    
    public String serializarClientes(ArrayList<Cliente> listaClientes) 
    {
        JsonArray array = new JsonArray();
        JsonObject myObjJson;
        
        for(Cliente cli : listaClientes)
        {
            myObjJson = parseToJSONCliente(cli);
            array.add(myObjJson);
        }
        
        return array.toString();
    }
    
     /**
     * Convierte el objeto Cliente a json
     *
     * @param cliente Objeto Cliente
     * @return Objeto json
     */
    private JsonObject parseToJSONCliente(Cliente cliente)
    {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("id", cliente.getId());
        jsonobj.addProperty("nombres", cliente.getNombres());
        jsonobj.addProperty("apellidos", cliente.getApellidos());
        jsonobj.addProperty("fechaNacimiento", cliente.getFechaNacimiento());
        jsonobj.addProperty("sexo", cliente.getSexo());
        jsonobj.addProperty("email", cliente.getEmail());
        
        return jsonobj;
    }
    
    
    public String serializarPlanes(ArrayList<Plan> listaPlanes) 
    {
        JsonArray array = new JsonArray();
        JsonObject myObjJson;
        
        for(Plan myPlan : listaPlanes)
        {
            myObjJson = parseToJSONPlan(myPlan);
            array.add(myObjJson);
        }
        
        return array.toString();
    }
    
     /**
     * Convierte el objeto Plan a json
     *
     * @param plan Objeto Plan
     * @return Objeto json
     */
    private JsonObject parseToJSONPlan(Plan plan)
    {
        JsonObject jsonobj = new JsonObject();
        jsonobj.addProperty("idPlan", plan.getIdPlan());
        jsonobj.addProperty("nombrePlan", plan.getNombrePlan());
        jsonobj.addProperty("descripcion", plan.getDescripcion());
        jsonobj.addProperty("rangoEdad1", plan.getRangoEdad1());
        jsonobj.addProperty("rangoEdad2", plan.getRangoEdad2());
        jsonobj.addProperty("genero", plan.getGenero());
        
        return jsonobj;
    }
    
    
}
