import java.util.ArrayList;
import java.util.List;

public class Alquimista {

    public static void main(String[] args) {
        String[] nombresPociones = {"aprobado", "honestidad", "esfuerzo", "trabajo"};

        List<Pocion> pociones = new ArrayList<>();
        List<Thread> calderos = new ArrayList<>();

        // Crear las pociones y meterlas en strings con el nombre caldero
        for (String nombre : nombresPociones) {
            Pocion p = new Pocion(nombre);
            pociones.add(p);
            calderos.add(new Thread(p, "Caldero" + nombre));
        }
        int vivosAntes = 0;
        for (Thread t : calderos) {
            if (t.isAlive()) {
                vivosAntes++;
            }
        }
        System.out.println("[Alquimista] Calderos al fuego: " + vivosAntes);
        long inicioReal = System.currentTimeMillis();
        for (Thread t : calderos) {
            t.start();
        }
        int vivosArrancados = 0;
        for (Thread t : calderos) {
            if (t.isAlive()) {
                vivosArrancados++;
            }
        }
        System.out.println("[Alquimista] Calderos al fuego: " + vivosArrancados);
        for (Thread t : calderos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("Error esperando al hilo");
            }
        }
        long finReal = System.currentTimeMillis();
        long tiempoReal = finReal - inicioReal;
        int vivosAlFinal = 0;
        for (Thread t : calderos) {
            if (t.isAlive()) {
                vivosAlFinal++;
            }
        }
        System.out.println("[Alquimista] Calderos al fuego: " + vivosAlFinal);
        long sumaTiempos = 0;
        Pocion pocionMasLenta = pociones.get(0);
        for (Pocion p : pociones) {
            sumaTiempos += p.getTiempoTotal();
            if (p.getTiempoTotal() > pocionMasLenta.getTiempoTotal()) {
                pocionMasLenta = p;
            }
        }
        System.out.println("Todas las pociones están listas.");
        System.out.println("Tiempo real: " + tiempoReal + " ms");
        System.out.println("Una detrás de otra: " + sumaTiempos + " ms");
        System.out.println("La más lenta: " + pocionMasLenta.getNombre() + " (" + pocionMasLenta.getTiempoTotal() + " ms)");
    }
}