package controlador;
import dao.MascotaDao;
import dao.PersonaDao;
import procesos.ProcesoMascota;
import procesos.ProcesoPersona;
import ventanas.VentanaMascotas;
import ventanas.VentanaPersonas;
import ventanas.VentanaPrincipal;

public class Relaciones {
    // Instancias
    private Coordinador coordinador;
    private MascotaDao mascotaDao;
    private PersonaDao personaDao;
    private ProcesoMascota procesoMascota;
    private ProcesoPersona procesoPersona;
    private VentanaMascotas ventanaMascotas;
    private VentanaPersonas ventanaPersonas;
    private VentanaPrincipal ventanaPrincipal;

    public void iniciar() {
        // Crear objetos
        coordinador = new Coordinador();
        mascotaDao = new MascotaDao();
        personaDao = new PersonaDao();
        procesoMascota = new ProcesoMascota();
        procesoPersona = new ProcesoPersona();
        ventanaMascotas = new VentanaMascotas();
        ventanaPersonas = new VentanaPersonas();
        ventanaPrincipal = new VentanaPrincipal();

        // Enlazar al coordinador
        coordinador.setMascotaDao(mascotaDao);
        coordinador.setPersonaDao(personaDao);
        coordinador.setProcesoMascota(procesoMascota);
        coordinador.setProcesoPersona(procesoPersona);
        coordinador.setVentanaMascotas(ventanaMascotas);
        coordinador.setVentanaPersonas(ventanaPersonas);
        coordinador.setVentanaPrincipal(ventanaPrincipal);

        // Pasar coordinador a las ventanas
        ventanaMascotas.setCoordinador(coordinador);
        ventanaPersonas.setCoordinador(coordinador);
        ventanaPrincipal.setCoordinador(coordinador);

        ventanaPrincipal.setVisible(true);


    }

    public Coordinador getCoordinador() {
        return coordinador;
    }
}
