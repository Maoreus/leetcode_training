package class09;

/**
 * 前缀树
 */
public class TireTree {

    private Node root;
    public TireTree() {
        root = new Node();
    }


    /**
     * insert a string
     */
    public  void insert(String str) {
        if (str == null) {
            return;
        }
        char[] chars = str.toCharArray();
        Node cur = root;
        cur.pass++;
        int path = 0;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - 'a';
            //没有这条路径要加入
            if (cur.nexts[path] == null) {
                cur.nexts[path] = new Node();
            }
            //有过路径的pass+1
            cur = cur.nexts[path];
            cur.pass++;
        }
        cur.end++;
    }

    /**
     * search a string equal to
     */
    public int countWordsEqualTo(String word) {
        if (word == null) {
            return 0;
        }
        int res = 0;
        char[] chars = word.toCharArray();
        Node node = root;
        for (int i = 0; i < chars.length; i++) {
            //当前字符
            int path = chars[i] - 'a';
            if (node.nexts[path] == null) {
                return 0;
            }
            //下一个字符路径
            node = node.nexts[path];
        }
        res = node.end;
        return res;
    }

    //计算有多少个str以此为前缀
    public int countWordsStartingWith(String pre) {
        if (pre == null) {
            return 0;
        }
        int res = 0;
        Node node = root;
        char[] chars = pre.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int path = chars[i] - 'a';
            if (node.nexts[path] == null) {
                return 0;
            }
            node = node.nexts[path];
        }
        res = node.pass;
        return res;
    }
}
