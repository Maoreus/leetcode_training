package class12;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 * 此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 *
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 */
public class PaperFold {

    public void paperFold(int N) {
        process(1, N, true);
    }

    //中序遍历凹凸
    private void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        //左孩子是凹
        process(i + 1, n, true);
        System.out.println(down ? "凹" : "凸");
        //右孩子是凸
        process(i + 1, n, false);
    }
}
