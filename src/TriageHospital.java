import java.util.*;

class Paciente {
    String nombre;
    int prioridad; // 1=rojo, 2=amarillo, 3=verde
    Date horaLlegada;

    public Paciente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.horaLlegada = new Date();
    }

    @Override
    public String toString() {
        String color = switch (prioridad) {
            case 1 -> "Rojo";
            case 2 -> "Amarillo";
            case 3 -> "Verde";
            default -> "Desconocido";
        };
        return nombre + " (" + color + ") - " + horaLlegada.toString();
    }
}

public class TriageHospital {
    private Queue<Paciente> colaRojo = new LinkedList<>();
    private Queue<Paciente> colaAmarillo = new LinkedList<>();
    private Queue<Paciente> colaVerde = new LinkedList<>();
    private Stack<Paciente> historial = new Stack<>();
    private List<Paciente> atendidos = new ArrayList<>();

    // 1️⃣ Registrar paciente
    public void registrarPaciente(String nombre, int prioridad) {
        Paciente p = new Paciente(nombre, prioridad);
        switch (prioridad) {
            case 1 -> colaRojo.add(p);
            case 2 -> colaAmarillo.add(p);
            case 3 -> colaVerde.add(p);
            default -> System.out.println("⚠️ Prioridad inválida (use 1, 2 o 3).");
        }
        System.out.println("✅ Registrado: " + p);
    }

    // 2️⃣ Ver siguiente paciente
    public void verSiguiente() {
        Paciente siguiente = obtenerSiguiente(false);
        if (siguiente != null) {
            System.out.println("👀 Siguiente paciente: " + siguiente);
        } else {
            System.out.println("⛔ No hay pacientes en espera.");
        }
    }

    // 3️⃣ Atender paciente
    public void atender() {
        Paciente siguiente = obtenerSiguiente(true);
        if (siguiente != null) {
            atendidos.add(siguiente);
            historial.push(siguiente);
            System.out.println("🚑 Atendiendo a: " + siguiente);
        } else {
            System.out.println("⛔ No hay pacientes para atender.");
        }
    }

    // 4️⃣ Mostrar contadores
    public void mostrarContadores() {
        System.out.println("\n📊 Contadores:");
        System.out.println("   🔴 Rojo: " + colaRojo.size());
        System.out.println("   🟡 Amarillo: " + colaAmarillo.size());
        System.out.println("   🟢 Verde: " + colaVerde.size());
    }

    // 🧩 Extra: Deshacer última atención
    public void deshacerUltimaAtencion() {
        if (historial.isEmpty()) {
            System.out.println("⛔ No hay atenciones para deshacer.");
            return;
        }
        Paciente ultimo = historial.pop();
        atendidos.remove(ultimo);
        switch (ultimo.prioridad) {
            case 1 -> ((LinkedList<Paciente>) colaRojo).addFirst(ultimo);
            case 2 -> ((LinkedList<Paciente>) colaAmarillo).addFirst(ultimo);
            case 3 -> ((LinkedList<Paciente>) colaVerde).addFirst(ultimo);
        }
        System.out.println("↩️ Atención deshecha: " + ultimo.nombre + " volvió a la cola.");
    }

    // 🧾 Extra: Reporte
    public void reporte() {
        System.out.println("\n📋 Reporte de Triage");
        System.out.println("🩺 Atendidos:");
        if (atendidos.isEmpty()) System.out.println("   (Ninguno)");
        for (Paciente p : atendidos) System.out.println("   - " + p);

        System.out.println("\n🕒 En espera:");
        for (Paciente p : colaRojo) System.out.println("   - " + p);
        for (Paciente p : colaAmarillo) System.out.println("   - " + p);
        for (Paciente p : colaVerde) System.out.println("   - " + p);
    }

    // 🔧 Función interna
    private Paciente obtenerSiguiente(boolean remover) {
        if (!colaRojo.isEmpty()) return remover ? colaRojo.poll() : colaRojo.peek();
        if (!colaAmarillo.isEmpty()) return remover ? colaAmarillo.poll() : colaAmarillo.peek();
        if (!colaVerde.isEmpty()) return remover ? colaVerde.poll() : colaVerde.peek();
        return null;
    }

    // 💻 Main interactivo
    public static void main(String[] args) {
        TriageHospital sistema = new TriageHospital();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Ver siguiente paciente");
            System.out.println("3. Atender paciente");
            System.out.println("4. Mostrar contadores");
            System.out.println("5. Deshacer última atención");
            System.out.println("6. Mostrar reporte");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.print("Nombre del paciente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Prioridad (1=Rojo, 2=Amarillo, 3=Verde): ");
                    int prioridad = Integer.parseInt(sc.nextLine());
                    sistema.registrarPaciente(nombre, prioridad);
                }
                case "2" -> sistema.verSiguiente();
                case "3" -> sistema.atender();
                case "4" -> sistema.mostrarContadores();
                case "5" -> sistema.deshacerUltimaAtencion();
                case "6" -> sistema.reporte();
                case "0" -> {
                    System.out.println("👋 Saliendo del sistema...");
                    sc.close();
                    return;
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        }
    }
}
