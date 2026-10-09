
/**
 * Representa una mascota registrada en la peluquería Patitas & Pelos.
 * Cada mascota tiene un identificador único, nombre, especie, edad
 * y un largo de pelaje que puede cambiar tras un corte.
 */

public class Mascota {

    private int id;
    private String nombre;
    private String especie;
    private int edad;
    private String largoPelo; // "Sin pelo", "Corto", "Medio", "Largo"
    /**
     * Crea una nueva mascota con todos sus datos.
     *
     * @param id Identificador único de la mascota.
     * @param nombre Nombre de la mascota.
     * @param especie Especie de la mascota.
     * @param edad Edad en años cumplidos.
     * @param largoPelo Largo actual del pelaje.
     */

    public Mascota(int id, String nombre, String especie, int edad, String largoPelo) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.largoPelo = largoPelo;

    }
    /**
     * Obtiene el identificador único de la mascota.
     * @return ID de la mascota.
     */

    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la mascota.
     * @return Nombre de la mascota.
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la especie de la mascota.
     * @return Especie de la mascota.
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Obtiene la edad de la mascota.
     * @return Edad en años.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el largo actual del pelaje.
     * @return Largo del pelaje.
     */
    public String getLargoPelo() {
        return largoPelo;
    }

    /**
     * Actualiza el largo del pelaje de la mascota.
     * @param largoPelo Nuevo largo del pelaje.
     */
    public void setLargoPelo(String largoPelo) {
        this.largoPelo = largoPelo;
    }
    /**
     * Devuelve una representación en texto de la mascota.
     * @return Cadena con los datos de la mascota.
     */

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Especie: " + especie
                + " | Edad: " + edad + " | Pelo: " + largoPelo;
    }
}