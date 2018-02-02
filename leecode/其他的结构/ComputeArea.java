/*

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.

Credits:
Special thanks to @mithmatt for adding this problem, creating the above image and all test cases.

*/

// 没啥技术含量，就是小学一年级的数学
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);

        if (A >= E && B >= F && C <= G && D <= H) return area2;
        if (A <= E && B <= F && C >= G && D >= H) return area1;
        if (C <= E || G<= A || B >= H || D <= F) return area1 + area2;

        int height = 0, width = 0;
        // 计算公共部分
        if (E >= A && G >= C) width = C - E;
        else if (A >= E && C <= G) width = C - A;
        else if (A >= E && C >= G) width = G - A;
        else if (E >= A && G <= C) width = G - E;

        if (B >= F && D >= H) height = H - B;
        else if (B >= F && D <= H) height = D - B;
        else if (F >= B && H <= D) height = H - F;
        else if (F >= B && H >= D) height = D - F;

        return area1 + area2 - height * width;
    }
}


