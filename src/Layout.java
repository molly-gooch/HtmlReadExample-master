import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Layout {
        private JFrame mainFrame;
        private JLabel statusLabel;
        private JLabel statusLabel2;
        private JLabel statusLabel3;
        private JPanel controlPanel1;
        private JPanel controlPanel2;
        private JPanel controlPanel3;
    private JPanel controlPanel4;
    private JScrollPane scroll;
        private JTextArea ta1;
        private JTextArea ta2;
        private JTextArea ta3;
        private int WIDTH=800;
        private int HEIGHT=700;
        public int errorCheck;
        


        public Layout() {
            prepareGUI();
        }

        public static void main(String[] args) {
            Layout layout = new Layout();
            layout.showEventDemo();
        }

        private void prepareGUI() {
            mainFrame = new JFrame("Html Reader");
            mainFrame.setSize(WIDTH, HEIGHT);
            mainFrame.setLayout(new GridLayout(4, 2));

            controlPanel1 = new JPanel();
            controlPanel2 = new JPanel();
            controlPanel3 = new JPanel();
            controlPanel4 = new JPanel();
            controlPanel1.setLayout(new GridLayout(1,2)); //set the layout of the pannel
            controlPanel2.setLayout(new BorderLayout());
            controlPanel3.setLayout(new BorderLayout());
            controlPanel4.setLayout(new BorderLayout());


            statusLabel = new JLabel("Type URL Here:", JLabel.CENTER);
            statusLabel.setOpaque(true);
            statusLabel.setBackground(new Color(250, 185, 237));
            statusLabel2 = new JLabel("Type Search Term Here:", JLabel.CENTER);
            statusLabel2.setOpaque(true);
            statusLabel2.setBackground(new Color(250, 185, 237));
            statusLabel3 = new JLabel("Results:", JLabel.CENTER);
            statusLabel3.setOpaque(true);
            statusLabel3.setBackground(new Color(250, 185, 237));


            mainFrame.add(controlPanel2);
            mainFrame.add(controlPanel3);
            mainFrame.add(controlPanel1);
            mainFrame.add(controlPanel4);


            mainFrame.setVisible(true);

            ta1 = new JTextArea();
            ta2 = new JTextArea();
            ta3 = new JTextArea();
            scroll = new JScrollPane(ta3);

            controlPanel2.add(statusLabel, BorderLayout.NORTH);
            controlPanel2.add(ta1);
            ta1.setBackground(new Color(250, 85, 82));
            controlPanel3.add(statusLabel2, BorderLayout.NORTH);
            controlPanel3.add(ta2);
            ta2.setBackground(new Color(247, 176, 69));
            controlPanel4.add(statusLabel3, BorderLayout.NORTH);
            controlPanel4.add(scroll, BorderLayout.CENTER);
            ta3.setBackground(new Color(66, 179, 245));


            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    System.exit(0);
                }
            });

        }

        private void showEventDemo() {


            JButton submitButton = new JButton("Submit");
            JButton resetButton = new JButton("Reset");
            submitButton.setFont(new Font("Calibri", Font.BOLD, 30));
            resetButton.setFont(new Font("Calibri", Font.BOLD, 30));
            submitButton.setBackground(new Color(250, 239, 85));
            resetButton.setBackground(new Color(130, 252, 96));
            submitButton.setOpaque(true);
            resetButton.setOpaque(true);
            submitButton.setBorderPainted(false);
            resetButton.setBorderPainted(false);



            submitButton.setActionCommand("Submit");
            resetButton.setActionCommand("Reset");



           submitButton.addActionListener(new ButtonClickListener());
           resetButton.addActionListener(new ButtonClickListener());
           



            controlPanel1.add(submitButton);
            controlPanel1.add(resetButton);



            mainFrame.setVisible(true);
        }


        private class ButtonClickListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();


                if (command.equals("Submit")) {
                    statusLabel3.setText("Results:");
                    String link = ta1.getText();
                    String word = ta2.getText();

                    try {
                        URL url = new URL(link);
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(url.openStream())
                        );
                        String line;

                        while ( (line = reader.readLine()) != null ) {
                            if (line.contains("href")) {

                                int indexHref = line.indexOf("href") + 6;
                                String newLine = line.substring(indexHref);


                                int end = newLine.indexOf("\"");
                                int oEnd = newLine.indexOf("'");

                                if (end < oEnd && end > -1 || oEnd == -1) {
                                    if (newLine.substring(0, end).contains(word.toLowerCase())) {
                                        String newline = newLine.substring(0, end);
                                        ta3.append(newline.toLowerCase());
                                        ta3.append("\n");
                                        errorCheck = errorCheck+1;
                                    }
                                } else if (oEnd > -1) {
                                    if (newLine.substring(0, oEnd).contains(word.toLowerCase())) {
                                        String newline = newLine.substring(0, oEnd);
                                        ta3.append(newline.toLowerCase());
                                        ta3.append("\n");
                                        errorCheck = errorCheck+1;
                                    }
                                }

                            }
                        }
                        reader.close();
                    } catch(Exception ex) {
                    }
                    if(errorCheck==0){
                        ta3.append("No links contains this keyword. Please reset and try again.");
                    }

                    }

                  if(command.equals("Reset")) {
                        ta3.setText("");
                        ta2.setText("");

                        errorCheck=0;
                  }
                }
                }
            }



