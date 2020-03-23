package wait_notify;

class Store
{
    private int product = 0;

    public synchronized void get()
    {
        if (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product--;
        System.out.println("Покупатель купил 1 продукт");
        System.out.println("Кол-во продуктов = " + product);
        notify();
    }

    public synchronized void put()
    {
        if (product >= 3)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product++;
        System.out.println("Производитель добавил 1 продукт");
        System.out.println("Кол-во продуктов = " + product);
        notify();
    }
}

class Producer implements Runnable
{
    Store store;
    Producer(Store store)
    {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            store.put();
        }
    }
}

class Consumer implements Runnable
{
    Store store;
    Consumer(Store store)
    {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            store.get();
        }
    }
}

public class WaitMain {
    public static void main(String[] args) {
        Store store = new Store();

        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
