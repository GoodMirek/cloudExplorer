package cloudExplorer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static cloudExplorer.NewJFrame.jTextArea1;

public class MakeBucket implements Runnable {

    NewJFrame mainFrame;
    public static String response = null;
    public static String region = null;

    public MakeBucket(NewJFrame Frame) {
        mainFrame = Frame;

    }

    public void run() {
        try {
            final JButton createBucket = new JButton("Create Bucket");
            final JButton close = new JButton("Close");
            final JLabel blank = new JLabel(" ");
            final JLabel blank2 = new JLabel(" ");
            final JLabel blank3 = new JLabel(" ");
            final JTextField bucketName = new JTextField();
            final JTextField regionName = new JTextField(mainFrame.cred.getRegion());
            final JLabel name = new JLabel("Bucket Name:");
            final JLabel region_name = new JLabel("Region Name:");
            name.setBackground(Color.white);
            name.setForeground(Color.blue);
            region_name.setBackground(Color.white);
            region_name.setForeground(Color.blue);
            createBucket.setBackground(Color.white);
            createBucket.setForeground(Color.blue);
            createBucket.setBorder(null);
            close.setBackground(Color.white);
            close.setBorder(null);
            close.setForeground(Color.blue);

            createBucket.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (bucketName.getText().length() < 3) {
                        close.doClick();
                    } else {
                        mainFrame.jTextArea1.append("\n" + mainFrame.bucket.makeBucket(mainFrame.cred.getAccess_key(), mainFrame.cred.getSecret_key(), bucketName.getText().toLowerCase(), mainFrame.cred.getEndpoint(), regionName.getText()));
                        close.doClick();
                        mainFrame.reloadBuckets();
                    }
                }
            });

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
            mainFrame.jPanel14.add(name);
            mainFrame.jPanel14.add(bucketName);
            mainFrame.jPanel14.add(blank3);
            mainFrame.jPanel14.add(region_name);
            mainFrame.jPanel14.add(regionName);
            mainFrame.jPanel14.add(blank);
            mainFrame.jPanel14.add(createBucket);
            mainFrame.jPanel14.add(blank2);
            mainFrame.jPanel14.add(close);
            mainFrame.jPanel14.repaint();
            mainFrame.jPanel14.revalidate();
            mainFrame.jPanel14.validate();

        } catch (Exception makebucket) {
            jTextArea1.append("\n" + makebucket.getMessage());
        }
        mainFrame.calibrateTextArea();

    }

    void startc() {
        (new Thread(new MakeBucket(mainFrame))).start();
    }
}
