package exercicio3;

import java.util.Random;

public class AppMain {

    public static void main(String[] args)
    {
        double[][] dados = 
        {
            {1, 0.0, 0.9958},
            {2, 0.5, 1.8814},
            {3, 0.7, 1.9778},
            {4, 1.1, 1.9948},
        };

        int n = dados.length;

        double[][] a = new double[n][n];
        double[] b = new double[n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j <= 3; j++)
            {
                a[i][j] = Math.pow(dados[i][1], j);
            }

            b[i] = dados[i][2];
        }

        double[] solucao = exercicio1.AppMain.sistemsMetodoPivotamento(a, b);

        System.out.println("\nC1 = " + solucao[0] + "   C2 = " + solucao[1] + "   C3 = " + solucao[2] + "   C4 = " + solucao[3]);

    }

}
