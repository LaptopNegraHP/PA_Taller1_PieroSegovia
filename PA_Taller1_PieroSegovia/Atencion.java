/**
 * Representa un registro del historial de cortes realizados
 * en la peluquería Patitas & Pelos.
 */
public class Atencion {

    /** RUT del dueño de la mascota. */
    private String rutDueno;

    /** ID de la mascota atendida. */
    private int idMascota;

    /** Tipo de corte solicitado. */
    private String corteRealizado;

    /** Total pagado por el servicio. */
    private int totalPagado;

    /** Indica si el corte fue exitoso (true) o fallido (false). */
    private boolean fueExitoso;

    /**
     * Crea un nuevo registro de atención.
     *
     * @param rutDueno RUT del dueño.
     * @param idMascota ID de la mascota.
     * @param corteRealizado Tipo de corte.
     * @param totalPagado Total pagado.
     * @param fueExitoso Resultado del corte.
     */
    public Atencion(String rutDueno, int idMascota, String corteRealizado,
                    int totalPagado, boolean fueExitoso) {
        this.rutDueno = rutDueno;
        this.idMascota = idMascota;
        this.corteRealizado = corteRealizado;
        this.totalPagado = totalPagado;
        this.fueExitoso = fueExitoso;
    }

    /**
     * Obtiene el RUT del dueño.
     * @return RUT del dueño.
     */
    public String getRutDueno() {
        return rutDueno;
    }

    /**
     * Obtiene el ID de la mascota.
     * @return ID de la mascota.
     */
    public int getIdMascota() {
        return idMascota;
    }

    /**
     * Obtiene el tipo de corte realizado.
     * @return Tipo de corte.
     */
    public String getCorteRealizado() {
        return corteRealizado;
    }

    /**
     * Obtiene el total pagado.
     * @return Total pagado.
     */
    public int getTotalPagado() {
        return totalPagado;
    }

    /**
     * Indica si el corte fue exitoso.
     * @return true si fue exitoso, false si falló.
     */
    public boolean isFueExitoso() {
        return fueExitoso;
    }

    /**
     * Devuelve una representación en texto de la atención.
     * @return Cadena con los datos de la atención.
     */
    @Override
    public String toString() {
        return rutDueno + " | ID: " + idMascota + " | " + corteRealizado
                + " | $" + totalPagado + " | " + (fueExitoso ? "Exitoso" : "Fallido");
    }
}