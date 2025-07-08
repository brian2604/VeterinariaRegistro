package vo;
public class PersonaVo {

    private String documento;
    private String nombre;
    private String telefono;

    public PersonaVo(String documento, String nombre, String telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public PersonaVo() {

    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String mostrarInfo(){
        return "Documento: " + documento + "\nNombre: " + nombre + "\nTelefono: " + telefono;
    }
}
