package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame{
    public LandingPage(){
        JFrame frame = this;
        setTitle("Main Menu");
        setSize(400, 300);
        setLayout(new GridLayout(2, 1));
    
        JButton login = new JButton("Menu Login");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                new MenuLogin();
            }
        });
        add(login);
        JButton register = new JButton("Menu Register");
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                new MenuRegister();
            }
        });
        add(register);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LandingPage();
    }
}
