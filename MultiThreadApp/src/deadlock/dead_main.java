package deadlock;

class Account
{
    private int balance;
    private int number;

    public Account(int balance, int number) {
        this.balance = balance;
        this.number = number;
    }

    public void put(int sum)
    {
        balance += sum;
    }

    public void get(int sum)
    {
        if (sum > balance)
            throw new IllegalArgumentException();

        balance -= sum;
    }

    public int getBalance() {
        return balance;
    }

    public int getNumber() {
        return number;
    }
}

class Transaction implements Runnable
{
    Account fromAccount, toAccount;
    Object lock = new Object();
    int sum;

    public Transaction(Account fromAccount, Account toAccount, int amount)
    {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        sum = amount;
    }

    @Override
    public void run() {
        Account first, second;
        if (fromAccount.getNumber() < toAccount.getNumber())
        {
            first = fromAccount;
            second = toAccount;
        }
        else
        {
            first = toAccount;
            second = fromAccount;
        }

        synchronized (first)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (second)
            {
                fromAccount.get(sum);
                toAccount.put(sum);
            }
        }
    }
}

public class dead_main {
    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(1000, 2);
        Account account2 = new Account(900, 5);

        Thread thread1 = new Thread(new Transaction(account1, account2, 100));
        Thread thread2 = new Thread(new Transaction(account2, account1, 100));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
    }
}
