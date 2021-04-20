package BloomFilter;

import java.util.Arrays;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilterPolicy bloomFilterPolicy = new BloomFilterPolicy(20);
        String[] urls = new String[]{"www.baidu.com", "www.google.com"};
        bloomFilterPolicy.createFilter(urls);
        System.out.println(bloomFilterPolicy.getBitSet());
    }
}
