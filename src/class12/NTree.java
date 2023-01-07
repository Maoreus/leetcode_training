package class12;

import java.util.List;

public class NTree {
    public int val;
    public List<NTree> children;

    public NTree() {
    }

    public NTree(int _val) {
        val = _val;
    }

    public NTree(int _val, List<NTree> _children) {
        val = _val;
        children = _children;
    }
}
