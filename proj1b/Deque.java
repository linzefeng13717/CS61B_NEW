public interface Deque <T>{
    public boolean isEmpty();

    public void addFirst(T front);

    public void resize();

    public void addLast(T last);

    public int size();

    public void printDeque();

    public void removeFirst();
}
