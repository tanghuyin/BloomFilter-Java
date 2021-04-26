package BloomFilter;

import java.util.List;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilterPolicy bloomFilterPolicy = new BloomFilterPolicy(20);
        List<String> blacklists = FileReader.readFile("src/main/resources/blackweb.txt");
        bloomFilterPolicy.createFilter(blacklists);
        System.out.println(bloomFilterPolicy.urlMayMatch("www.baidu.com"));
        System.out.println(bloomFilterPolicy.urlMayMatch("www.google.com"));
        System.out.println(bloomFilterPolicy.urlMayMatch("www.bytedance.com"));
        System.out.println(bloomFilterPolicy.urlMayMatch(".0000000000000000000000.com"));
    }
}
