package ventanas;

import controlador.Coordinador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton btnPersonas;
    private JButton btnMascotas;
    private Coordinador coordinador;
    private  JLabel fondo;



    public VentanaPrincipal() {
        setTitle("SISTEMA VETERINARIA ABC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null); // NO usar BorderLayout


        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/ventanas/imagenFondo.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon imagenFondo = new ImageIcon(imagenEscalada);

        fondo = new JLabel(imagenFondo);
        fondo.setBounds(0, 0, 800, 600); // ocupa toda la ventana
        fondo.setLayout(null); // permite usar setBounds

        btnPersonas = new JButton("Gestionar Personas");
        btnPersonas.setBounds(150, 350, 200, 60);
        btnPersonas.addActionListener(this);

        btnMascotas = new JButton("Gestionar Mascotas");
        btnMascotas.setBounds(450, 350, 200, 60);
        btnMascotas.addActionListener(this);

        fondo.add(btnPersonas);
        fondo.add(btnMascotas);

        add(fondo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPersonas){
            coordinador.mostrarPersonas();
        } else if (e.getSource() == btnMascotas) {
            coordinador.mostrarMacotas();
        }
    }


    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

}
