package main;

import java.sql.SQLException;
import logicaNegocio.Conector;

public class RunMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Conector myCon = new Conector();
        myCon.conectarse();
        
    }
    
}
