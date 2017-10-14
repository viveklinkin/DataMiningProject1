package model;

/**
 * @author vaidy083
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 * This depicts a node item on the FP tree
 */
public class Node {

    //TODO: decide on how to present an item in the itemset
    private int itemId;
    private Map<Integer, Node> children;
//  private Map<Integer, Integer> support;
    private Node parent;
    private long supportCount;
    private FPTree context;

    //Constructor
    public Node(FPTree context, Node parent, int iid) {
        this.parent = parent;
        this.itemId = iid;
        this.supportCount = 1;
        this.context = context;
        children = new HashMap<>();
    }
    
    public void propagate(List<Integer> x){
        this.supportCount++;
        if(x.size() == 0){
            return;
        }
        Node currentNode = null;
        if(!children.containsKey(x.get(0))){
            currentNode = createChild(x.get(0));
        }
        else{
            currentNode = children.get(x.get(0));
        }
        x.remove(0);
        currentNode.propagate(x);
        
    }
    
    public Node createChild(int i){
        Node newNode = new Node(getContext(), this, i);
        children.put(i, newNode);
        getContext().addNode(i, newNode);
        return newNode;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long getSupportCount() {
        return supportCount;
    }

    public void setSupportCount(long supportCount) {
        this.supportCount = supportCount;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public long getCount() {
        return supportCount;
    }

    public void setCount(long count) {
        this.supportCount = count;
    }

    public Node getChild(int i1) {
        return children.get(i1);
    }
    
    public Map<Integer, Node> getChildren(){
        return this.children;
    }
    
    public FPTree getContext(){
        return this.context;
    }
}
