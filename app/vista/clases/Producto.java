package app.vista.clases;

import app.controlador.Servicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Producto extends JFrame {
    private JPanel jpanel1;
    private JButton btnMostrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnNuevo;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtPrecio;
    private JTable tbProd;
    private JButton imprimirReporteButton;
    private DefaultTableModel model;


    // tabla
    private Object[] columns={"Id","Codigo","Producto","Precio"};
    private Object[] row= new Object[4];
    private Map<Integer,Producto> mapa=null;

    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::




    public Producto() {
        setTitle("Venta");
        setSize(500, 300);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(jpanel1);
        setLocationRelativeTo(null);
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductos();
            }
        });

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            new Producto().setVisible(true);
        });
    }

    public void agregarProductos(){
        String nombre=txtNombre.getText();
        String codigo=txtCodigo.getText();
        String precio=txtPrecio.getText();

    }

    public void obtenerRegistrosTabla(){
        model=new DefaultTableModel(){
            private static final long serialVersionUID=1L;
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {return false;}
        };
        model.setColumnIdentifiers(columns);
        tbProd.setModel(model);

        tbProd.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbProd.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbProd.getColumnModel().getColumn(0).setPreferredWidth(180);
        tbProd.getColumnModel().getColumn(0).setPreferredWidth(50);

        while(model.getRowCount()>0) {model.removeRow(0);}

        //mapa=controlador.seleccionarTodo();

        for(Map.Entry<Integer,Producto> entry:mapa.entrySet()){
            row[0]=entry.getKey();
            row[1]=entry.getValue().getCodigo();
            row[2]=entry.getValue().getNombre();
            row[3]=String.format("%.2f",entry.getValue().getPrecio());
            model.addRow(row);
        }
        limpiarCampos();
    }

    private Object getPrecio() {
        return null;
    }
    private Object getNombre() {
        return null;
    }
    private Object getCodigo() {
        return null;
    }

    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }


}
