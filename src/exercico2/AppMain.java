package exercico2;

public class AppMain {

    public static void main(String[] args)
    {
        double[][] dados =
        {
            {1, 0.0, 0.9958},
            {2, 0.5, 1.8814},
            {3, 0.7, 1.9778},
            {4, 1.1, 1.9948}
        };

        int n = dados.length;

        double[][] a = new double[n][4];
        double[] b = new double[n];

        for (int i = 0; i < n; i++)
        {
            a[i][0] = 1;
            a[i][1] = dados[i][1];
            a[i][2] = Math.pow(dados[i][1], 2);
            a[i][3] = Math.pow(dados[i][1], 3);
            b[i] = dados[i][2];
        }

        double[] solucao = exercicio1.AppMain.sistemsMetodoPivotamento(a, b);

        System.out.println("\na) C0 = " + solucao[0] + "   C1 = " + solucao[1] + "   C2 = " + solucao[2] + "   C3 = " + solucao[3]);

        double desvio = calcularDesvio(dados, solucao);
        System.out.println("\nb) Desvio Quadrático = " + desvio + "\n");
    }

    public static double calcularDesvio(double[][] dados, double[] coeficientes)
    {
        int n = dados.length;
        double desvio = 0.0;

        for (int i = 0; i < n; i++)
        {
            double xi = dados[i][1];
            double yi = dados[i][2];

            double pxi = coeficientes[0] + coeficientes[1] * xi + coeficientes[2] * Math.pow(xi, 2) + coeficientes[3] * Math.pow(xi, 3);

            desvio += Math.pow(yi - pxi, 2);
        }

        return desvio;
    }
}
