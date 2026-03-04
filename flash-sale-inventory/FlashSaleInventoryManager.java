import java.util.*;

public class FlashSaleInventoryManager {

    private HashMap<String, Integer> stock = new HashMap<>();
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public void checkStock(String productId) {
        System.out.println(productId + " → " + stock.get(productId) + " units available");
    }

    public synchronized void purchaseItem(String productId, int userId) {

        int currentStock = stock.get(productId);

        if (currentStock > 0) {
            stock.put(productId, currentStock - 1);
            System.out.println("Success, remaining " + (currentStock - 1));
        } else {
            waitingList.get(productId).add(userId);
            System.out.println("Added to waiting list position " + waitingList.get(productId).size());
        }
    }

    public static void main(String[] args) {

        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();

        manager.addProduct("IPHONE15", 2);

        manager.checkStock("IPHONE15");

        manager.purchaseItem("IPHONE15", 101);
        manager.purchaseItem("IPHONE15", 102);
        manager.purchaseItem("IPHONE15", 103);
    }
}