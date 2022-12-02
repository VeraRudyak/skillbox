
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(4);
        long start = System.currentTimeMillis();
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            service.submit(new NumberGenerator(regionCode));
        }
        service.shutdown();

        while (!service.isTerminated()) {
        }
        System.out.println("Время выполнения несколькими потоками - " + (System.currentTimeMillis() - start) + " ms");

    }
}
