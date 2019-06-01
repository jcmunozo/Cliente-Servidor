package registraduria.logicanegocio;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConectorJdbc { 
    
    private Connection conexion;
    private ResultSet resultado;
    private Statement estamento;
    private final String URL = "jdbc:hsqldb:file:E:\\INGENIERIA DE SISTEMAS\\6To Semestre\\Lab. Ingeniería de Software II\\Practica 05\\Taller4-cliente-servidorV2\\V2Registraduria\\BASEDATOS\\clientesPlanes;hsqldb.lock_file=false";
    private final String USER = "alex";
    private final String PASSWORD = "1234";
    
    public ConectorJdbc() {
        
    }
    
    public void conectarse() throws  ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        conexion = DriverManager.getConnection(URL, USER, PASSWORD);
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
