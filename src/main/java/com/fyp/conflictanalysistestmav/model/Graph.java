/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fyp.conflictanalysistestmav.model;

import java.util.ArrayList;

/**
 *
 * @author Lakshan
 */
public class Graph {
    private Node root;
    private int size;

    public Graph() {
    }

    public Graph(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public Node searchNode(Node root, String id, String type){
        Node node = null;
        if (root.getId().equals(id) && root.getType().equals(type)) {
            return root;
        }
        for( Node successor : root.getSuccessors()) {
            Node leaf = searchNode(successor, id, type);
            if(leaf != null) {
                return leaf;
            }            
        }
        return node;
    }
    
    public void addNode(Node node){
        if (this.root == null) {
            this.root = node;
        }
        ArrayList<Node> newPredecesorList = new ArrayList<>();
        for(Node predesessor : node.getPredecessors()) {
            Node grapg_node = searchNode(this.root, predesessor.getId(), predesessor.getType());
            if (grapg_node != null) {
                grapg_node.addSuccessor(node);
                newPredecesorList.add(grapg_node);
            }
        }
        node.setPredecessors(newPredecesorList);
        
        ArrayList<Node> newSuccessorList = new ArrayList<>();
        for(Node successor : node.getSuccessors()) {
            Node grapg_node = searchNode(this.root, successor.getId(), successor.getType());
            if (grapg_node != null) {
                grapg_node.addPredecessor(node);
                newSuccessorList.add(grapg_node);
            }
        }
        node.setSuccessors(newSuccessorList);
    }
}
