import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String DATA_FILE = "src/test/resources/movementList.csv";
    public static void main(String[] args) throws IOException {
        Movements movements = new Movements(DATA_FILE);

        System.out.println("Сумма расходов: " + movements.getExpenseSum() + " руб.");
        System.out.println("Сумма доходов: " + movements.getIncomeSum() + " руб." + "\n");

        Path path = Paths.get(DATA_FILE);
        List<Operation> operations = parseDataFromFile(path);
        System.out.println("Суммы расходов по организациям:" + "\n");

        for (String operation : getAllOperationtsWithSymbol(operations)) {
            System.out.print(operation + "      ");
            double sumEveryUniqueOperation = operations.stream()
                    .filter(s -> s.getDescription().indexOf(operation) > -1)
                    .mapToDouble(s -> s.getExpense()).sum();
            System.out.println(sumEveryUniqueOperation + " руб.");
        }
    }

    private static List<Operation> parseDataFromFile(Path path) {
        List<Operation> operations = new ArrayList<>();
        try {
            List<String> data = Files.readAllLines(path);
            data.remove(0);
            for (String line : data) {
                String[] elements = line.split(",");
                if (elements.length == 8) {
                    operations.add(new Operation(elements[5], Double.parseDouble(elements[6]), Double.parseDouble(elements[7])));
                }
                if (elements.length == 9 && elements[6].substring(0,1).contains("\"")) {
                    operations.add(new Operation(elements[5], Double.parseDouble(elements[6].replaceAll("\"",
                                    "") + "." + elements[7].replaceAll("\"", "")),
                            Double.parseDouble(elements[8])));
                }
                if (elements.length == 9 && !elements[6].substring(0,1).contains("\"")){
                    operations.add(new Operation(elements[5], Double.parseDouble(elements[6]),
                            Double.parseDouble(elements[7].replaceAll("\"",
                                    "") + "." + elements[8].replaceAll("\"", ""))));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return operations;
    }

    private static List<String> getAllOperationtsWithSymbol(List<Operation> operations) {

        List<String> opers = operations.stream()
                .map(s -> s.getDescription().trim().split(" {4,}"))
                .filter(s -> s[1].contains("\\"))
                .map(s ->  s[1].substring(s[1].indexOf("\\")).replaceAll("(?<!\\\\)\\\\(?!\\\\)", " "))
                .distinct().collect(Collectors.toList());
        List<String> uniqueOperations = new ArrayList<>();
        uniqueOperations.addAll(opers);
        return uniqueOperations;
    }
}
