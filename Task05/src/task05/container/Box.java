package task05.container;

public class Box<T>{
    private T content;

    public Box(){
        content = null;
    }
    public Box(T content){
        this.content = content;
    }

    public void put(T item){
        if(content != null){
            throw new IllegalArgumentException("Container must be emptied before it can be reused.");
        }
        content = item;
    }

    public T get(){
        if(content == null){
            throw new IllegalArgumentException("Container is empty");
        }
        return content;
    }
    public T getAndClear(){
        T item = get();
        clear();
        return item;
    }

    public boolean isEmpty(){
        if(content == null){
            return true;
        }
        return false;
    }

    public void clear(){
        content = null;
    }

    @Override
    public String toString(){
        if(isEmpty()){
            return "EMPTY";
        }
        return "Box{"+content.toString()+"}";
    }

}
