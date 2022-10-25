package byog.Core;

import org.junit.Test;

import java.util.StringTokenizer;

public class Index{
    private int x;
    private int y;
    private Index parent;

    public Index()
    {
        parent=new Index(-1,-1);
        x=0;
        y=0;
        parent.parent=null;

    }

    Index(int xX,int yY)
    {
        parent=new Index(-1,-1);
        x=xX;
        y=yY;
        parent.parent=null;
    }

    public static boolean Equals(Index p,Index q)
    {
        return p.x==q.x&&p.y==q.y;
    }


    public static void connect(Index p, Index q) {
        Index pRoot=findRoot(p);
        int pWeight=-pRoot.parent.x;
        Index qRoot=findRoot(q);
        int qWeight=-qRoot.parent.x;
        if(qWeight>=pWeight)
        {
            pRoot.parent=qRoot;
            qRoot.x=qRoot.y=-(pWeight+qWeight);
        }
        else{
            qRoot.parent=pRoot;
            pRoot.x=pRoot.y=-(pWeight+qWeight);
        }
    }

    private static Index findRoot(Index p) {
        Index root=p;
        while(root.parent.parent!=null)
        {
            root=root.parent;
        }
        Index bottom=p;
        Index upper=p;
        while(upper!=root)
        {
            upper=upper.parent;
            bottom.parent=root;
            bottom=upper;
        }
        return root;
    }

    public static boolean isConnected(Index p, Index q) {
        return Equals(findRoot(q),findRoot(p));
    }

    @Test
    public void test()
    {
        Index i1=new Index(1,2);
        Index i2=new Index(2,3);
        Index i3=new Index(3,4);
        Index i4=new Index(4,5);
        Index.connect(i1,i2);
        Index.connect(i3,i4);
        System.out.println(Index.isConnected(i1,i2));
        System.out.println(Index.isConnected(i1,i3));
        Index.connect(i1,i4);
        System.out.println(Index.isConnected(i1,i3));
    }
}
