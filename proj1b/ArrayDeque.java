public class ArrayDeque<T> implements Deque<T> {
        private T[] valArray;
        private int maxSize;
        private int size;

        public ArrayDeque()
        {
            valArray=(T[])(new Object[20]);
            maxSize=20;
            size=0;
        }

        @Override
        public boolean isEmpty()
        {
            return size == 0;
        }

        @Override
        public void addFirst(T front) {
            if(size==maxSize)
            {
                resize();
            }
            if(size==0)
            {
                valArray[0]=front;
            }
            else{
                T[] temp=(T[])new Object[maxSize];
                temp[0]=front;
                System.arraycopy(valArray,0,temp,1,size);
                valArray=temp;
            }
            size++;
        }

        @Override
        public void resize() {
            T[] temp=(T[])new Object[2*maxSize];
            System.arraycopy(valArray,0,temp,0,size);
            valArray=temp;
        }

        @Override
        public void addLast(T last) {
            if(size==maxSize)resize();
            valArray[size]=last;
            size++;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void printDeque() {
            for(int i=0;i<size;i++)
            {
                System.out.println(valArray[i]);
            }
        }

        @Override
        public void removeFirst() {
            for(int i=0;i<size-1;i++)
            {
                valArray[i]=valArray[i+1];
            }
            valArray[size-1]=null;
            size--;
        }
}
