import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Layout {
        private JFrame mainFrame;
        private JLabel statusLabel;
        private JLabel statusLabel2;
        private JPanel controlPanel1;
        private JPanel controlPanel2;
        private JPanel controlPanel3;
        private JTextArea ta1;
        private JTextArea ta2;
        private int WIDTH=800;
        private int HEIGHT=700;


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
            mainFrame.setLayout(new GridLayout(2, 2));

            controlPanel1 = new JPanel();
            controlPanel2 = new JPanel();
            controlPanel3 = new JPanel();
            controlPanel1.setLayout(new BorderLayout()); //set the layout of the pannel
            controlPanel2.setLayout(new BorderLayout());
            controlPanel3.setLayout(new BorderLayout());

            statusLabel = new JLabel("Type URL Here:", JLabel.CENTER);
            statusLabel2 = new JLabel("Type Search Term Here:", JLabel.CENTER);

            mainFrame.add(controlPanel2);
            mainFrame.add(controlPanel3);
            mainFrame.add(controlPanel1);

            mainFrame.setVisible(true);

            ta1 = new JTextArea();
            ta2 = new JTextArea();
            controlPanel2.add(statusLabel, BorderLayout.NORTH);
            controlPanel2.add(ta1);
            controlPanel3.add(statusLabel2, BorderLayout.NORTH);
            controlPanel3.add(ta2);



            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    System.exit(0);
                }
            });

        }

        private void showEventDemo() {


            JButton submitButton = new JButton("Submit");



            submitButton.setActionCommand("Submit");



            submitButton.addActionListener(new ButtonClickListener());



            controlPanel1.add(submitButton);


            mainFrame.setVisible(true);
        }


        private class ButtonClickListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();

                if (command.equals("OK")) {
                    statusLabel.setText("Ok Button clicked.");
                } else if (command.equals("Submit")) {
                    statusLabel.setText("Submit Button clicked.");
                } else {
                    statusLabel.setText("Cancel Button clicked.");
                }
            }
        }
    }

