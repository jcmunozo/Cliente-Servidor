package servicio;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import logicaNegocio.Cliente;

public class ServidorCentralServer {
    
    public String serializar(ArrayList<Cliente> listaClientes) 
    {
        JsonArray array = new JsonArray();
        JsonObject myObjJson;
        
        for(Cliente cli : listaClientes)
        {
            myObjJson = parseToJSON(cli);
            array.add(myObjJson);
        }
        
        return array.toString();
    }
    
    private JsonObject parseToJSON(Cliente cliente)
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
    
}
