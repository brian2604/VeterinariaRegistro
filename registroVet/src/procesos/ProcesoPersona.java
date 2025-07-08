package procesos;

import vo.PersonaVo;

import java.util.HashMap;

public class ProcesoPersona extends ProcesosGenerales{
    private HashMap<String, PersonaVo> personas = new HashMap<>();


    @Override
    public void registrar(Object obj) {
        PersonaVo persona = (PersonaVo) obj;
        personas.put(persona.getDocumento(), persona);
    }

    @Override
    public void actualizar(Object obj) {
        PersonaVo persona = (PersonaVo) obj;
        personas.put(persona.getDocumento(), persona);
    }

    @Override
    public void eliminar(String documento) {
        personas.remove(documento);
    }

    @Override
    public Object consultar(String documento) {
        return personas.get(documento);
    }

    @Override
    public String listar() {
        StringBuilder sb = new StringBuilder();
        for (PersonaVo p: personas.values()){
            sb.append(p.mostrarInfo()).append("\n\n");
        }
        return sb.toString();
    }


}
