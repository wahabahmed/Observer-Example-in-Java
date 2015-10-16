import com.sun.javaws.exceptions.ExitException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by wahaba on 16/10/2015.
 */
public class Main {
    ObservableValue ov;


    public Main() {
        ov = new ObservableValue(0);
        TextObserver to = new TextObserver(ov);
        ov.addObserver(to);
        init();
    }

    TextArea observerText = new TextArea();

    private void init(){
        JFrame jFrame = new JFrame();
        jFrame.setMaximumSize(jFrame.getPreferredSize());
        jFrame.setSize(new Dimension(800, 600));

        JPanel wrapper = new JPanel();
        JPanel panel = new JPanel();

        String[] values = {"10","20","30","40"};
        final JComboBox comboBox = new JComboBox(values);
        comboBox.setVisible(true);
        comboBox.setSelectedIndex(3);
        //comboBox.setMaximumSize(comboBox.getPreferredSize());
        comboBox.setPreferredSize(new Dimension(300, 50));

        wrapper.add( comboBox );
        panel.add( wrapper );

        JPanel middleWrapper = new JPanel();
        JPanel middlePanel = new JPanel();
        middleWrapper.add(observerText);
        middlePanel.add(middleWrapper);



        JPanel bottomWrapper = new JPanel();
        JPanel bottomRightPanel = new JPanel();
        Border redLine = BorderFactory.createLineBorder(Color.RED);
        bottomRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomRightPanel.setPreferredSize(new Dimension(50, 50));
        //bottomRightPanel.setBorder(redLine);
        JButton exitButton = new JButton();
        exitButton.setText("Exit");
        bottomWrapper.setBorder(redLine);
        bottomWrapper.add(exitButton);
        bottomRightPanel.add(bottomWrapper);




        //panel.setVisible(true);

        jFrame.add(BorderLayout.WEST,panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(BorderLayout.CENTER, middlePanel);
        //jFrame.add(BorderLayout.EAST, panel1);



        jFrame.add(BorderLayout.SOUTH,bottomRightPanel);
        jFrame.setVisible(true);

        ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getActionCommand() == "comboBoxChanged") {
                    String s = (String) comboBox.getSelectedItem();//get the selected item
                    setSelectedItem(Integer.parseInt(s));
                }else if(e.getActionCommand() == "Exit"){
                    System.exit(1);
                }
            }
        };

        comboBox.addActionListener(cbActionListener);
        exitButton.addActionListener(cbActionListener);
    }

    public void setSelectedItem(int value){
        ov.setValue(value);
    }


    public static void main(String[] args){
        Main m = new Main();
    }

    public class TextObserver implements Observer {
        private ObservableValue ov = null;

        public TextObserver(ObservableValue ov){
            this.ov = ov;
        }

        @Override
        public void update(Observable obs, Object obj){
            if(obs == ov){
                System.out.println("New Value is " + ov.getValue());
                observerText.setText(Integer.toString(ov.getValue()));
            }
        }
    }


}
