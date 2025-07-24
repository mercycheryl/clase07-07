package app.vista.clases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login extends JFrame {
    private  JPanel panel1;
    private JTextField textField1;
    private JButton btnInicSes;
    private JLabel txt1;
    private JLabel txt2;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;

    public Login() {
        setTitle("Inicio de sesión ");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);

        btnInicSes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user=textField1.getText().trim();
                String password=passwordField1.getText().trim();
                if (verificarCredenciales(user, password)) {
                    Ventana ventana = new Ventana(user);
                    ventana.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
                textField1.setText("");
                passwordField1.setText("");
            }
        });
    }

    private boolean verificarCredenciales(String usuario, String contrasena) {
        try {
            BufferedReader br = new BufferedReader(new FileReader
                    ("C:\\Users\\POO\\IdeaProjects\\CRUD\\src\\app\\modelo\\users.txt"));
            String linea;
            //JOptionPane.showMessageDialog(null,"Exito al leer el archivo");
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    String user = partes[0];
                    String pass = partes[1];
                    if (usuario.equals(user) && contrasena.equals(pass)) {
                        br.close();
                        return true;
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error ");
        }
        return false;
    }



    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            new Login().setVisible(true);
        });
    }
}
