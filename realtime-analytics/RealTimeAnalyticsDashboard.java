import java.util.*;

public class RealTimeAnalyticsDashboard {

    HashMap<String, Integer> pageViews = new HashMap<>();
    HashMap<String, HashSet<String>> uniqueVisitors = new HashMap<>();
    HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void showDashboard() {

        System.out.println("Page Views:");
        System.out.println(pageViews);

        System.out.println("Unique Visitors:");
        System.out.println(uniqueVisitors);

        System.out.println("Traffic Sources:");
        System.out.println(trafficSources);
    }

    public static void main(String[] args) {

        RealTimeAnalyticsDashboard dashboard = new RealTimeAnalyticsDashboard();

        dashboard.processEvent("/news", "user1", "google");
        dashboard.processEvent("/news", "user2", "facebook");
        dashboard.processEvent("/sports", "user1", "direct");

        dashboard.showDashboard();
    }
}