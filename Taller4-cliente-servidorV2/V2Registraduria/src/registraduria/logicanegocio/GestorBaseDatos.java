package registraduria.logicanegocio;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestorBaseDatos {
    
    private final ConectorJdbc myConector;
    
    public GestorBaseDatos()
    {
        myConector = new ConectorJdbc();
    }
    
    public ArrayList<Cliente> obtenerClientes() throws ClassNotFoundException, SQLException
    {
        myConector.conectarse();
        myConector.crearConsulta("SELECT * FROM CLIENTE");
        
        ArrayList<Cliente> misClientes = new ArrayList<>();
        
        while(myConector.getResultado().next())
        {
            Cliente myCliente = new Cliente(myConector.getResultado().getString("ID"), myConector.getResultado().getString("NOMBRES"), myConector.getResultado().getString("APELLIDOS"), myConector.getResultado().getString("FECHANAC"), myConector.getResultado().getString("SEXO"), myConector.getResultado().getString("EMAIL"));
            misClientes.add(myCliente);
        }
        
        myConector.desconectarse();
        return misClientes;
    }
    
    public ArrayList<Plan> obtenerPlanes() throws ClassNotFoundException, SQLException
    {
        myConector.conectarse();
        myConector.crearConsulta("SELECT * FROM PLAN");
        
        ArrayList<Plan> misPlanes = new ArrayList<>();
        
        while(myConector.getResultado().next())
        {
            Plan myPlan = new Plan(myConector.getResultado().getString("IDPLAN"), myConector.getResultado().getString("NOMBREPLAN"), myConector.getResultado().getString("DESCRIPCION"), myConector.getResultado().getString("RANGOEDAD1"), myConector.getResultado().getString("RANGOEDAD2"), myConector.getResultado().getString("GENERO"));
            misPlanes.add(myPlan);
        }
        
        myConector.desconectarse();
        return misPlanes;
    }
    
}
