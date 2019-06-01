package agenciaviajes.negocio;

import agenciaviajes.acceso.ServicioRegistraduriaSocket;
import java.util.ArrayList;
import mvcf.AModel;
import com.google.gson.Gson;
import agenciaviajes.acceso.IRegistraduria;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Representa el modelo (Observable) de datos Cuando hay cambios en el estado,
 * notifica a todas sus vistas (observadores)
 *
 * @author Julio, Libardo, Ricardo
 */
public class GestorClientes extends AModel {

    private final IRegistraduria registraduria;
    public ArrayList<Cliente> listaClientes;
    
    public GestorClientes() {
        listaClientes = new ArrayList<>();
        registraduria = new ServicioRegistraduriaSocket();
    }

    /**
     * Obtiene los Clientes que se encuentran en el servidor remoto
     * de Registraduria.
     * 
     * return ArrayList de tipo Cliente, null si no lo encuentra
     * @return 
     */
    public ArrayList<Cliente> obtenerClientesServidor()
    {
        String arrayJson = registraduria.obtenerClientesDeLaBDRegistraduria();
        if(!arrayJson.equals("NO_ENCONTRADO"))
        {
            //lo Encontro
            Cliente[] misClientes;
            misClientes = deserializarMisClientesV2(arrayJson);
            for (int i = 0; i < misClientes.length; i++) {
                System.out.println(misClientes[i].getNombres());
            }
            ArrayList<Cliente> listClientes = convertirArrayList(misClientes);
            listaClientes = listClientes;
            this.notificar();
            return listClientes;
        }
        
        return null;
    }
    
    private ArrayList<Cliente> deserializarMisClientes( String arrayJsonSerializado)
    {
        ArrayList<Cliente> listaC = new ArrayList<>();
        JsonArray array = new Gson().fromJson(arrayJsonSerializado, JsonArray.class);
        
        for(JsonElement elem : array)
        {
            Cliente cli = new Cliente();
            
            cli.setId(elem.getAsJsonObject().get("id").getAsString());
            cli.setNombres(elem.getAsJsonObject().get("nombres").getAsString());
            cli.setApellidos(elem.getAsJsonObject().get("apellidos").getAsString());
            cli.setFechaNacimiento(elem.getAsJsonObject().get("fechaNacimiento").getAsString());
            cli.setSexo(elem.getAsJsonObject().get("sexo").getAsString());
            cli.setEmail(elem.getAsJsonObject().get("email").getAsString());
            
            listaC.add(cli);
        }
        return listaC;
    }
    
    public Cliente[] deserializarMisClientesV2( String arrayJsonSerializado)
    {
        Cliente[] listaClientes = new Gson().fromJson(arrayJsonSerializado, Cliente[].class);
        return listaClientes;
    }
     
    private ArrayList<Cliente> convertirArrayList(Cliente[] misClientes)
    {
        ArrayList<Cliente> listaCli = new ArrayList<>();
        for(int i=0; i<misClientes.length; i++)
        {
            listaCli.add(misClientes[i]);
        }
        return listaCli;
    }
    
    /**
     * Obtiene los Planes que se encuentran en el servidor remoto
     * de Registraduria.
     * 
     * return ArrayList de tipo Cliente, null si no lo encuentra
     * @return 
     */
    public ArrayList<Plan> obtenerPlanesServidor()
    {
        String arrayJson = registraduria.obtenerPlanesDeLaBDRegistradutia();
        if(!arrayJson.equals("NO_ENCONTRADO"))
        {
            //lo Encontro
            Plan[] misPlanes;
            misPlanes = deserializarMisPlanes(arrayJson);
            ArrayList<Plan> listaPlanes = convertirArrayListPlan(misPlanes);
            return listaPlanes;
        }
        
        return null;
    }
    
    public Plan[] deserializarMisPlanes( String arrayJsonSerializado)
    {
        Plan[] listaPlanes = new Gson().fromJson(arrayJsonSerializado, Plan[].class);
        return listaPlanes;
    }
    
    public ArrayList<Plan> convertirArrayListPlan(Plan[] misClientes)
    {
        ArrayList<Plan> listaPlanes = new ArrayList<>();
        for(Plan plan: misClientes)
        {
            listaPlanes.add(plan);
        }
        return listaPlanes;
    }
    
    public ArrayList<Cliente> obtenerClientes() {
        return listaClientes;
    }
    
    public int getTotalHombres(){
        return 12;
    }
    
    public int getTotalMujeres(){
        return 6;
    }    
}
