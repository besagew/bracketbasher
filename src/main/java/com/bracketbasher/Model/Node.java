package com.bracketbasher.Model;

public class Node {
    private String data;
    private Node left;
    private Node right;

    public Node (String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public String getData(){
        return data;
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight(){
        return right;
    }

    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
}
