package exercicio2;

import java.util.Arrays;

public class AppMain {

    public static void main(String[] args) 
    {
        try
        {
            double[][] coeficientes = 
            {
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5},
            };

            double[] respostas = {8, 12, 9};

            var respostaA = exercicio1.AppMain.sistemsMetodoPivotamento(coeficientes, respostas);

            System.out.println("\na) Solucão = " + Arrays.toString(respostaA));

            var respostaB = exercicio1.AppMain.sistemaMetodoJacobi(coeficientes, respostas, false);

            System.out.println("\nb) Solução = " + Arrays.toString(respostaB));
        }
        catch(Exception ex)
        {
            System.out.println("\nErro na resolução. " + ex.getMessage() + "\n");
        }
    }
}
