package View;

import javax.swing.JFrame;

import Model.User;

public class MenuHomepage extends JFrame{
    public MenuHomepage(String email){
        setTitle("Menu Homepage");
        setSize(500, 500);

        User user = Controller.SystemFunction.getUserByEmail(email);
        
    }
}
