package procesos;

public abstract class ProcesosGenerales {


    public abstract void registrar(Object objeto);

    public abstract void actualizar(Object obj);

    public abstract void eliminar(String documento);

    public abstract Object consultar(String id);

    public abstract String listar();
}
