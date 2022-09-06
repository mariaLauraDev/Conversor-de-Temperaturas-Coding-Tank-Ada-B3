import com.sun.source.tree.ReturnTree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetTheTemperature {

    public static void main(String[] args) {
        try{
            int restartOption;
            Scanner sc = new Scanner(System.in);
            do {
                Initialize();
                int dimTempArray = getTempQuantity(); // dim. = dimension = dimensão
                Menu();

                int unityInput = getUnityInput();
                String initialTempSymbol = getTempSymbol(unityInput);

                int unityOutput = getUnityOutput();
                String finalTempSymbol = getTempSymbol(unityOutput);

                System.out.println("---------------------------ENTRADA DE DADOS--------------------------------");
                double[] initialTempArray = getTempInitial(dimTempArray, initialTempSymbol);
                double[] finalTempArray = new double[dimTempArray];

                switch (unityOutput) {
                    case 1:
                        for (int i = 0; i < dimTempArray; i++) {
                            finalTempArray[i] = toCelsiusTransform(unityInput, initialTempArray[i]);
                        }
                        break;
                    case 2:
                        for (int i = 0; i < dimTempArray; i++) {
                            finalTempArray[i] = toFahrenheitTransform(unityInput, initialTempArray[i]);
                        }
                        break;
                    case 3:
                        for (int i = 0; i < dimTempArray; i++) {
                            finalTempArray[i] = toKelvinTransform(unityInput, initialTempArray[i]);
                        }
                        break;
                }

                System.out.println("Temperatura de origem -> Temperatura tranformada");

                for (int i = 0; i < dimTempArray; i++) {
                    System.out.printf("%.2f %s -> %.2f %s \n", initialTempArray[i], initialTempSymbol, finalTempArray[i], finalTempSymbol);
                }

                System.out.printf("Média da (s) temperatura (s) de origem: %.2f %s \n", CalculateAverage(initialTempArray, dimTempArray), initialTempSymbol);
                System.out.printf("Média da (s) temperatura (s) tranformada (s): %.2f %s", CalculateAverage(finalTempArray, dimTempArray), finalTempSymbol);

                menuRestart();
                restartOption = restart();
            } while (restartOption != 0);

            System.out.println(" ------------------------------------------------------------------------- ");
            System.out.println("|                         Até a próxima! xD                               |");
            System.out.println("--------------------------------FIM----------------------------------------");

            sc.close();
        } catch (InputMismatchException e){
            System.out.println("Ops! Aconteceu um erro grave. Perdão pelo transtorno. Reinicie o programa!");
        }


    }

    private static void Initialize() {
        System.out.println("--------------------Bem-vindo(a) ao GetTheTemperature--------------------- ");
        System.out.println("| Neste programa, você poderá transformar temperaturas nas unidades de    |");
        System.out.println("| Celsius, Fahrenheit ou Kelvin sem se preocupar com fórmulas matemáticas!|");
        System.out.println("| Nós da GetTheTemperature fazemos esse trabalho pesado para você xD!     |");
        System.out.println(" ------------------------------------------------------------------------- ");
        System.out.println("Para iniciar, ");
    }

    private static int getTempQuantity() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite a quantidade de temperaturas a serem transformadas:");
            int dimension = sc.nextInt();

            if (dimension==0){
                System.err.println("Ops! A dimensão deve ser diferente de zero. Tente novamente.\n ");
                return getTempQuantity();
            } else {
                return dimension;
            }

        } catch (InputMismatchException e) {
            System.err.println("A quantidade foi digitada incorretamente, ela deve ser um número inteiro. \nEx.: Se desejo 1 temperatura, digito *1*, sem casas decimais.\nTente novamente!");
            return getTempQuantity();
        }
    }

    private static void Menu() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Agora, conforme a Tabela a seguir, escolha a opção numérica correspondente ");
        System.out.println("a unidade de origem da(s) temperatura(s) e a unidade a ser transformada.   ");
        System.out.println(" ------------------------------------------------------------------------- ");
        System.out.println("|             Opções              |         Unidade                       |");
        System.out.println("|---------------------------------|---------------------------------------|");
        System.out.println("|               1                 |         Celsius (ºC)                  |");
        System.out.println("|               2                 |         Fahrenheit (°F)               |");
        System.out.println("|               3                 |         Kelvin (K)                    |");
        System.out.println(" ------------------------------------------------------------------------- ");
    }

    private static int getUnityInput(){
        try{
            System.out.printf("Digite a opção da unidade de origem: \n");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            if (option<1||option>3){
                System.err.println("*ATENÇÃO:* Opção inexistente! Temte novamente!\n");
                return getUnityInput();
            } else{
                return option;
            }
        } catch (InputMismatchException e) {
            System.err.println("A opção foi digitada em um formato inválido, ele deve corresponder a \num número inteiro. Tente novamente! \nEx.: Se desejo opção 1, digito *1*, sem casas decimais. ");
            return getUnityInput();
        }

    }
    private static int getUnityOutput(){
        try{
            System.out.printf("Digite a opção da unidade a ser transformada:\n");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            if (option<1||option>3){
                System.err.println("*ATENÇÃO:* Opção inexistente! Temte novamente!\n");
                return getUnityOutput();
            } else{
                return option;
            }
        } catch (InputMismatchException e) {
            System.err.println("A opção foi digitada em um formato inválido, ele deve corresponder a \num número inteiro. Tente novamente!");
            return getUnityOutput();
        }

    }

    public static String getTempSymbol(int tempOption) {
        String symbol = new String();
        switch (tempOption) {
            case 1:
                symbol = "ºC";
                break;
            case 2:
                symbol = "ºF";
                break;
            case 3:
                symbol = "K";
                break;
        }
        return symbol;
    }

    private static double[] getTempInitial(int dim, String initialTempSymbol) {
        try{
            double[] tempArray = new double[dim];
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < dim; i++) {
                System.out.printf("Digite a %dº temperatura em %s: \n", i + 1, initialTempSymbol);
                tempArray[i] = sc.nextDouble();
            }
            System.out.println("-------------------------------RESULTADOS----------------------------------");
            return tempArray;
        } catch (InputMismatchException e) {
            System.err.println("A última opção digitada estava em um formato inválido, ele deve corresponder \na uma temperatura inteiro ou real (com separador decimal vírgula ',').\nTente novamente!");
            System.out.println("------------------------NOVA ENTRADA DE DADOS------------------------------");
            return getTempInitial(dim, initialTempSymbol);
        }

    }

    public static double toCelsiusTransform(int unityInput, double temp) {
        if (unityInput == 2) {
            return (temp - 32.0) / 1.8;
        } else if (unityInput == 3) {
            return temp - 273.15;
        } else {
            return temp;
        }
    }

    public static double toFahrenheitTransform(int unityInput, double temp) {
        if (unityInput == 1) {
            return (temp *(9.0/5.0)) +32.0;
        } else if (unityInput == 3) {
            return (temp-273.15)*(9.0/5.0)+32.0;
        } else {
            return temp;
        }
    }

    public static double toKelvinTransform(int unityInput, double temp) {
        if (unityInput == 1) {
            return temp + 273.15;
        } else if (unityInput == 2) {
            return (temp-32)*(5.0/9)+273.15;
        } else {
            return temp;
        }
    }

    private static double CalculateAverage(double[] array, int dim){
        // Media = (a1 + a2+ ... + an)/ n = (a1/n)+(a2/n)+...+ (an/n)
        double average=0.0;
        for (int i = 0; i < dim; i++) {
            average = average + array[i]/dim;
        }
        return average;
    }

    public static void menuRestart(){
        System.out.println("\n---------------------------------------------------------------------------");
        System.out.println("Deseja converter novas temperaturas?");
        System.out.println(" ------------------------------------------------------------------------- ");
        System.out.println("|             Opções           |  Ação                                    |");
        System.out.println("|------------------------------|------------------------------------------|");
        System.out.println("|               1              |  Sim! Reiniciar #GetTheTemperature xD    |");
        System.out.println("|               0              |  Não. Encerrar o programa :(             |");
        System.out.println(" ------------------------------------------------------------------------- ");
    }
    private static int restart() {

        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite a opção: ");
            int option = sc.nextInt();
            if (option<0||option>1){
                System.err.println("*ATENÇÃO:* Opção inexistente! Temte novamente!\n");
                return restart();
            } else{
                return option;
            }
        } catch (InputMismatchException e) {
            System.err.println("A opção foi digitada em um formato inválido, ele deve corresponder a \num número inteiro. Tente novamente!");
            return restart();
        }
    }
}