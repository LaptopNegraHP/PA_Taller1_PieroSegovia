import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal del sistema Patitas & Pelos.
 * Muestra el menú, carga los archivos y ejecuta cada funcionalidad.
 */
public class Main {

    /**
     * Método principal. Carga los archivos, muestra el menú
     * y ejecuta las opciones hasta que el usuario decida salir.
     *
     * @param args Argumentos de línea de comandos (no usados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Peluqueria peluqueria = new Peluqueria();

        // Carga inicial de archivos
        try {
            LectorArchivos.leerHistorial("historial.txt", peluqueria);
            LectorArchivos.leerClientes("clientes.txt", peluqueria);
            System.out.println("Archivos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer archivos: " + e.getMessage());
        }

        int opcion;
        do {
            System.out.println("\n========================");
            System.out.println("=   MENU PRINCIPAL     =");
            System.out.println("========================");
            System.out.println("[1] Registrar Persona");
            System.out.println("[2] Agregar Mascotas");
            System.out.println("[3] Consultar Clientes y Mascotas");
            System.out.println("[4] Realizar Corte");
            System.out.println("[5] Consultar Historial");
            System.out.println("[6] Finalizar Programa");
            System.out.print("Opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
                opcion = -1;
            }

            switch (opcion) {
                case 1: registrarPersona(sc, peluqueria); break;
                case 2: agregarMascota(sc, peluqueria); break;
                case 3: consultarClientes(sc, peluqueria); break;
                case 4: realizarCorte(sc, peluqueria); break;
                case 5: consultarHistorial(peluqueria); break;
                case 6: System.out.println("Cerrando programa..."); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 6);

        sc.close();
    }
    /**
     * Registra una nueva persona en la peluquería.
     * Valida que el RUT no esté duplicado y permite agregar mascotas.
     *
     * @param sc Scanner para leer entrada.
     * @param peluqueria Contenedor de clientes.
     */

    public static void registrarPersona(Scanner sc, Peluqueria peluqueria) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("RUT: ");
        String rut = sc.nextLine().trim();

        if (peluqueria.existeCliente(rut)) {
            System.out.println("Error: el RUT " + rut + " ya está registrado.");
            return;
        }

        // Creamos la persona con espacio para 10 mascotas
        Persona persona = new Persona(nombre, rut, 10);

        System.out.print("¿Desea agregar mascotas ahora? (s/n): ");
        String resp = sc.nextLine().trim().toLowerCase();

        if (resp.equals("s")) {
            boolean seguir = true;
            while (seguir) {
                Mascota m = pedirDatosMascota(sc, peluqueria);
                if (m != null) {
                    if (persona.agregarMascota(m)) {
                        System.out.println("Mascota agregada.");
                    } else {
                        System.out.println("No hay espacio para más mascotas.");
                    }
                }
                System.out.print("¿Agregar otra mascota? (s/n): ");
                resp = sc.nextLine().trim().toLowerCase();
                if (!resp.equals("s")) seguir = false;
            }
        }

        if (peluqueria.agregarCliente(persona)) {
            System.out.println("Persona registrada correctamente.");
        } else {
            System.out.println("No hay espacio para más clientes.");
        }
    }

    public static Mascota pedirDatosMascota(Scanner sc, Peluqueria peluqueria) {
        System.out.print("ID de la mascota: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return null;
        }

        if (peluqueria.existeIdMascota(id)) {
            System.out.println("Error: el ID " + id + " ya existe.");
            return null;
        }

        System.out.print("Nombre de la mascota: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Especie: ");
        String especie = sc.nextLine().trim();

        System.out.print("Edad: ");
        int edad;
        try {
            edad = Integer.parseInt(sc.nextLine().trim());
            if (edad < 0) {
                System.out.println("La edad no puede ser negativa.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Edad inválida.");
            return null;
        }

        String largo = pedirLargoPelo(sc);
        if (largo == null) return null;

        return new Mascota(id, nombre, especie, edad, largo);
    }

    public static String pedirLargoPelo(Scanner sc) {
        System.out.println("Largo del pelo:");
        System.out.println("  [1] Sin pelo");
        System.out.println("  [2] Corto");
        System.out.println("  [3] Medio");
        System.out.println("  [4] Largo");
        System.out.print("Opción: ");

        int op;
        try {
            op = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return null;
        }

        switch (op) {
            case 1:
                return "Sin pelo";
            case 2:
                return "Corto";
            case 3:
                return "Medio";
            case 4:
                return "Largo";
            default:
                System.out.println("Opción fuera de rango.");
                return null;
        }
    }
    /**
     * Agrega una mascota a un cliente existente.
     * Busca por RUT y valida el ID único de la mascota.
     *
     * @param sc Scanner para leer entrada.
     * @param peluqueria Contenedor de clientes.
     */
    public static void agregarMascota(Scanner sc, Peluqueria peluqueria) {
        System.out.print("RUT del dueño: ");
        String rut = sc.nextLine().trim();

        Persona persona = peluqueria.buscarCliente(rut);
        if (persona == null) {
            System.out.println("No existe un cliente con ese RUT. Regístrelo primero.");
            return;
        }

        Mascota m = pedirDatosMascota(sc, peluqueria);
        if (m == null) return;

        if (persona.agregarMascota(m)) {
            System.out.println("Mascota agregada correctamente a " + persona.getNombre() + ".");
        } else {
            System.out.println("No hay espacio para más mascotas.");
        }

    }

    /**
     * Muestra los clientes registrados y consulta las mascotas
     * de un cliente por su RUT.
     *
     * @param sc Scanner para leer entrada.
     * @param peluqueria Contenedor de clientes.
     */
    public static void consultarClientes(Scanner sc, Peluqueria peluqueria) {
        if (peluqueria.getCantidadClientes() == 0) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("\n--- Clientes registrados ---");
        for (int i = 0; i < peluqueria.getCantidadClientes(); i++) {
            Persona p = peluqueria.getClientes()[i];
            System.out.println(p.getNombre() + " - " + p.getRut());
        }

        System.out.print("\nIngrese RUT a consultar: ");
        String rut = sc.nextLine().trim();

        Persona persona = peluqueria.buscarCliente(rut);
        if (persona == null) {
            System.out.println("RUT no encontrado.");
            return;
        }

        if (persona.getCantidadMascotas() == 0) {
            System.out.println(persona.getNombre() + " no tiene mascotas registradas.");
            return;
        }

        System.out.println("\n--- Mascotas de " + persona.getNombre() + " ---");
        Mascota[] ms = persona.getMascotas();
        for (int i = 0; i < persona.getCantidadMascotas(); i++) {
            System.out.println(ms[i].toString());
        }
    }
    /**
     * Realiza un corte a una mascota de un cliente.
     * Muestra cortes compatibles, aplica probabilidad de fallo 1/3,
     * actualiza el pelaje y registra la atención.
     *
     * @param sc Scanner para leer entrada.
     * @param peluqueria Contenedor de clientes y atenciones.
     */
    public static void realizarCorte(Scanner sc, Peluqueria peluqueria) {
        System.out.print("RUT del cliente: ");
        String rut = sc.nextLine().trim();

        Persona persona = peluqueria.buscarCliente(rut);
        if (persona == null) {
            System.out.println("No existe un cliente con ese RUT.");
            return;
        }

        if (persona.getCantidadMascotas() == 0) {
            System.out.println(persona.getNombre() + " no tiene mascotas registradas.");
            return;
        }

        // Mostrar mascotas
        System.out.println("\n--- Mascotas de " + persona.getNombre() + " ---");
        Mascota[] ms = persona.getMascotas();
        for (int i = 0; i < persona.getCantidadMascotas(); i++) {
            System.out.println("[" + i + "] " + ms[i].getId() + " - " + ms[i].getNombre()
                    + " (" + ms[i].getLargoPelo() + ")");
        }

        System.out.print("Seleccione el índice de la mascota: ");
        int idx;
        try {
            idx = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Índice inválido.");
            return;
        }

        if (idx < 0 || idx >= persona.getCantidadMascotas()) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        Mascota mascota = ms[idx];

        if (mascota.getLargoPelo().equalsIgnoreCase("Sin pelo")) {
            System.out.println("Esta mascota no puede recibir cortes (sin pelo).");
            return;
        }

        // Mostrar cortes compatibles
        String[] cortes = {"Corto", "Medio", "Largo"};
        System.out.println("\n--- Cortes compatibles ---");
        for (int i = 0; i < cortes.length; i++) {
            if (peluqueria.esCorteCompatible(mascota.getLargoPelo(), cortes[i])) {
                System.out.println("[" + (i+1) + "] " + cortes[i] + " - $" + peluqueria.precioCorte(cortes[i]));
            }
        }

        System.out.print("Seleccione el corte: ");
        int opCorte;
        try {
            opCorte = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return;
        }

        if (opCorte < 1 || opCorte > cortes.length) {
            System.out.println("Opción fuera de rango.");
            return;
        }

        String corteElegido = cortes[opCorte - 1];

        if (!peluqueria.esCorteCompatible(mascota.getLargoPelo(), corteElegido)) {
            System.out.println("Ese corte no es compatible con el largo actual.");
            return;
        }

        // Determinar éxito o fallo (1/3 de fallo)
        boolean exito = Math.random() >= (1.0 / 3.0);

        String largoOriginal = mascota.getLargoPelo();
        if (exito) {
            mascota.setLargoPelo(corteElegido);
        }

        int precio = peluqueria.precioCorte(corteElegido);

        // Registrar en historial
        Atencion atencion = new Atencion(rut, mascota.getId(), corteElegido, precio, exito);
        peluqueria.agregarAtencion(atencion);

        // Mostrar comprobante
        System.out.println("\n=== COMPROBANTE ===");
        System.out.println("Cliente: " + persona.getNombre() + " (" + rut + ")");
        System.out.println("Mascota: " + mascota.getNombre() + " (ID " + mascota.getId() + ")");
        System.out.println("Corte solicitado: " + corteElegido);
        System.out.println("Resultado: " + (exito ? "Exitoso" : "Fallido"));
        System.out.println("Largo antes: " + largoOriginal);
        System.out.println("Largo después: " + mascota.getLargoPelo());
        System.out.println("Total pagado: $" + precio);
    }
    /**
     * Muestra todas las atenciones registradas en el historial.
     *
     * @param peluqueria Contenedor de atenciones.
     */
    public static void consultarHistorial(Peluqueria peluqueria) {
        if (peluqueria.getCantidadAtenciones() == 0) {
            System.out.println("No hay atenciones registradas.");
            return;
        }

        System.out.println("\n--- HISTORIAL DE CORTES ---");
        Atencion[] historial = peluqueria.getHistorial();
        for (int i = 0; i < peluqueria.getCantidadAtenciones(); i++) {
            System.out.println(historial[i].toString());
        }
    }

}