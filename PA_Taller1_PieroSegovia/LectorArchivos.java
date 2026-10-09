import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase encargada de leer los archivos historial.txt y clientes.txt
 * al iniciar el programa, cargando los datos en la peluquería.
 */
public class LectorArchivos {

    /**
     * Lee el archivo historial.txt y llena el historial de la peluquería.
     * Formato esperado: rut,id,corte,total,exitoso
     *
     * @param ruta Ruta del archivo.
     * @param peluqueria Peluquería donde se guardan las atenciones.
     * @throws IOException Si el archivo no existe o no se puede leer.
     */
    public static void leerHistorial(String ruta, Peluqueria peluqueria) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split(",");
            if (partes.length < 5) continue;

            String rut = partes[0].trim();
            int id = Integer.parseInt(partes[1].trim());
            String corte = partes[2].trim().replace("\"", "");
            int total = Integer.parseInt(partes[3].trim());
            boolean exitoso = Boolean.parseBoolean(partes[4].trim());

            peluqueria.agregarAtencion(new Atencion(rut, id, corte, total, exitoso));
        }
        br.close();
    }

    /**
     * Lee el archivo clientes.txt y llena los clientes con sus mascotas.
     * Formato esperado:
     *   "Nombre",rut,cantidadMascotas
     *   id,"NombreMascota","Especie",edad,"Largo"
     *
     * @param ruta Ruta del archivo.
     * @param peluqueria Peluquería donde se guardan los clientes.
     * @throws IOException Si el archivo no existe o no se puede leer.
     */
    public static void leerClientes(String ruta, Peluqueria peluqueria) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) continue;

            String[] partes = linea.split(",");
            String nombre = partes[0].trim().replace("\"", "");
            String rut = partes[1].trim();
            int cantMascotas = Integer.parseInt(partes[2].trim());

            Persona persona = new Persona(nombre, rut, cantMascotas);

            for (int i = 0; i < cantMascotas; i++) {
                String lineaMascota = br.readLine();
                if (lineaMascota == null) break;
                lineaMascota = lineaMascota.trim();
                if (lineaMascota.isEmpty()) { i--; continue; }

                String[] pm = lineaMascota.split(",");
                int id = Integer.parseInt(pm[0].trim());
                String nombreM = pm[1].trim().replace("\"", "");
                String especie = pm[2].trim().replace("\"", "");
                int edad = Integer.parseInt(pm[3].trim());
                String largo = pm[4].trim().replace("\"", "");

                persona.agregarMascota(new Mascota(id, nombreM, especie, edad, largo));
            }

            peluqueria.agregarCliente(persona);
        }
        br.close();
    }
}