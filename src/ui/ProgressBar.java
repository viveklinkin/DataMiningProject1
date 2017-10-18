/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import main.fptminer;

/**
 *
 * @author vivek
 */
public class ProgressBar extends JPanel implements Runnable {

    private static final int MIN = 0;
    private int max = 100;
    private JProgressBar bar;
    private static JFrame frame;

    public ProgressBar(int max) {
        this.max = max;
        bar = new JProgressBar();
        bar.setMinimum(MIN);
        bar.setMaximum(max);
        
        add(bar);
    }

    public void updateBar(int newValue) {
        bar.setValue(newValue);
    }

    public JFrame getStatusForDataSet(int max) {

        final ProgressBar iter = new ProgressBar(max);
        JFrame frame = new JFrame("Progress Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(iter);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        Thread t = new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        
        return frame;
    }

    @Override
    public void run() {
        while(fptminer.isRunning()){
            bar.setValue(fptminer.getStatus());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.err.println("Somthing up with Thread");
            }
        }
    }
}
