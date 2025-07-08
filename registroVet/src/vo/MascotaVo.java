package vo;

public class MascotaVo extends Animal{


    private String nombre;
    private String idDueno;

    public String getNombre() {
        return nombre;
    }

    public String getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(String idDueno) {
        this.idDueno = idDueno;
    }

    public MascotaVo() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String mostrarInfo(){
        return "Documento dueño: " + idDueno + "\nNombre: " + nombre + "\nSexo: " + getSexo() + "\nRaza: " + getRaza() ;
    }


}
