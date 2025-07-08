package procesos;

import vo.MascotaVo;
import java.util.HashMap;

public class ProcesoMascota extends ProcesosGenerales {
    private HashMap<String, MascotaVo> mascotas = new HashMap<>();

    @Override
    public void registrar(Object obj) {
        MascotaVo mascota = (MascotaVo) obj;
        mascotas.put(mascota.getNombre(), mascota);
    }

    @Override
    public MascotaVo consultar(String nombre) {
        return mascotas.get(nombre);
    }

    @Override
    public void actualizar(Object obj) {
        MascotaVo mascota = (MascotaVo) obj;
        mascotas.put(mascota.getNombre(), mascota);
    }

    @Override
    public void eliminar(String nombre) {
        mascotas.remove(nombre);
    }

    @Override
    public String listar() {
        StringBuilder sb = new StringBuilder();
        for (MascotaVo m : mascotas.values()) {
            sb.append("Nombre: ").append(m.getNombre())
                    .append("\nDueño: ").append(m.getIdDueno())
                    .append("\nRaza: ").append(m.getRaza())
                    .append("\nSexo: ").append(m.getSexo())
                    .append("\n\n");
        }
        return sb.toString();
    }
}

