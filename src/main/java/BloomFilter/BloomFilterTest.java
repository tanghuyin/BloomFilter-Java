package BloomFilter;

import java.util.Arrays;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilterPolicy bloomFilterPolicy = new BloomFilterPolicy(20);
        String[] urls = new String[]{"www.baidu.com", "www.google.com"};
        bloomFilterPolicy.createFilter(urls);
        System.out.println(bloomFilterPolicy.urlMayMatch("www.baidu.com"));
        System.out.println(bloomFilterPolicy.urlMayMatch("www.google.com"));
        System.out.println(bloomFilterPolicy.urlMayMatch("www.bytedance.com"));
    }
}
