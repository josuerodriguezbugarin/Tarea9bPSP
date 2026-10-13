public class Pocion implements Runnable {
    private String nombre;
    private int fases;
    private int tiempoFase;
    private long tiempoTotal;
    public Pocion(String nombre) { //Recibe el nombre de la pocion y calcula fases y tiempo
        this.nombre = nombre;
        this.fases = (int) (Math.random() * 5) + 4;
        this.tiempoFase = (int) (Math.random() * 201) + 200;
        this.tiempoTotal = 0;
    }
    public String getNombre() { // getters y setters de los nombres, tiempos, fases y tiempos
        return nombre;
    }
    public long getTiempoTotal() {
        return tiempoTotal;
    }
    public int getFases() {
        return fases;
    }
    public int getTiempoFase() {
        return tiempoFase;
    }
    public void run() {
        long inicio = System.currentTimeMillis();
        for (int i = 1; i <= fases; i++) {
            try {
                Thread.sleep(tiempoFase);
            } catch (InterruptedException e) {
                System.err.println("Error en la pocion " + nombre);
            }
            System.out.println("[" + nombre + "] fase" + i + "/" + fases);
        }
        long fin = System.currentTimeMillis();
        this.tiempoTotal = fin - inicio;

        System.out.println("[" + nombre + "] lista en" + tiempoTotal);
    }
}