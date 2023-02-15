import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.print.PrintException;
import javax.swing.*;

class note extends JFrame implements ActionListener {

  JFrame f;
  JTextArea t;

  note() {
    f = new JFrame("NotePad");
    t = new JTextArea();
    JMenuBar mb = new JMenuBar();

    JMenu file = new JMenu("File");
    JMenuItem f1 = new JMenuItem("New");
    JMenuItem f2 = new JMenuItem("Save");
    JMenuItem f3 = new JMenuItem("Open");
    JMenuItem f4 = new JMenuItem("Print");

    f1.addActionListener(this);
    f2.addActionListener(this);
    f3.addActionListener(this);
    f4.addActionListener(this);

    file.add(f1);
    file.add(f2);
    file.add(f3);
    file.add(f4);

    JMenu edit = new JMenu("Edit");
    JMenuItem e1 = new JMenuItem("Cut");
    JMenuItem e2 = new JMenuItem("Copy");
    JMenuItem e3 = new JMenuItem("Paste");

    e1.addActionListener(this);
    e2.addActionListener(this);
    e3.addActionListener(this);

    edit.add(e1);
    edit.add(e2);
    edit.add(e3);

    JMenuItem close = new JMenuItem("Close");
    close.addActionListener(this);

    mb.add(file);
    mb.add(edit);
    mb.add(close);
    f.setJMenuBar(mb);
    f.add(t);
    f.setSize(1280, 720);
    f.show();
  }

  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    switch (s) {
      case "New":
        t.setText("");
        break;
      case "Save":
        JFileChooser j = new JFileChooser("D:");
        int r = j.showSaveDialog(null);
        if (r == 0) {
          File fi = new File(j.getSelectedFile().getAbsolutePath());
          try {
            FileWriter fw = new FileWriter(fi);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(t.getText());
            bw.flush();
            bw.close();
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        } else {
          JOptionPane.showMessageDialog(
            f,
            "the user has cancelled the operation."
          );
        }
        break;
      case "Open":
        JFileChooser ji = new JFileChooser("D:");
        int ri = ji.showOpenDialog(null);
        if (ri == 0) {
          File fi = new File(ji.getSelectedFile().getAbsolutePath());
          try {
            FileReader fw = new FileReader(fi);
            BufferedReader br = new BufferedReader(fw);
            String s1 = "", s2 = "";
            s1 = br.readLine();
            while ((s2 = br.readLine()) != null) {
              s1 = s1 + "\n" + s2;
            }
            t.setText(s1);
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        } else {
          JOptionPane.showMessageDialog(
            f,
            "the user has cancelled the operation."
          );
        }
        break;
      case "Print":
        try {
          t.print();
        } catch (PrinterException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        break;
      case "Cut":
        t.cut();
        break;
      case "Copy":
        t.copy();
        break;
      case "Paste":
        t.paste();
        break;
      case "Close":
        f.setVisible(false);
        break;
    }
  }

  public static void main(String args[]) {
    note n = new note();
  }
}
