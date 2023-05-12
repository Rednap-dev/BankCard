import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BankCard myCard = new BankCard();
        myCard.createBankCard();
        System.out.println();
        System.out.println("Выберите операцию которую хотите совершить:");
        System.out.println("1 - Снять деньги");
        System.out.println("2 - Положить деньги");
        System.out.println("3 - Выход");
        System.out.println("---------------------------------------");
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        if (c == 1) {
            myCard.getMoney();
        } else if (c == 2) {
            System.out.println("Введите сумму которую хотите положить: ");
            myCard.addMoney((scanner.nextDouble()));
        }
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        switch (Enum){
            case 1:myCard.getMoney(Double.parseDouble(reader.readLine()));
            case 2:myCard.addMoney(Double.parseDouble(reader.readLine()));
            case 3:break;
        }*/
/*        while (true) {

            try {
                myCard.getMoney(Double.parseDouble(reader.readLine()));
                break;
            } catch (BankCard.MinusBalanceException | NumberFormatException e) {
                System.out.println(e);
            }
        }*/

    }
}