/**
 * Representa a un cliente de la peluquería Patitas & Pelos.
 * Cada persona tiene un nombre, un RUT único y un contenedor
 * de mascotas asociadas.
 *

 */
public class Persona {

    /** Nombre completo del cliente. */
    private String nombre;

    /** RUT del cliente */
    private String rut;

    /** Arreglo de mascotas asociadas al cliente. */
    private Mascota[] mascotas;

    /** Cantidad de mascotas registradas. */
    private int cantidadMascotas;

    /**
     * Crea una nueva persona con su nombre, RUT y capacidad máxima de mascotas.

     */
    public Persona(String nombre, String rut, int maxMascotas) {
        this.nombre = nombre;
        this.rut = rut;
        this.mascotas = new Mascota[maxMascotas];
        this.cantidadMascotas = 0;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el RUT del cliente.
     * @return RUT del cliente.
     */
    public String getRut() {
        return rut;
    }

    /**
     * Obtiene el arreglo de mascotas.
     * @return Arreglo de mascotas.
     */
    public Mascota[] getMascotas() {
        return mascotas;
    }

    /**
     * Obtiene la cantidad actual de mascotas.
     * @return Cantidad de mascotas registradas.
     */
    public int getCantidadMascotas() {
        return cantidadMascotas;
    }

    /**
     * Agrega una mascota al contenedor del cliente.
     *
     * @param m Mascota a agregar.
     * @return true si se agregó, false si no hay espacio.
     */
    public boolean agregarMascota(Mascota m) {
        if (cantidadMascotas >= mascotas.length) {
            return false;
        }
        mascotas[cantidadMascotas] = m;
        cantidadMascotas++;
        return true;
    }


    @Override
    public String toString() {
        return nombre + " - " + rut;
    }
}