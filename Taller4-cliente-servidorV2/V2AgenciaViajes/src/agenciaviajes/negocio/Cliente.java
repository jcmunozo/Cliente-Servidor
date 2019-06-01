package agenciaviajes.negocio;

/**
 *
 * @author Libardo, Ricardo, Julio Representa un cliente de la agencia de viajes
 * con sus atributos
 *
 */
public class Cliente {

    
    private String id;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String sexo;
    private String email;

    public Cliente(String id, String nombres, String apellidos, String fechaNacimiento, String sexo, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.email = email;
    }

    Cliente() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
