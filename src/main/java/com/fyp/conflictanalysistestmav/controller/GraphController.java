/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fyp.conflictanalysistestmav.controller;

import com.fyp.conflictanalysistestmav.constants.NodeTypes;
import com.fyp.conflictanalysistestmav.database.DBConnection;
import com.fyp.conflictanalysistestmav.model.Graph;
import com.fyp.conflictanalysistestmav.model.Node;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Lakshan
 */
public class GraphController {
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static Connection connection;
    private static final Logger LOGGER = Logger.getLogger(GraphController.class.getName() );
    public static Graph getGraph(String disease, String id){
        
        ArrayList<Node> graphCategoryList = getGraphCategoryList(disease);
        Node rootNode = new Node(disease, id);
        rootNode.setType(NodeTypes.NODE_TYPE_DISEASE);
        Graph graph = new Graph(rootNode);
        for(Node graphCategory : graphCategoryList){
            graphCategory.setSuccessors(getSuccessors(graphCategory));
            graphCategory.setPredecessors(getPredecessors(graphCategory));
            if (graphCategory.getPredecessors().isEmpty()) {
                graphCategory.getPredecessors().add(rootNode);
            }
            graph.addNode(graphCategory);
        }
        
        return graph;
    }
    public static ArrayList<Node> getSuccessors(Node node){
        
        ArrayList<Node> successorList = new ArrayList<>();
        try 
        {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT category_id, category_name FROM successor natural join category "
                    + "where graph_category_id = ? and "
                    + "category.category_id = successor.successor_id;";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, node.getGraph_category_id());
            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Node successor = new Node();
                successor.setId(resultSet.getString("category_id"));
                successor.setName(resultSet.getString("category_name")); 
                successor.setType(NodeTypes.NODE_TYPE_CATEGORY);
                successorList.add(successor);
            }
            
        } 
        catch (SQLException | IOException | PropertyVetoException ex) 
        {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        finally 
        {
            try {
                DbUtils.closeQuietly(resultSet);
                DbUtils.closeQuietly(preparedStatement);
                DbUtils.close(connection);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        
        return successorList;
    }
    
    public static ArrayList<Node> getPredecessors(Node node){
        
        ArrayList<Node> predecessorList = new ArrayList<>();
        try 
        {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT category_id, category_name FROM predecessor natural join category "
                    + "where graph_category_id = ? and "
                    + "category.category_id = predecessor.predecessor_id;";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, node.getGraph_category_id());
            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Node predecessor = new Node();
                predecessor.setId(resultSet.getString("category_id"));
                predecessor.setName(resultSet.getString("category_name")); 
                predecessor.setType(NodeTypes.NODE_TYPE_CATEGORY);
                predecessorList.add(predecessor);
            }
            
        } 
        catch (SQLException | IOException | PropertyVetoException ex) 
        {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        finally 
        {
            try {
                DbUtils.closeQuietly(resultSet);
                DbUtils.closeQuietly(preparedStatement);
                DbUtils.close(connection);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        
        return predecessorList;
    }
    
    public static ArrayList<Node> getGraphCategoryList(String disease){
        
        ArrayList<Node> graphCategoryList = new ArrayList<>();
        try 
        {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT category_id, graph_category_id, category_name "
                    + "FROM disease natural join graph "
                    + "natural join graph_category natural join category "
                    + "where disease_name = ? and "
                    + "graph_id = disease_id and "
                    + "graph_category.category_id = category.category_id;";

            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, disease);
            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Node node = new Node();
                node.setId(resultSet.getString("category_id"));
                node.setName(resultSet.getString("category_name"));
                node.setGraph_category_id(resultSet.getString("graph_category_id"));
                node.setType(NodeTypes.NODE_TYPE_CATEGORY);
                graphCategoryList.add(node);
            }
            
        } 
        catch (SQLException | IOException | PropertyVetoException ex) 
        {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        finally 
        {
            try {
                DbUtils.closeQuietly(resultSet);
                DbUtils.closeQuietly(preparedStatement);
                DbUtils.close(connection);
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        
        return graphCategoryList;
    }
    
}
