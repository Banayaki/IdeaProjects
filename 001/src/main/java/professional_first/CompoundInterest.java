package main.java.professional_first;

public class CompoundInterest {
    public static void main(String[] args) {
        final double STARTRATE = 10;
        final int NRATES = 6;
        final int NYEARS = 10;

        //Установить процентные ставки 10....15%
        double[] interestRate = new double[NRATES];
        for (int j = 0; j < interestRate.length; j++) {
            interestRate[j] = (STARTRATE + j) / 100.0;
        }
        double[][] balances = new double[NYEARS][NRATES];

        //установить исходные остатки на счету равными 10000
        for (int j = 0; j < balances[0].length; j++) {
            balances[0][j] = 10000;
        }

        //рассчитать проценты на следующие годы
        for (int i = 1; i < balances.length; i++) {
            for (int j = 0; j < balances[i].length; j++) {
                //получить остатки на счету за прошлый год
                double oldBalance = balances[i - 1][j];
                //расчитать проценты
                double interest = oldBalance * interestRate[j];
                //расчитать остатки на счету в текущем году
                balances[i][j] = oldBalance + interest;
            }
        }

        //вывести один ряд проценты ставок
        for (int j = 0; j < interestRate.length; j++) {
            System.out.printf("%9.0f%%", 100 * interestRate[j]);
        }
        System.out.println();
        //вывести таблицу остатков на счету
        for (double[] row : balances) {
            for (double b : row) {
                System.out.printf("%10.2f", b);
            }
            System.out.println();
        }
    }
}
