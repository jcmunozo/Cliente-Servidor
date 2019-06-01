package logicaNegocio;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conector {
    
    private Connection conexion;
    private ResultSet resultado;
    private Statement estamento;
    private final String URL = "jdbc:hsqldb:file:E:\\INGENIERIA DE SISTEMAS\\6To Semestre\\Lab. Ingeniería de Software II\\Practica 05\\Taller4-cliente-servidorV2\\ServidorCentral\\bd\\clientes_planes;hsqldb.lock_file=false";
    //Cambie la URL de su bd local, por ejemplo, si tiene Windows,sería algo como:
    //private final String URL = "jdbc:hsqldb:file:C:\\clientes\\bd\\clientes;hsqldb.lock_file=false";    
    private final String USER = "usuario";
    private final String PASSWORD = "user";

    public Conector() {

    }
    
    public void conectarse() throws ClassNotFoundException, SQLException  {
        Class.forName("org.hsqldb.jdbcDriver");
        conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Se conecto a la Base de Datos");
    }
    
     /**
     * Ejecuta una consulta tipo Select
     * @param sql
     * @throws SQLException
     */
    public void crearConsulta(String sql) throws SQLException 
    {
        estamento = conexion.createStatement();
        resultado = estamento.executeQuery(sql);
    }
    
     /**
     * Ejecuta una consulta de tipo insert, update o delete
     *
     * @param sql
     * @throws SQLException
     */
    public void actualizar(String sql) throws SQLException {
        estamento = conexion.createStatement();
        estamento.executeUpdate(sql);
        
        
    }
  
    /**
     * Cierra las variables de rs, st y cn que estén abiertas
     * @throws SQLException 
     */
    public void desconectarse() throws SQLException {
        if ( resultado != null){
            resultado.close();
        }
        estamento.close();
        conexion.close();
    }
    /**
     * Devuelve todo el conjunto de resultados
     * @return 
     */
    public ResultSet getResultado() {
        return resultado;
    }
    
}
