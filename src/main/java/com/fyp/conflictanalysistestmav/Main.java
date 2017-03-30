/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fyp.conflictanalysistestmav;

import com.fyp.conflictanalysistestmav.controller.GraphController;
import com.fyp.conflictanalysistestmav.model.Graph;
import com.fyp.conflictanalysistestmav.view.ConflictAnalysisTest;
import java.applet.Applet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Lakshan
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
    frame.setSize(400, 300);
    Graph diabetes = GraphController.getGraph("Diabetes", "1");
    Graph hyp = GraphController.getGraph("Hypertension", "2");
    Graph copd = GraphController.getGraph("COPD", "3");
    final Applet applet = new ConflictAnalysisTest();

    frame.getContentPane().add(applet);
    frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent we) {
            applet.stop();
            applet.destroy();
            System.exit(0);
        }
    });

    frame.setVisible(true);
    applet.init();
    applet.start();
    }
}
