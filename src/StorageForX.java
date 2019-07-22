public class StorageForX {
    public int x = 0;

    public static void main(String[] args) throws InterruptedException {
        // Создаем объект с ресурсом (с переменной x)
        StorageForX storage = new StorageForX();
        System.out.println("x=" + storage.x);

        // Запускаем два потока - они будут увеличивать на единичку переменную storage.x
        System.out.println("Starting 2 threads...");
        IncrementerThread thread1 = new IncrementerThread(storage);
        IncrementerThread thread2 = new IncrementerThread(storage);
        IncrementerThread thread3 = new IncrementerThread(storage);
        IncrementerThread thread4 = new IncrementerThread(storage);
        IncrementerThread thread5 = new IncrementerThread(storage);
        IncrementerThread thread6 = new IncrementerThread(storage);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        // Дожидаемся когда оба потока закончат свое выполнение
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        System.out.println("Finished!");

        // Каждый поток увеличивает переменную на единичку 10000 раз, т.о. в результате мы бы ожидале storage.x == 20000
        System.out.println("x=" + storage.x);
    }
}

class IncrementerThread extends Thread {
    private StorageForX storage;

    public IncrementerThread(StorageForX storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        synchronized (storage) {
            System.out.println("IncrementerThread starts running...");
            for (int i = 0; i < 100000000; ++i) {
                this.storage.x += 1;
            }
            System.out.println("IncrementerThread finished!");
        }
    }
}