import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BankCard {
    private String ownerName;
    private CurrencyType currency;
    private long cardNumber;
    private double balance;

    public BankCard() {
    }

    public BankCard(String ownerName, CurrencyType currency, String date, long cardNumber, int balance)
            throws MinusCardNumberException, ShortOwnerNameException {
        if (ownerName.length() < 2) throw new ShortOwnerNameException(ownerName);
        this.ownerName = ownerName;
        this.currency = currency;
        if (cardNumber < 0) throw new MinusCardNumberException(cardNumber);
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) throws ShortOwnerNameException {
        if (ownerName.length() < 2) throw new ShortOwnerNameException(ownerName);
        this.ownerName = ownerName;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) throws MinusCardNumberException {
        if (cardNumber < 0) throw new MinusCardNumberException(cardNumber);
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /*public boolean getMoney(double rem) throws MinusBalanceException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму которую хотите снять: ");
        double a = scanner.nextDouble();
        if (a <= balance) {
            balance -= rem;
            System.out.println("Баланс карты: " + balance);
        } else {
            throw new MinusBalanceException(balance, rem);
        }
        return true;
    }*/
    public boolean getMoney() throws MinusBalanceException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сумму которую хотите снять: ");
        double a = scanner.nextDouble();
        if (a <= balance) {
            balance -= a;
            System.out.println("Баланс карты: " + balance);
        } else {
            throw new MinusBalanceException(balance, a);
        }
        return true;
    }

    public boolean addMoney(double sum) {
        balance += sum;
        System.out.println("Баланс карты: " + balance);
        return true;
    }

    public void createBankCard() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Введите имя владельца карты");
            try {
                setOwnerName(reader.readLine());
                break;
            } catch (ShortOwnerNameException e) {
                System.out.println(e);
            }
        }

        int currencyType = 0;
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Введите тип валюты: 1 - рубли 2 - доллары 3 - евро");
            try {
                currencyType = Integer.parseInt(reader.readLine());
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        switch (currencyType) {
            case 2:
                setCurrency(CurrencyType.DOLLAR);
                break;
            case 3:
                setCurrency(CurrencyType.EURO);
                break;
            default:
                setCurrency(CurrencyType.BYN);
        }

        while (true) {
            try {
                System.out.println("---------------------------------------");
                System.out.println("Введите номер карты:");
                setCardNumber(Long.parseLong(reader.readLine()));
                break;
            } catch (MinusCardNumberException e) {
                System.out.println(e);
            } catch (NumberFormatException eNumb) {
                eNumb.printStackTrace();
            }
        }

        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Введите баланс карты:");
            try {
                setBalance(Double.parseDouble(reader.readLine()));
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        System.out.println("---------------------------------------");
        System.out.println("Банковская карта успешно создана!");
        System.out.println("---------------------------------------");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Владелец - " + ownerName +
                ", Валюта - " + currency +
                ", номер карты - " + cardNumber +
                ", балланс: " + balance;
    }

    public enum CurrencyType {
        BYN, EURO, DOLLAR
    }

    public class MinusCardNumberException extends Exception {
        public MinusCardNumberException(long cardNumber) {
            super("Номер счёта не может быть отрицательным " + cardNumber + " ");
        }
    }

    public class ShortOwnerNameException extends Exception {
        public ShortOwnerNameException(String ownerName) {
            super("Минимальная длина имени владельца - 2 символа " + ownerName + "");
        }
    }

    public class MinusBalanceException extends Exception {
        public MinusBalanceException(double balance, double rem) {
            super("Недостаточно средств на счёте \n Баланс: " + balance + "\n Сумма: " + rem);
        }
    }
}