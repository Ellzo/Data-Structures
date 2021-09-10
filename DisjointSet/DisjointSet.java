public class DisjointSet {
    private final int[] parents;
    private final int[] ranks;

    DisjointSet(int[] array) {
        parents = new int[array[array.length - 1] + 1];
        ranks = new int[array[array.length - 1] + 1];
        for (int i : array) {
            parents[i] = i;
            ranks[i] = 0;
        }
    }

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (ranks[pa] > ranks[pb]) {
            parents[pb] = pa;
        } else if (ranks[pa] < ranks[pb]) {
            parents[pa] = pb;
        } else {
            parents[pb] = pa;
            ++ranks[pa];
        }
    }

    int find(int n) {
        if (parents[n] == n) {
            return n;
        } else {
            parents[n] = find(parents[n]);
            return parents[n];
        }
    }

    int getRank(int n) {
        return ranks[n];
    }
}
