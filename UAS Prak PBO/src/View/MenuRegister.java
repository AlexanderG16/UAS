package View;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.DateLabelFormatter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class MenuRegister extends JFrame{
    public MenuRegister(){
        JFrame frame = this;
        JPanel panel = new JPanel();
        setTitle("Menu Register");
        setSize(600, 400);
        
        JLabel emailLabel = new JLabel("Masukkan E-Mail:");
        JTextField emailField = new JTextField();
        JLabel namaLabel = new JLabel("Masukkan Nama:");
        JTextField namaField = new JTextField();
        panel.add(emailLabel); panel.add(emailField);
        panel.add(namaLabel); panel.add(namaField);
        JLabel passwordLabel = new JLabel("Masukkan Password:");
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordLabel); panel.add(passwordField);
        JLabel genderLabel = new JLabel("Masukkan Jenis Kelamin:");
        panel.add(genderLabel);
        JRadioButton pria = new JRadioButton("Pria");
        JRadioButton wanita = new JRadioButton("Wanita");
        ButtonGroup gender = new ButtonGroup();
        gender.add(pria); gender.add(wanita);
        panel.add(pria); panel.add(wanita);
        JLabel tanggalLahirLabel = new JLabel("Masukkan Tanggal Lahir:");
        panel.add(tanggalLahirLabel);
        UtilDateModel tanggalLahirModel = new UtilDateModel();
        JDatePanelImpl tanggalLahirDatePanel = new JDatePanelImpl(tanggalLahirModel);
        JDatePickerImpl tanggalLahirDatePicker = new JDatePickerImpl(tanggalLahirDatePanel, new DateLabelFormatter());
        panel.add(tanggalLahirDatePicker);
        JLabel fotoLabel = new JLabel("Masukkan foto:");
        panel.add(fotoLabel);
        JButton browseButton = new JButton("Pilih Foto");
        JLabel fotoPath = new JLabel();
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fotoPath.setText(selectedFile.getAbsolutePath());
                    // Tambahkan logika untuk memproses file foto yang dipilih
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());
                    Image image = imageIcon.getImage().getScaledInstance(75, 100, Image.SCALE_DEFAULT);
                }
            }
        });

        JButton register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(Controller.SystemFunction.checkPassAuth(String.valueOf(passwordField.getPassword()))){
                    int gender = 0;
                    if(pria.isSelected()){gender = 0;} else {gender = 1;}
                    if (Controller.SystemFunction.inputNewUser(namaField.getText(), emailField.getText(), String.valueOf(passwordField.getPassword()), gender, (Date) tanggalLahirDatePicker.getModel().getValue(), fotoPath.getText()))
                    JOptionPane.showMessageDialog(null, "Successfully Registered!", "Successfully Registered!", JOptionPane.INFORMATION_MESSAGE, null);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed to Register!", "Failed!", JOptionPane.ERROR_MESSAGE, null); 
                }
            }
        });
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                new LandingPage();
            }
        });
        panel.add(register); panel.add(back);
        panel.setLayout(new GridLayout(7, 2));
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuRegister();
    }
}
