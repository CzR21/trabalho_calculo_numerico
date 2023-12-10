package exercicio1;

import java.util.Arrays;

public class AppMain
{
    public static void main(String[] args)
    {
        try
        {
            double[][] coeficientes = 
            {
                {2, -1, 1},
                {-3, -1, 2},
                {-2, 1, 2},
            };

            double[] respostas = {8, -11, -3};

            var respostaA = sistemsMetodoPivotamento(coeficientes, respostas);
            var normaErroA = calcularNormaErro(coeficientes, respostas, respostaA);

            System.out.println("\na) Solucão = " + Arrays.toString(respostaA));
            System.out.println("   Norma de erro = " + Arrays.toString(normaErroA) + "\n");

            var respostaB = sistemaMetodoJacobi(coeficientes, respostas, true);

            System.out.println("\n Solução = " + Arrays.toString(respostaB));
        }
        catch(Exception ex)
        {
            System.out.println("\nErro na resolução. " + ex.getMessage() + "\n");
        }
    }

    public static double[] sistemsMetodoPivotamento(double[][] coeficientes, double[] respostas ) 
    {
        try
        {
            int n = respostas.length;
            double[][] sistema = new double[n][n + 1];

            for (int i = 0; i < n; i++) 
            {
                System.arraycopy(coeficientes[i], 0, sistema[i], 0, n);
                sistema[i][n] = respostas[i];
            }

            for (int i = 0; i < n; i++)
            {
                int x = i;
                
                for (int j = i + 1; j < n; j++)
                {
                    if (Math.abs(sistema[j][i]) > Math.abs(sistema[x][i]))
                    {
                        x = j;
                    }
                }

                double[] aux = sistema[i];
                sistema[i] = sistema[x];
                sistema[x] = aux;

                for (int j = i + 1; j < n; j++)
                {
                    double factor = sistema[j][i] / sistema[i][i];

                    for (int k = i; k <= n; k++)
                    {
                        sistema[j][k] -= factor * sistema[i][k];
                    }
                }
            }

            double[] resposta = new double[n];

            for (int i = n - 1; i >= 0; i--)
            {
                double y = 0.0;

                for (int j = i + 1; j < n; j++)
                {
                    y += sistema[i][j] * resposta[j];
                }

                resposta[i] = (sistema[i][n] - y) / sistema[i][i];
            }

            return resposta;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    public static double[] sistemaMetodoJacobi(double[][] coeficientes, double[] respostas, boolean mostarErro)
    {
        try
        {
            int n = respostas.length;
            double tolerancia = 1e-10;  

            double[] atual = new double[n];
            double[] next = new double[n];

            for (int iteracao = 0; iteracao < 10; iteracao++) {
                System.arraycopy(atual, 0, next, 0, n);

                for (int i = 0; i < n; i++)
                {
                    double soma = 0.0;

                    for (int j = 0; j < n; j++)
                    {
                        if (j != i)
                        {
                            soma += coeficientes[i][j] * next[j];
                        }
                    }

                    atual[i] = (respostas[i] - soma) / coeficientes[i][i];
                }

                if(mostarErro)
                {
                    var normaErroB = calcularNormaErro(coeficientes, respostas, atual);
                    System.out.println((iteracao == 0 ? "\nb)" : "") + " Norma de erro = " + Arrays.toString(normaErroB));
                }
                

                boolean converge = verificarConvergencia(atual, next, tolerancia);
                
                if(converge)
                {
                    return atual;
                }
            }

            System.out.println("\nO método de Jacobi não convergiu.");

            return atual;
        }
        catch(Exception ex)
        {
            throw ex;
        }
        
    }

    public static double[] calcularNormaErro(double[][] coeficientes, double[] respostas, double[] solucao)
    {
        try
        {
            int n = respostas.length;
            double[] vetorErro = new double[n];

            for (int i = 0; i < n; i++)
            {
                double soma = 0.0;
                for (int j = 0; j < n; j++)
                {
                    soma += coeficientes[i][j] * solucao[j];
                }

                vetorErro[i] = respostas[i] - soma;
            }

            double[] norma = new double[vetorErro.length];

            for (int i = 0; i < vetorErro.length; i++)
            {
                norma[i] = Math.abs(vetorErro[i]);
            }

            return vetorErro;         
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    private static boolean verificarConvergencia(double[] atual, double[] anterior, double tolerancia)
    {
        for (int i = 0; i < atual.length; i++)
        {
            if (Math.abs(atual[i] - anterior[i]) > tolerancia)
            {
                return false;
            }
        }

        return true;
    }
}
