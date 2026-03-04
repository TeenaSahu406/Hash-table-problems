import java.util.*;

class DNSEntry {

    String ip;
    long expiryTime;

    DNSEntry(String ip, int ttl) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttl * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCacheSystem {

    private HashMap<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                System.out.println("Cache HIT → " + entry.ip);
                return entry.ip;
            }

            cache.remove(domain);
        }

        String newIP = "192.168.1." + new Random().nextInt(255);

        cache.put(domain, new DNSEntry(newIP, 5));

        System.out.println("Cache MISS → " + newIP);

        return newIP;
    }

    public static void main(String[] args) throws Exception {

        DNSCacheSystem dns = new DNSCacheSystem();

        dns.resolve("google.com");
        dns.resolve("google.com");

        Thread.sleep(6000);

        dns.resolve("google.com");
    }
}