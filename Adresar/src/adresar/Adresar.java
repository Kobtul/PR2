/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adresar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author iliusvla
 */
public class Adresar {

    static Seznam<Prvek> s = new Seznam<Prvek>();
    static String path = null;

    public static void main(String[] args) throws Exception {


        Prvek c1 = new Prvek("John", "Dow");
        c1.addCity("Praha");
        c1.addEmail("asd@safd.sa");
        c1.addHouseNumber("nula");
        c1.addStreet("1st");
        c1.addPSC(19821);
        c1.addTelNumber(123456789);

        Prvek c2 = new Prvek("Aad", "Man", "2st", "1", "LA", 123456788, 5161615, "aksf@asd.csa");
        Prvek c3 = new Prvek("Zad", "Man", "2st", "1", "LA", 100000000, 5161615, "ksf@asd.csa");
        Prvek c4 = new Prvek("Wad", "Man", "2st", "1", "LA", 999999999, 5161615, "sf@asd.csa");

        s.add(c1);
        s.add(c2);
        s.add(c3);
        s.add(c4);

        Window w = new Window();
        w.init();



    }

    static class Window extends Thread {

        final JFrame Window = new JFrame("Adresar");
        final JList pane1 = new JList();
        final JPanel pane2 = new JPanel();
        TextField FirstName = new TextField(90);
        TextField LastName = new TextField(90);
        TextField Email = new TextField(90);
        TextField City = new TextField(90);
        TextField Num = new TextField(90);
        TextField Street = new TextField(90);
        TextField tel = new TextField(90);
        TextField PSC = new TextField(90);

        public void init() {

            Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Window.setSize(800, 700);
            Window.setVisible(true);
            Window.setResizable(true);

            final JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");

            JMenuItem read = new JMenuItem("Read..");

            read.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String substring = "";
                    final JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(new File("."));
                    int r = fc.showOpenDialog(new JFrame());
                    if (r == JFileChooser.APPROVE_OPTION) {
                        substring = fc.getSelectedFile().getAbsolutePath();
                    }
                    path = substring;

                    ReadXMLFile xml = new ReadXMLFile();

                    try {
                        xml.main(substring);
                        s.removeAll();
                        if (xml.accList.size() > 0) {
                            for (Prvek p : xml.accList) {
                                s.add(p);
                            }
                        }
                        pane1.removeAll();
                        pane1.setListData(s.toArray());
                        pane1.repaint();

                    } catch (IOException ex) {
                        Logger.getLogger(Adresar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(Adresar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(Adresar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });


            JMenuItem save = new JMenuItem("Save");

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String substring = path;
                    if (path == null) {
                        final JFileChooser fc = new JFileChooser();
                        fc.setCurrentDirectory(new File("."));
                        int r = fc.showOpenDialog(new JFrame());
                        if (r == JFileChooser.APPROVE_OPTION) {
                            substring = fc.getSelectedFile().getAbsolutePath();
                        }
                    }

                    WriteXMLFile wxml = new WriteXMLFile();
                    wxml.main(substring, s);
                }
            });

            JMenuItem saveas = new JMenuItem("Save as...");

            saveas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String substring = "";

                    final JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(new File("."));
                    int r = fc.showOpenDialog(new JFrame());
                    if (r == JFileChooser.APPROVE_OPTION) {
                        substring = fc.getSelectedFile().getAbsolutePath();
                    }

                    WriteXMLFile wxml = new WriteXMLFile();
                    wxml.main(substring, s);
                }
            });

            fileMenu.add(read);
            fileMenu.add(save);
            fileMenu.add(saveas);

            JButton saveItem = new JButton("Save");
            JButton addItem = new JButton("Add item");
            JButton deleteItem = new JButton("Delete item");
            JButton search = new JButton("Search");
            JButton setUpperColor = new JButton("Set Upper Color");
            JButton setLowerColor = new JButton("Set Lower Color");
            final JButton sortName = new JButton("Sort by name");
            final JButton sortTel = new JButton("Sort by number");


            setUpperColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame guiFrame = new JFrame();
                    final JColorChooser colorChooser = new JColorChooser();
                    JDialog dialog = JColorChooser.createDialog(guiFrame,
                            "Set Text Area color", false, colorChooser, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            pane1.setBackground(colorChooser.getColor());
                            pane1.repaint();
                            Window.repaint();
                        }
                    }, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                        }
                    });

                    dialog.setVisible(true);
                }
            });

            setLowerColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame guiFrame = new JFrame();
                    final JColorChooser colorChooser = new JColorChooser();
                    JDialog dialog = JColorChooser.createDialog(guiFrame,
                            "Set Text Area color", false, colorChooser, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            pane2.setBackground(colorChooser.getColor());
                            pane2.repaint();
                            Window.repaint();
                        }
                    }, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            //this actionListener is for the cancel button
                            // tracker.append("\nCancel button clicked..");
                        }
                    });

                    dialog.setVisible(true);
                }
            });

            sortName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Seznam result = new Seznam();
                    if (s.size() > 0) {
                        for (Prvek prvek : s.compJmen()) {
                            result.add(prvek);
                        }
                        final JButton back = new JButton("Back to normal view");
                        back.setName("back");
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                pane1.removeAll();
                                pane1.setListData(s.toArray());
                                menuBar.remove(back);
                                Window.setJMenuBar(menuBar);
                                pane1.removeAll();
                                pane1.setListData(s.toArray());
                                pane1.repaint();
                                pane1.setSelectedIndex(0);
                                pane2.repaint();
                                Window.repaint();
                            }
                        });
                        boolean repeat = false;
                        for (Component c : menuBar.getComponents()) {
                            if (c.getName() == "back") {
                                repeat = true;
                            }
                        }

                        if (!repeat) {
                            menuBar.add(back);
                        }

                        for (Component a : menuBar.getComponents()) {
                            System.out.println(a.getName());
                        }
                        Window.setJMenuBar(menuBar);
                        pane1.removeAll();
                        pane1.setListData(result.toArray());
                        if (s.size() > 0) {
                            pane1.setSelectedIndex(pane1.getFirstVisibleIndex());
                        }
                        pane2.repaint();
                        Window.repaint();

                    }
                }
            });

            sortTel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Seznam result = new Seznam();
                    if (s.size() > 0) {
                        for (Prvek prvek : s.compTel()) {
                            result.add(prvek);
                        }
                        final JButton back = new JButton("Back to normal view");
                        back.setName("back");
                        back.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                pane1.removeAll();
                                pane1.setListData(s.toArray());
                                menuBar.remove(back);
                                Window.setJMenuBar(menuBar);
                                pane1.removeAll();
                                pane1.setListData(s.toArray());
                                pane1.repaint();
                                pane1.setSelectedIndex(0);
                                pane2.repaint();
                                Window.repaint();
                            }
                        });
                        boolean repeat = false;
                        for (Component c : menuBar.getComponents()) {
                            if (c.getName() == "back") {
                                repeat = true;
                            }
                        }

                        if (!repeat) {
                            menuBar.add(back);
                        }
                        Window.setJMenuBar(menuBar);
                        pane1.removeAll();
                        pane1.setListData(result.toArray());
                        pane1.setSelectedIndex(1);
                        pane2.repaint();
                        Window.repaint();
                    }
                }
            });


            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String substring = JOptionPane.showInputDialog(null, "Enter substing ",
                            "Search", JOptionPane.INFORMATION_MESSAGE);
                    if (!"".equals(substring) && substring != null) {
                        Seznam substing = s.findSubString1(substring);
                        if (substing.size() > 0) {
                            for (Component c : menuBar.getComponents()) {
                                if (c.getName() == "back") {
                                    menuBar.remove(c);
                                    Window.setJMenuBar(menuBar);
                                }
                            }

                            sortName.setEnabled(false);
                            sortName.setText("Please end you ");
                            sortTel.setEnabled(false);
                            sortTel.setText("search session");

                            final JButton back = new JButton("Back from search");
                            back.setName("search");
                            back.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    sortName.setEnabled(true);
                                    sortTel.setEnabled(true);
                                    sortTel.setText("Sort by number");
                                    sortName.setText("Sort by name");
                                    menuBar.remove(back);
                                    Window.setJMenuBar(menuBar);
                                    pane1.removeAll();
                                    pane1.setListData(s.toArray());
                                    pane1.repaint();
                                    pane1.setSelectedIndex(0);
                                    pane2.repaint();
                                    Window.repaint();
                                }
                            });
                            menuBar.add(back);
                            Window.setJMenuBar(menuBar);
                            pane1.removeAll();
                            pane1.setListData(substing.toArray());
                            pane1.repaint();
                            pane1.setSelectedIndex(0);
                            pane2.repaint();
                            Window.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Nothing",
                                    "Search", JOptionPane.PLAIN_MESSAGE);
                        }
                    }

                }
            });

            addItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Prvek p = new Prvek("New", "Item");
                    s.add(p);
                    pane1.setListData(s.toArray());
                    pane1.repaint();
                    pane1.setSelectedIndex(s.size() - 1);
                    pane2.repaint();
                    Window.repaint();

                }
            });


            deleteItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Component c : menuBar.getComponents()) {
                        if (c.getName() == "search") {
                            menuBar.remove(c);
                            sortName.setEnabled(true);
                            sortTel.setEnabled(true);
                        }
                    }
                    for (int i = 0; i < s.size(); i++) {
                        Prvek p = s.get(i);
                        if (pane1.getSelectedValue() != null && pane1.getSelectedValue().toString().contains(Integer.toString(p.getNumber()))) {
                            s.remove(i);
                            if (s.size() > 0) {
                                pane1.setListData(s.toArray());
                            } else {
                                pane1.setListData(new Object[0]);
                            }
                            pane1.repaint();
                            pane1.setSelectedIndex(pane1.getFirstVisibleIndex());
                            pane2.repaint();
                            Window.repaint();
                            break;
                        }
                    }
                }
            });

            menuBar.add(fileMenu);
            menuBar.add(sortName);
            menuBar.add(sortTel);
            menuBar.add(addItem);

            menuBar.add(deleteItem);
            menuBar.add(search);
            menuBar.add(setUpperColor);
            menuBar.add(setLowerColor);
            Window.setJMenuBar(menuBar);
            pane2.setLayout(null);
            pane1.setBackground(Color.WHITE);
            pane2.setBackground(Color.WHITE);
            pane1.setSize(800, 300);
            pane2.setSize(800, 300);

            pane1.setListData(s.toArray());


            Font big = new Font("Arial", Font.BOLD, 12);
            final Label FName = new Label("First Name:   ");
            FName.setBounds(5, 10, 90, 20);
            FName.setFont(big);

            final Label LName = new Label("Last Name:    ");
            LName.setBounds(5, 35, 90, 20);
            LName.setFont(big);

            final Label FEmail = new Label("Email:        ");
            FEmail.setBounds(5, 60, 90, 20);
            FEmail.setFont(big);

            final Label FCity = new Label("City :        ");
            FCity.setBounds(5, 85, 90, 20);
            FCity.setFont(big);

            final Label FBuildN = new Label("Building num: ");
            FBuildN.setBounds(5, 110, 90, 20);
            FBuildN.setFont(big);

            final Label FStreet = new Label("Street:       ");
            FStreet.setBounds(5, 135, 90, 20);
            FStreet.setFont(big);

            final Label FPhone = new Label("Phone num:    ");
            FPhone.setBounds(5, 160, 90, 20);
            FPhone.setFont(big);

            Label FPSC = new Label("PSC:          ");
            FPSC.setBounds(5, 185, 90, 20);
            FPSC.setFont(big);


            saveItem.setBounds(195, 210, 90, 30);
            saveItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = pane1.getSelectedIndex();

                    Prvek p = s.get(selectedIndex);
                    Object selsected = pane1.getSelectedValue();

                    if (selsected != null && selsected.toString().contains(Integer.toString(p.getNumber()))) {
                        p.addJmeno(FirstName.getText());
                        p.addPrijmeni(LastName.getText());
                        p.addCity(City.getText());
                        p.addEmail(Email.getText());
                        p.addHouseNumber(Num.getText());
                        p.addStreet(Street.getText());
                        p.addTelNumber(((int) Double.parseDouble((tel.getText()))));

                        if (!"".equals(p.getJmeno()) && !"".equals(p.getPrijmeni()) && p.getJmeno() != null && p.getPrijmeni() != null) {
                            System.out.println("Saved!");
                            pane1.setListData(s.toArray());
                            pane1.repaint();
                            pane1.setSelectedIndex(selectedIndex);
                            pane2.repaint();
                            Window.repaint();
                        }
                    }

                }
            });

            pane2.add(FName);
            pane2.add(LName);
            pane2.add(FEmail);
            pane2.add(FCity);
            pane2.add(FBuildN);
            pane2.add(FStreet);
            pane2.add(FPhone);
            pane2.add(FPSC);
            pane2.add(saveItem);

            FirstName.setBounds(
                    95, 10, 190, 20);
            LastName.setBounds(
                    95, 35, 190, 20);
            Email.setBounds(
                    95, 60, 190, 20);
            City.setBounds(
                    95, 85, 190, 20);
            Num.setBounds(
                    95, 110, 190, 20);
            Street.setBounds(
                    95, 135, 190, 20);
            tel.setBounds(
                    95, 160, 190, 20);
            PSC.setBounds(
                    95, 185, 190, 20);

            pane2.add(FirstName);
            pane2.add(LastName);
            pane2.add(Email);
            pane2.add(City);
            pane2.add(Num);
            pane2.add(Street);
            pane2.add(tel);
            pane2.add(PSC);

            for (Component c : pane2.getComponents()) {
                c.repaint();
            }

            Window();
        }

        private List<String> readFile(String filename) {
            List<String> records = new ArrayList<String>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = reader.readLine()) != null) {
                    records.add(line);
                }
                reader.close();
                return records;
            } catch (Exception e) {
                System.err.format("Exception occurred trying to read '%s'.", filename);
                e.printStackTrace();
                return null;
            }
        }

        public void Window() {

            pane1.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent evt) {
                    if (evt.getValueIsAdjusting()) {
                        return;
                    } else {
                        for (int i = 0; i < s.size(); i++) {
                            Prvek p = s.get(i);
                            if (pane1.getSelectedValue() != null && pane1.getSelectedValue().toString().contains(Integer.toString(p.getNumber()))) {
                                FirstName.setText(p.getJmeno());
                                LastName.setText(p.getPrijmeni());
                                Email.setText(p.getEmail());
                                City.setText(p.getMesto());
                                Num.setText(p.getHouseNumber());
                                Street.setText(p.getUlice());
                                tel.setText(Integer.toString(p.getNumber()));
                                PSC.setText(Integer.toString(p.getPSC()));
                                return;


                            }
                        }

                    }
                }
            });


            final JScrollPane mySchroll = new JScrollPane(pane1,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            mySchroll.setPreferredSize(new Dimension(800, 300));

            Window.add(mySchroll, BorderLayout.PAGE_START);
            Window.add(pane2, BorderLayout.CENTER);

            Thread t = new Thread(new Runnable() {
                public void run() {

                    pane2.repaint();
                    pane2.updateUI();
                    mySchroll.updateUI();
                    for (Component c : pane2.getComponents()) {
                        c.repaint();
                    }



                }
            });
            t.start();




        }
    }
}
