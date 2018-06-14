/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs.abelianComplexityGUI2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Karel Břinda
 */
public class RidiciVlakno extends Thread {

    static LinkedList<String> frontaPozadavku;
    static LinkedList<Integer> plan;
    ArrayList<VypocetniVlakno> vypocetniVlakna;
    int d;
    boolean bezetDal = true;

    public RidiciVlakno(int d, int pocetVlaken) {
        super();
        this.d = d;
        RidiciVlakno.plan = new LinkedList<Integer>();
        frontaPozadavku = new LinkedList<String>();
        vypocetniVlakna = new ArrayList<VypocetniVlakno>();
        for (int i = 0; i < pocetVlaken; i++) {
            vypocetniVlakna.add(new VypocetniVlakno(d, null));
        }
    }

    @Override
    public void run() {
        while (bezetDal == true) {
            System.out.println("Ridici vlakno - pravidelna udrzba ");
            System.out.println("pocet vypocetnich vlaken" + vypocetniVlakna.size());
            for (int i = 0; i < vypocetniVlakna.size(); i++) {
                if (vypocetniVlakna.get(i) == null || !vypocetniVlakna.get(i).isAlive()) {
                    LinkedList<Integer> list = new LinkedList<Integer>();

                    for (int ii = 1; ii < 100; ii++) {
                        list.add(ii);
                    }

                    VypocetniVlakno noveVlakno;
                    try {
                        noveVlakno = new VypocetniVlakno(d, getPlan());
                        noveVlakno.start();
                        vypocetniVlakna.set(i, noveVlakno);
                    } catch (SQLException ex) {
                        System.out.println("Chyba sql " + ex);
                    }
                    System.out.println("start noveho vlakna");
                }
            }

            System.out.println("faze2");

            synchronized (frontaPozadavku) {
                if (frontaPozadavku.size() != 0) {
                    synchronized (Okno.dbStatement) {
                        for (String query : frontaPozadavku) {
                            try {
                                //System.out.println("spoustim " + query);
                                Okno.dbStatement.execute(query);
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                    frontaPozadavku.clear();

                }
            }
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Chyba cekani " + ex);
            }
            yield();
        }
        for (VypocetniVlakno vypocetniVlakno : vypocetniVlakna) {
            vypocetniVlakno.ukoncit();
        }
    }

    static public void vlozDoDatabaze(int d, int n, String parikhVectors) {
        String sqlQuery = "INSERT INTO `dbonacci` VALUES (''," + d + "," + n + ",'" + parikhVectors + "')";
        synchronized (frontaPozadavku) {
            frontaPozadavku.add(sqlQuery);
        }
    }

    public void ukoncit() {
        bezetDal = false;
    }

    private void provedPlanovani() throws SQLException {
        if (plan.size() == 0) {
            TreeSet<Integer> oldNs = new TreeSet<Integer>();
            TreeSet<Integer> newNs = new TreeSet<Integer>();

            synchronized (Okno.dbStatement) {
                String sqlQuery = "SELECT `n` FROM `dbonacci` WHERE `d`='" + d + "' ORDER BY `n` ASC";
                ResultSet rs = Okno.dbStatement.executeQuery(sqlQuery);
                while (rs.next()) {
                    int n = rs.getInt("n");
                    oldNs.add(n);
                }
                oldNs.add(0); // to be non-empty
            }

            int maxN = oldNs.last();
            for (int i = 1; i < maxN + 1000; i++) {
                newNs.add(i);
            }

            newNs.removeAll(oldNs);
            RidiciVlakno.plan = new LinkedList<Integer>(newNs);
        }
    }

    private List<Integer> getPlan() throws SQLException {
        if (plan.size() == 0) {
            provedPlanovani();
        }

        LinkedList<Integer> planForAThread = new LinkedList<Integer>();
        for (int i = 0; i < 100; i++) {
            if (plan.size() != 0) {
                planForAThread.add(plan.removeFirst());
            }
        }

        return planForAThread;
    }
}
