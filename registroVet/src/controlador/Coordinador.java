package controlador;

import dao.MascotaDao;
import dao.PersonaDao;
import procesos.ProcesoMascota;
import procesos.ProcesoPersona;
import ventanas.VentanaMascotas;
import ventanas.VentanaPersonas;
import ventanas.VentanaPrincipal;
import vo.MascotaVo;
import vo.PersonaVo;

public class Coordinador {
    private MascotaDao mascotaDao;
    private PersonaDao personaDao;
    private ProcesoMascota procesoMascota;
    private ProcesoPersona procesoPersona;
    private VentanaMascotas ventanaMascotas;
    private VentanaPersonas ventanaPersonas;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaPrincipal getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public MascotaDao getMascotaDao() {
        return mascotaDao;
    }

    public void setMascotaDao(MascotaDao mascotaDao) {
        this.mascotaDao = mascotaDao;
    }

    public PersonaDao getPersonaDao() {
        return personaDao;
    }

    public void setPersonaDao(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    public ProcesoMascota getProcesoMascota() {
        return procesoMascota;
    }

    public void setProcesoMascota(ProcesoMascota procesoMascota) {
        this.procesoMascota = procesoMascota;
    }

    public ProcesoPersona getProcesoPersona() {
        return procesoPersona;
    }

    public void setProcesoPersona(ProcesoPersona procesoPersona) {
        this.procesoPersona = procesoPersona;
    }

    public VentanaMascotas getVentanaMascotas() {
        return ventanaMascotas;
    }

    public void setVentanaMascotas(VentanaMascotas ventanaMascotas) {
        this.ventanaMascotas = ventanaMascotas;
    }

    public VentanaPersonas getVentanaPersonas() {
        return ventanaPersonas;
    }

    public void setVentanaPersonas(VentanaPersonas ventanaPersonas) {
        this.ventanaPersonas = ventanaPersonas;
    }


    public Coordinador() {
        mascotaDao = new MascotaDao();
        personaDao = new PersonaDao();
    }

    public void registrarMascota(MascotaVo mascota) {
        try {
            mascotaDao.registrarMascota(mascota);
        }catch (RuntimeException e){
            throw e;
        }

    }

    public void registrarPersona(PersonaVo persona){
        personaDao.registrarPersona(persona);
    }

    public void actualizarPersona(PersonaVo persona){
        personaDao.actualizar(persona);
        procesoPersona.actualizar(persona);
    }

    public void eliminarPersona(String documento){
        try {
            personaDao.eliminar(documento);
            procesoPersona.eliminar(documento);
        }catch (RuntimeException e){
            throw e;
        }

    }

    public String listarPersona(){
        return personaDao.listar();
    }

    public boolean personaYaExiste(String documento) {
        return personaDao.existeDocumento(documento);
    }

    public  void  actualizarMascota(MascotaVo mascota){
        mascotaDao.actualizar(mascota);
        procesoMascota.actualizar(mascota);
    }

    public void eliminarMascota(String nombre){
        mascotaDao.eliminar(nombre);
        procesoMascota.eliminar(nombre);
    }

    public String listarMacota(){
        return mascotaDao.listar();
    }

    public void mostrarMacotas(){

        if (ventanaMascotas == null || !ventanaMascotas.isDisplayable()){
            ventanaMascotas = new VentanaMascotas();
            ventanaMascotas.setCoordinador(this);
            ventanaMascotas.setVisible(true);
        }else {
            ventanaMascotas.toFront();
        }

    }

    public void mostrarPersonas(){
        if (ventanaPersonas == null || !ventanaPersonas.isDisplayable()){
            ventanaPersonas = new VentanaPersonas();
            ventanaPersonas.setCoordinador(this);
            ventanaPersonas.setVisible(true);
        }else {
            ventanaPersonas.toFront();
        }

    }




}
