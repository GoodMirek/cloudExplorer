/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.package
 * cloudExplorer
 *
 */
package cloudExplorer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static cloudExplorer.NewJFrame.jTextArea1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GetPerformance implements Runnable {

    NewJFrame mainFrame;
    Put put;
    Get get;
    GetPerformance getperformance;
    GetPerformanceThread getperformancethread;
//    GetPerformanceThread performancethread;
    String Home = System.getProperty("user.home");
    String output_log = Home + File.separator + "performance_results.csv";

    public GetPerformance(NewJFrame Frame) {
        mainFrame = Frame;

    }

    public void performance_logger(float time, float rate) {
        try {
            FileWriter frr = new FileWriter(output_log, true);
            BufferedWriter bfrr = new BufferedWriter(frr);
            bfrr.write("\n" + time + "," + rate);
            bfrr.close();
        } catch (Exception perf_logger) {
        }
    }

    public void run() {
        try {

            final JButton startPerformanceTest = new JButton("Start Test");
            //final JButton abortPerformanceTest = new JButton("Abort");
            final JButton close = new JButton("Close");
            final JLabel fileSize = new JLabel("File Size in KB: ");
            final JLabel threadCount = new JLabel("Thread Count:");
            final JLabel operationCount = new JLabel("Operation Count:");
            final JLabel blank = new JLabel(" ");;
            final JTextField getFileSize = new JTextField();
            final JTextField getTheadCount = new JTextField("5");
            final JTextField getOperationCount = new JTextField("5");
            getFileSize.setText("1024");

            startPerformanceTest.setBackground(Color.white);
            startPerformanceTest.setForeground(Color.blue);
            //abortPerformanceTest.setBackground(Color.white);
            //abortPerformanceTest.setForeground(Color.blue);
            //abortPerformanceTest.setBorder(null);
            startPerformanceTest.setBorder(null);

            close.setBackground(Color.white);
            close.setBorder(null);
            close.setForeground(Color.blue);

            close.setIcon(mainFrame.genericEngine);
            //abortPerformanceTest.setIcon(mainFrame.genericEngine);
            startPerformanceTest.setIcon(mainFrame.genericEngine);

            startPerformanceTest.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    mainFrame.jTextArea1.append("\nStarting test. Please wait.");
                    mainFrame.calibrateTextArea();

                    int threadcount = Integer.parseInt(getTheadCount.getText());
                    String getValue = getFileSize.getText();
                    String operationCount = getOperationCount.getText();
                    getperformancethread = new GetPerformanceThread(threadcount, getValue, operationCount, mainFrame.cred.getAccess_key(), mainFrame.cred.getSecret_key(), mainFrame.cred.getBucket(), mainFrame.cred.getEndpoint());
                    getperformancethread.startc(threadcount, getValue, operationCount, mainFrame.cred.getAccess_key(), mainFrame.cred.getSecret_key(), mainFrame.cred.getBucket(), mainFrame.cred.getEndpoint());
                    close.doClick();
                }
            });

            /**
             * abortPerformanceTest.addActionListener(new ActionListener() {
             *
             * public void actionPerformed(ActionEvent e) {
             *
             * }
             * });
             *
             */
            close.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    mainFrame.jPanel14.removeAll();
                    mainFrame.jPanel14.repaint();
                    mainFrame.jPanel14.revalidate();
                    mainFrame.jPanel14.validate();
                }
            });

            mainFrame.jPanel14.removeAll();
            mainFrame.jPanel14.setLayout(new BoxLayout(mainFrame.jPanel14, BoxLayout.Y_AXIS));
            mainFrame.jPanel14.add(fileSize);
            mainFrame.jPanel14.add(getFileSize);
            mainFrame.jPanel14.add(threadCount);
            mainFrame.jPanel14.add(getTheadCount);
            mainFrame.jPanel14.add(operationCount);
            mainFrame.jPanel14.add(getOperationCount);
            mainFrame.jPanel14.add(blank);
            mainFrame.jPanel14.add(startPerformanceTest);
            // mainFrame.jPanel14.add(abortPerformanceTest);
            mainFrame.jPanel14.add(close);
            mainFrame.jPanel14.repaint();
            mainFrame.jPanel14.revalidate();
            mainFrame.jPanel14.validate();

        } catch (Exception mp3player) {
            jTextArea1.append("\n" + mp3player.getMessage());
        }
        mainFrame.calibrateTextArea();

    }

    public void calibrate() {
        try {
            jTextArea1.setCaretPosition(jTextArea1.getLineStartOffset(jTextArea1.getLineCount() - 1));
        } catch (Exception e) {
        }
    }

    void startc() {
        (new Thread(new GetPerformance(mainFrame))).start();
    }
}
