import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CekAngkaGanjilGenap extends JFrame {
    private JTextField txtAngka;
    private JButton btnCek;
    private JLabel lblHasil;

    public CekAngkaGanjilGenap() {
        setTitle("Program Cek Angka Ganjil Genap & Prima");
        setSize(350, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));

        JLabel lblJudul = new JLabel("Masukkan Angka:", SwingConstants.CENTER);
        panel.add(lblJudul);

        txtAngka = new JTextField();
        panel.add(txtAngka);

        btnCek = new JButton("Cek");
        panel.add(btnCek);

        lblHasil = new JLabel("", SwingConstants.CENTER);

        add(panel, BorderLayout.CENTER);
        add(lblHasil, BorderLayout.SOUTH);

        // Tombol cek ditekan
        btnCek.addActionListener(e -> cekAngka());

        // Input hanya angka
        txtAngka.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Masukkan hanya angka!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Bersihkan input saat difokuskan
        txtAngka.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtAngka.setText("");
                lblHasil.setText("");
            }
        });
    }

    private void cekAngka() {
        String teks = txtAngka.getText().trim();
        if (teks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan angka terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int angka = Integer.parseInt(teks);
            String hasil = "";

            if (angka % 2 == 0) hasil += angka + " adalah GENAP\n";
            else hasil += angka + " adalah GANJIL\n";

            if (cekPrima(angka)) hasil += "Dan merupakan bilangan PRIMA.";
            else hasil += "Bukan bilangan prima.";

            JOptionPane.showMessageDialog(this, hasil, "Hasil", JOptionPane.INFORMATION_MESSAGE);
            lblHasil.setText("Terakhir diperiksa: " + angka);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean cekPrima(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CekAngkaGanjilGenap().setVisible(true));
    }
}
