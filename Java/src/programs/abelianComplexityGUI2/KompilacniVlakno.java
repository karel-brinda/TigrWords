/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programs.abelianComplexityGUI2;

import java.io.IOException;

/**
 *
 * @author Karel Břinda
 */
public class KompilacniVlakno extends Thread {

    String nameOfFile;

    public KompilacniVlakno(String nameOfFile) {
        super();
        this.nameOfFile = nameOfFile;
    }

    @Override
    public void run() {
        try {
            Runtime run = Runtime.getRuntime();
            Process process;
//            String[] cmd1 = {"cmd", "/c", "start", "app\\python.exe", "vygeneruj_html.py", nameOfFile + ".txt", nameOfFile + ".html"};
//            String[] cmd2 = {"cmd", "/c", "start", nameOfFile + ".html"};
            
            String[] cmd1 = {"cmd", "/c", "start", "export_txt.bat", nameOfFile + ".txt", nameOfFile + ".html"};
            
            process = run.exec(cmd1);
            process.waitFor();

//            process = run.exec(cmd2);
//            process.waitFor();
        } catch (IOException ex) {
            System.out.println("Chyba CMD " + ex);;
        } catch (InterruptedException ex) {
            System.out.println("Chyba CMD " + ex);;
        }
    }
}
