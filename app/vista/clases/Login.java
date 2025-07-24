package app.vista.clases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Login extends JFrame {
    private  JPanel panel1;
    private JTextField textField1;
    private JButton btnInicSes;
    private JLabel txt1;
    private JLabel txt2;
    private JPasswordField passwordField1;

    public Login() {
        setTitle("Inicio de sesión ");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);

        File archivo= new File("C:\Users\POO\IdeaProjects\CRUD\src\app\modelo\users.txt");
        btnInicSes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user=textField1.getText();
                String password=passwordField1.getText();
                if(user.equals("Ameri")&& password.equals("2112")){
                    JOptionPane.showMessageDialog(null,"Bienvenido");
                    Ventana ventana=new Ventana(user);
                    ventana.setVisible(true);
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null,"Credenciales incorrectas");
                }
                textField1.setText("");
                passwordField1.setText("");
            }
        });
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            new Login().setVisible(true);
        });
    }
}
