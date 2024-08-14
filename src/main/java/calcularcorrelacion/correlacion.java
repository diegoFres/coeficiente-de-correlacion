package calcularcorrelacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class correlacion {

    public static void main(String[] args) {
        // inicializar variables
        double suma1 = 0, sum1 = 0, suma2 = 0;
        double suma1Q = 0, sum1Q = 0, suma2Q = 0;
        double suma1Y = 0, sum2Y = 0;
        int n = 0;

        String file = "C:\\Users\\diego\\Downloads\\startup-profit (1).csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea = br.readLine();
            linea = br.readLine();

            while (linea != null) {
                String[] values = linea.split(",");

                // analizar valores del csv
                double rdSpend = Double.parseDouble(values[0]);
                double marketingSpend = Double.parseDouble(values[2]);
                double profit = Double.parseDouble(values[4]);

                // sumas gasto ryd
                suma1 += rdSpend;
                sum1 += profit;
                suma1Q += rdSpend * rdSpend;
                sum1Q += profit * profit;
                suma1Y += rdSpend * profit;

                // sumas gasto de marketing
                suma2 += marketingSpend;
                suma2Q += marketingSpend * marketingSpend;
                sum2Y += marketingSpend * profit;

                n++;
                linea = br.readLine();
            }

            // calcular coeficiontes de correlacion
            double correlationRD = (n * suma1Y - suma1 * sum1) /
                    Math.sqrt((n * suma1Q - suma1 * suma1) * (n * sum1Q - sum1 * sum1));

            double correlationMarketing = (n * sum2Y - suma2 * sum1) /
                    Math.sqrt((n * suma2Q - suma2 * suma2) * (n * sum1Q - sum1 * sum1));

            // imprimir resultados
            System.out.printf("correlación entre la utilidad (profit) y la inversión en investigación y desarrollo: %.3f%n", correlationRD);
            System.out.printf("correlación entre la utilidad (profit) y la inversión en marketing: %.3f%n", correlationMarketing);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}