import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Movements {
    private final String pathMovementsCsv;


    public Movements(String pathMovementsCsv) {
        this.pathMovementsCsv = pathMovementsCsv;
    }
    //Получаем сумму расходов
    public double getExpenseSum() {
        double expense = 0.0;
        double expenseTmp = 0.0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            lines.remove(0);
            for (String line : lines) {
                String[] fragments = line.split(",");
                if (fragments.length == 8) {
                    expenseTmp = Double.parseDouble(fragments[7]);
                }
                if (fragments.length == 9 && !fragments[6].substring(0,1).contains("\"")) {
                        expenseTmp = Double.parseDouble(fragments[7].replaceAll("\"",
                                "") + "." + fragments[8].replaceAll("\"",
                                "") );
                }
                if (fragments.length == 9 && fragments[6].substring(0,1).contains("\"")){
                        expenseTmp = Double.parseDouble(fragments[8]);
                    }
               expense += expenseTmp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return expense ;
    }
    //Получаем сумму доходов
    public double getIncomeSum() {
        double income = 0.0;
        double incomeTmp = 0.0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            lines.remove(0);
            for (String line : lines) {
                String[] fragments = line.split(",");
                if (fragments.length == 8) {
                     incomeTmp = Double.parseDouble(fragments[6]);
                }
                if (fragments.length == 9 && fragments[6].substring(0,1).contains("\"")) {
                        incomeTmp = Double.parseDouble(fragments[6].replaceAll("\"",
                                "") + "." + fragments[7].replaceAll("\"",
                                ""));
                }
                if (fragments.length == 9 && !fragments[6].substring(0,1).contains("\"")){
                        incomeTmp = Double.parseDouble(fragments[6]);
                }
                income += incomeTmp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return income ;
    }
}