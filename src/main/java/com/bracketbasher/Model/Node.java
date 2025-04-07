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
}
