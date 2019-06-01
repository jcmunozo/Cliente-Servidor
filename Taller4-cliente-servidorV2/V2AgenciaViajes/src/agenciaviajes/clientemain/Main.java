package agenciaviajes.clientemain;

import agenciaviajes.acceso.IRegistraduria;
import agenciaviajes.acceso.ServicioRegistraduriaSocket;
import agenciaviajes.negocio.Cliente;
import agenciaviajes.negocio.GestorClientes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author libardo
 */
public class Main{

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        RunMVC mainRunMVC = new RunMVC();
        
//        ServicioRegistraduriaSocket miServer = new ServicioRegistraduriaSocket();
//        GestorClientes myGestor = new GestorClientes();
//      
//        System.err.println(miServer.obtenerClientesDeLaBDRegistraduria());
//        System.err.println(miServer.obtenerPlanesDeLaBDRegistradutia());
//        
//        ArrayList<Cliente> misClientes = myGestor.obtenerClientesServidor();
//  
//        for(int i=0; i < misClientes.size(); i++)
//        {
//            Cliente myCli = misClientes.get(i);
//            System.out.println("ID: " + myCli.getId());
//            System.out.println("Nombres: " + myCli.getNombres());
//            System.out.println("Apellidos: " + myCli.getApellidos());
//            System.out.println("Fecha Nacimiento: " + myCli.getFechaNacimiento());
//            System.out.println("Sexo: " + myCli.getSexo());
//            System.out.println("E-mail: " + myCli.getEmail());
//            System.out.println("\n------------------------\n");               
//        }
    }

}
