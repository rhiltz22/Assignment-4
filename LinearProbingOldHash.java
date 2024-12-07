public class LinearProbingHashST<Key, Value>
{
    private int M = 20000;
    private Value[] vals = (Value[]) new Object[M];
    private Key[] keys = (Key[]) new Object[M];

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M)
            if (key.equals(keys[i]))
                return vals[i];
        return null;
    }

    public static int oldHashCode(String string, int M) {
        int hash = 0;
        int skip = Math.max(1, string.length() / 8);
        for (int i = 0; i < string.length(); i += skip) {
            hash = (hash * 37 + string.charAt(i)) % M;
        }
        return hash;
    }
    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M)
            if (keys[i].equals(key))
                break;
        keys[i] = key;
        vals[i] = val;
    }
}
