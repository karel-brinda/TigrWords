/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs.abelianComplexityGUI2;

import cz.brinda.tigrwords.WordSubDBonacci;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Karel Břinda
 */
public final class Okno {

    static KompilacniVlakno kompilacniVlakno;
    static RidiciVlakno ridiciVlakno;
    static Connection dbConnection;
    static Statement dbStatement;
    JFrame okno = new JFrame();
    GridLayout gridLayout = new GridLayout(3, 1);
    //
    // panel 1
    JPanel jPanel1 = new JPanel();
    JButton jButtonConnect = new JButton("Connect to database");
    //
    // panel 2
    JPanel jPanel2 = new JPanel();
    JLabel jLabelD = new JLabel("d = ");
    JSpinner jSpinnerD = new JSpinner(new SpinnerNumberModel(3, 2, null, 1));
    JLabel jLabelMaxN = new JLabel("Max n = ");
    JLabel jLabelThreadsCount = new JLabel("Count of threads");
    JSpinner jSpinnerThreads = new JSpinner(new SpinnerNumberModel(2, 1, null, 1));
    JButton jButtonGenerateContinue = new JButton("Continue generating");
    JButton jButtonGenerateStop = new JButton("Stop generating");
    //
    // panel 3
    JPanel jPanel3 = new JPanel();
    JLabel jLabelHTMLFrom = new JLabel("From");
    JTextField jTextFieldHTMLFrom = new JTextField("1");
    //JLabel jLabelHTMLTo = new JLabel("To");
    //JTextField jTextFieldHTMLTo = new JTextField("10000");
    JButton jButtonGenerateHTML = new JButton("Generate HTML");
    JLabel jLabelHTMLFileName = new JLabel("Name of file");
    JTextField jTextFieldHTMLFileName = new JTextField("output");

    public Okno() {
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(500, 500);
        okno.setLayout(gridLayout);

        okno.add(jPanel1, null);
        okno.add(jPanel2, null);
        okno.add(jPanel3, null);

        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black));

        jPanel1.add(jButtonConnect, null);
        jButtonConnect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                databaseConnect();
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        jPanel2.add(jLabelD, null);
        jPanel2.add(jSpinnerD, null);
        jPanel2.add(jLabelMaxN, null);
        jPanel2.add(jLabelThreadsCount, null);
        jPanel2.add(jSpinnerThreads, null);
        jPanel2.add(jButtonGenerateContinue, null);
        jButtonGenerateContinue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                zacniPocitat();
            }
        });
        jPanel2.add(jButtonGenerateStop, null);
        jButtonGenerateStop.setEnabled(false);
        jButtonGenerateStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prestanPocitat();
            }
        });

        //jPanel3.add(jLabelHTMLFrom, null);
        //jPanel3.add(jTextFieldHTMLFrom, null);
        //jPanel3.add(jLabelHTMLTo, null);
        //jPanel3.add(jTextFieldHTMLTo, null);
        jPanel3.add(jButtonGenerateHTML, null);
        jButtonGenerateHTML.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vygenerujHTML();
                } catch (IOException ex) {
                    System.out.println("Chyba generovani HTML " + ex);
                } catch (SQLException ex) {
                    System.out.println("Chyba SQL dotazu pro generovani HTML " + ex);
                }
            }
        });
        jPanel3.add(jLabelHTMLFileName, null);
        jPanel3.add(jTextFieldHTMLFileName, null);
        
        databaseDisconnected();

    }

    public void setVisible(boolean b) {
        okno.setVisible(b);
    }

    public void setTitle(String title) {
        okno.setTitle(title);
    }

    public void databaseConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception err) {
            System.out.println("chyba 1" + err);
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
        } catch (Exception err) {
            System.out.println("chyba 2" + err);
        }
        try {
            dbStatement = dbConnection.createStatement();
        } catch (Exception err) {
            System.out.println("chyba 3 " + err);
        }
        
        this.jPanel2.setVisible(true);
        this.jPanel3.setVisible(true);
    }
    
    public void databaseDisconnected() {
        this.jPanel2.setVisible(false);
        this.jPanel3.setVisible(false);
   
    }

    public int getD() {
        return (Integer) jSpinnerD.getValue();
    }

    public int getThreadsCount() {
        return (Integer) jSpinnerThreads.getValue();
    }

    public void zacniPocitat() {
        System.out.println("Vytvarim ridici vlakno");
        ridiciVlakno = new RidiciVlakno(getD(), getThreadsCount());
        ridiciVlakno.start();
        this.jButtonGenerateContinue.setEnabled(false);
        this.jButtonGenerateStop.setEnabled(true);
        this.jSpinnerD.setEnabled(false);
        this.jSpinnerThreads.setEnabled(false);
    }

    public void prestanPocitat() {
        System.out.println("Koncim ridici vlakno");
        ridiciVlakno.ukoncit();
        ridiciVlakno = null;
        this.jButtonGenerateContinue.setEnabled(true);
        this.jButtonGenerateStop.setEnabled(false);
        this.jSpinnerD.setEnabled(true);
        this.jSpinnerThreads.setEnabled(true);
    }

    public void vygenerujHTML() throws IOException, SQLException {
        String fileName = jTextFieldHTMLFileName.getText();
        String txtFileName = fileName + ".txt";
        FileWriter fw = new FileWriter(txtFileName);
        
        fw.write("#iterationLengths=" + new WordSubDBonacci(getD()).getIterationLengths()+"\n");

        synchronized (Okno.dbStatement) {
            String sqlQuery = "SELECT `n`,`pv` FROM `dbonacci` WHERE `d`='" + getD() + "' ORDER BY `n` ASC";
            ResultSet rs = Okno.dbStatement.executeQuery(sqlQuery);
            int lastN = 0;
            while (rs.next()) {
                int n = rs.getInt("n");
                if (n == lastN + 1) {
                    String pv = rs.getString("pv");
                    fw.write(pv + "\n");
                    lastN++;
                }
            }

            fw.close();
        }

        kompilacniVlakno = new KompilacniVlakno(fileName);
        kompilacniVlakno.start();
    }
}
