package sync;

class FileResource
{
    int x = 0;
}

class Manager implements Runnable
{
    FileResource resource;

    Manager(FileResource resource)
    {
        this.resource = resource;
    }

    @Override
    public void run() {
        synchronized (resource) {
            resource.x = 1;

            for (int i = 0; i < 5; i++) {
                System.out.printf("%s - %d\n", Thread.currentThread().getName(), resource.x);

                resource.x++;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class SyncMain {
    public static void main(String[] args) {
        FileResource resource = new FileResource();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Manager(resource), "Поток " + i);
            thread.start();
        }
    }
}
