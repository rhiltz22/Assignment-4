public class LinearProbingHashST<Key, Value> {
    private int M = 20000;
    private Value[] vals = (Value[]) new Object[M];
    private Key[] keys = (Key[]) new Object[M];

    public void put(Key key, Value val, boolean useOldHash) {
        int i = useOldHash ? oldHashCode((String) key, M) : currentHashCode((String) key, M);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            i = (i + 1) % M;
        }
        keys[i] = key;
        vals[i] = val;
    }

    public Value get(Key key, boolean useOldHash) {
        int i = useOldHash ? oldHashCode((String) key, M) : currentHashCode((String) key, M);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
            i = (i + 1) % M;
        }
        return null;
    }

    public int getSearchCost(Key key, boolean useOldHash) {
        int i = useOldHash ? oldHashCode((String) key, M) : currentHashCode((String) key, M);
        int cost = 0;
        while (keys[i] != null) {
            cost++;
            if (keys[i].equals(key)) {
                break;
            }
            i = (i + 1) % M;
        }
        return cost;
    }

    public static int oldHashCode(String string, int M) {
        int hash = 0;
        int skip = Math.max(1, string.length() / 8);
        for (int i = 0; i < string.length(); i += skip) {
            hash = (hash * 37 + string.charAt(i)) % M;
        }
        return hash;
    }

    public static int currentHashCode(String string, int M) {
        int hash = 0;
        for (int i = 0; i < string.length(); i++) {
            hash = (hash * 31 + string.charAt(i)) % M;
        }
        return hash;
    }
}
