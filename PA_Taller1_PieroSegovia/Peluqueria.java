/**
 * Contenedor principal de la peluquería Patitas & Pelos.
 * Almacena a los clientes registrados y el historial de atenciones
 * en arreglos separados.
 */
public class Peluqueria {

    /** Capacidad máxima de clientes. */
    public static final int MAX_CLIENTES = 100;

    /** Capacidad máxima de atenciones en el historial. */
    public static final int MAX_ATENCIONES = 500;

    /** Arreglo de clientes registrados. */
    private Persona[] clientes;

    /** Cantidad actual de clientes. */
    private int cantidadClientes;

    /** Arreglo de atenciones registradas. */
    private Atencion[] historial;

    /** Cantidad actual de atenciones. */
    private int cantidadAtenciones;

    /**
     * Crea una nueva peluquería con contenedores vacíos.
     */
    public Peluqueria() {
        clientes = new Persona[MAX_CLIENTES];
        cantidadClientes = 0;
        historial = new Atencion[MAX_ATENCIONES];
        cantidadAtenciones = 0;
    }

    /**
     * Obtiene el arreglo de clientes.
     * @return Arreglo de clientes.
     */
    public Persona[] getClientes() {
        return clientes;
    }

    /**
     * Obtiene la cantidad de clientes registrados.
     * @return Cantidad de clientes.
     */
    public int getCantidadClientes() {
        return cantidadClientes;
    }

    /**
     * Obtiene el arreglo del historial de atenciones.
     * @return Arreglo de atenciones.
     */
    public Atencion[] getHistorial() {
        return historial;
    }

    /**
     * Obtiene la cantidad de atenciones registradas.
     * @return Cantidad de atenciones.
     */
    public int getCantidadAtenciones() {
        return cantidadAtenciones;
    }

    /**
     * Agrega un cliente al contenedor.
     *
     * @param p Cliente a agregar.
     * @return true si se agregó, false si no hay espacio.
     */
    public boolean agregarCliente(Persona p) {
        if (cantidadClientes >= MAX_CLIENTES) {
            return false;
        }
        clientes[cantidadClientes] = p;
        cantidadClientes++;
        return true;
    }

    /**
     * Busca un cliente por su RUT.
     *
     * @param rut RUT a buscar.
     * @return El cliente encontrado, o null si no existe.
     */
    public Persona buscarCliente(String rut) {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getRut().equalsIgnoreCase(rut)) {
                return clientes[i];
            }
        }
        return null;
    }

    /**
     * Verifica si un RUT ya está registrado.
     *
     * @param rut RUT a verificar.
     * @return true si existe, false si no.
     */
    public boolean existeCliente(String rut) {
        return buscarCliente(rut) != null;
    }

    /**
     * Verifica si un ID de mascota ya existe entre todos los clientes.
     *
     * @param id ID a verificar.
     * @return true si existe, false si no.
     */
    public boolean existeIdMascota(int id) {
        for (int i = 0; i < cantidadClientes; i++) {
            Mascota[] ms = clientes[i].getMascotas();
            for (int j = 0; j < clientes[i].getCantidadMascotas(); j++) {
                if (ms[j].getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Agrega una atención al historial.
     *
     * @param a Atención a agregar.
     * @return true si se agregó, false si no hay espacio.
     */
    public boolean agregarAtencion(Atencion a) {
        if (cantidadAtenciones >= MAX_ATENCIONES) {
            return false;
        }
        historial[cantidadAtenciones] = a;
        cantidadAtenciones++;
        return true;
    }

    /**
     * Convierte el largo del pelo a un nivel numérico.
     * Sin pelo = 0, Corto = 1, Medio = 2, Largo = 3.
     *
     * @param largo Largo del pelo.
     * @return Nivel numérico, o -1 si no es válido.
     */
    public int nivelLargo(String largo) {
        if (largo.equalsIgnoreCase("Sin pelo")) return 0;
        if (largo.equalsIgnoreCase("Corto")) return 1;
        if (largo.equalsIgnoreCase("Medio")) return 2;
        if (largo.equalsIgnoreCase("Largo")) return 3;
        return -1;
    }

    /**
     * Verifica si un corte es compatible con el largo actual del pelo.
     *
     * @param largoActual Largo actual del pelo.
     * @param corte Corte solicitado.
     * @return true si es compatible.
     */
    public boolean esCorteCompatible(String largoActual, String corte) {
        if (largoActual.equalsIgnoreCase("Sin pelo")) return false;
        int nivelActual = nivelLargo(largoActual);
        int nivelCorte = nivelLargo(corte);
        return nivelCorte <= nivelActual;
    }

    /**
     * Devuelve el precio de un corte.
     *
     * @param corte Tipo de corte.
     * @return Precio en pesos, o 0 si no es válido.
     */
    public int precioCorte(String corte) {
        if (corte.equalsIgnoreCase("Corto")) return 10000;
        if (corte.equalsIgnoreCase("Medio")) return 20000;
        if (corte.equalsIgnoreCase("Largo")) return 30000;
        return 0;
    }
}