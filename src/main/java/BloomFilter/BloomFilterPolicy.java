package BloomFilter;

import org.apache.commons.codec.digest.MurmurHash2;

import java.util.BitSet;

public class BloomFilterPolicy {
    private int bitsPerKey;
    private BitSet bitSet;
    /**
     * k means the k hash functions, however, we don't use k hash function here, we use double hash to imitate
     * e.g
     * newHash(x) = hash1(x) + i * hash2(x)
     * hash2(x) = hash1(x) >> 17 | hash1(x) << 15
     */
    private int k;
    public BloomFilterPolicy(int bitsPerKey) {
        this.bitsPerKey = bitsPerKey;
        this.k = (int)(bitsPerKey * 0.69);
        if (this.k < 1) this.k = 1;
        if (this.k > 30) this.k = 30;
    }

    public String getBitSet() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public void createFilter(String[] urls) {
        int n = urls.length;
        int bits = n * bitsPerKey;
        if (bits < 64) bits = 64;

        int bytes = (bits + 7) / 8;
        bits = bytes * 8;
        bitSet = new BitSet(bits);

        for (String url : urls) {
            int h = hashHelper(url);
            int delta = (h >> 17) | (h << 15);
            for (int j = 0; j < k; j++) {
                int bitPos = h % bits;
                bitSet.set(bitPos);
                h = getPositiveValue(h + delta); // the factor before hash2 increase
            }
        }
    }

    public boolean urlMayMatch(String url) {
        int bits = bitSet.size(); // length is not correct, length is the Most Significant 1's position plus 1
        if (bits < 2) return false; // special check?

        int h = hashHelper(url);
        int delta = (h >> 17) | (h << 15);
        // k hash function check
        for (int i = 0; i < k; i++) {
            int bitPos = h % bits;
            if (!bitSet.get(bitPos)) {
                return false;
            }
            h = getPositiveValue(h + delta);
        }
        return true;
    }

    /**
     * @description to hash the url with murmurhash function
     * @param url url to be hashed
     * @return hash result of the url, always positive
     */
    private int hashHelper(String url) {
        int hashed = MurmurHash2.hash32(url);
        return getPositiveValue(hashed);
    }

    private int getPositiveValue(int value) {
        return value < 0 ? Integer.MAX_VALUE + value : value;
    }
}
