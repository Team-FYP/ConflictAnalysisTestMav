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
public class Node {
    private String name;
    private String id;
    private String graph_category_id;
    private String type;
    private ArrayList<Node> predecessors;
    private ArrayList<Node> successors;

    public Node() {
        predecessors = new ArrayList<>();
        successors = new ArrayList<>();
    }

    public Node(String name, String id) {
        predecessors = new ArrayList<>();
        successors = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    public Node(String name, String id, String type, ArrayList<Node> predecessors, ArrayList<Node> successors) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.predecessors = predecessors;
        this.successors = successors;
    }
    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Node> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(ArrayList<Node> predecessors) {
        this.predecessors = predecessors;
    }

    public ArrayList<Node> getSuccessors() {
        return successors;
    }

    public void setSuccessors(ArrayList<Node> successors) {
        this.successors = successors;
    }
    
    public void addSuccessor(Node successor){
        for(Node node  : this.successors){
            if(node.getId().equals(successor.getId())){
                return;
            }
        }
        this.successors.add(successor);
    }
    
    public void addPredecessor(Node predecessor){
        for(Node node  : this.predecessors){
            if(node.getId().equals(predecessor.getId())){
                return;
            }
        }
        this.predecessors.add(predecessor);
    }

    public String getGraph_category_id() {
        return graph_category_id;
    }

    public void setGraph_category_id(String graph_category_id) {
        this.graph_category_id = graph_category_id;
    }
    
    
       
    
    
}
