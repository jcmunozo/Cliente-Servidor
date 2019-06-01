package agenciaviajes.negocio;



public class Plan {
    
    private String idPlan;
    private String nombrePlan;
    private String descripcion;
    private String rangoEdad1;
    private String rangoEdad2;
    private String genero;

    public Plan() {
        
    }
    public Plan(String idPlan, String nombrePlan, String descripcion, String rangoEdad1, String rangoEdad2, String genero) {
        this.idPlan = idPlan;
        this.nombrePlan = nombrePlan;
        this.descripcion = descripcion;
        this.rangoEdad1 = rangoEdad1;
        this.rangoEdad2 = rangoEdad2;
        this.genero = genero;
    }
   

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRangoEdad1() {
        return rangoEdad1;
    }

    public void setRangoEdad1(String rangoEdad1) {
        this.rangoEdad1 = rangoEdad1;
    }

    public String getRangoEdad2() {
        return rangoEdad2;
    }

    public void setRangoEdad2(String rangoEdad2) {
        this.rangoEdad2 = rangoEdad2;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
