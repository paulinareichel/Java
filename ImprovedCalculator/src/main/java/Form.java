import javax.swing.*;
import java.awt.event.*;
import java.text.MessageFormat;

import org.mariuszgromada.math.mxparser.*;

import javax.swing.DefaultListModel;

public class Form extends JFrame implements ActionListener {

    private JTextArea textArea1;
    private JTextField textField1;
    private JList list1;
    private JButton evaluateButton;
    private JPanel mainPanel;
    private JScrollPane scroll;
    private JMenuItem reset, exit;


    /*** My variables***/
    JMenu menu = new JMenu("Options");
    static JMenuBar menuBar = new JMenuBar();
    DefaultListModel function = new DefaultListModel();
    String lastText;
    public double result = 0;

    public Form() {

        textArea1.setEditable(false);
        reset = new JMenuItem("Reset");
        exit = new JMenuItem("Exit");
        reset.addActionListener(this);
        exit.addActionListener(this);
        menu.add(reset);
        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        add(mainPanel);

        /**Filling JList**/
        final DefaultListModel function = createListofFunction();
        Functions last = new Functions("Last result", Double.toString(result));
        function.addElement(last);
        evaluateButton.addActionListener(this);
        list1.setModel(function);
        list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        MouseListener mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getClickCount() == 2) {
                    JList theList = (JList) e.getSource();
                    int index = theList.getSelectedIndex();
                    switch (index) {
                        case 0:
                            textField1.setText(textField1.getText() + "sin()");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() - 1);
                            break;
                        case 1:
                            textField1.setText(textField1.getText() + "cos()");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() - 1);
                            break;
                        case 2:
                            textField1.setText(textField1.getText() + "tg()");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() - 1);
                            break;
                        case 3:
                            textField1.setText(textField1.getText() + "ctg()");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() - 1);
                            break;
                        case 4:
                            textField1.setText(textField1.getText() + "pi");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() );
                            break;
                        case 5:
                            textField1.setText(textField1.getText() + "e");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() );
                            break;
                        case 6:
                            textField1.setText(textField1.getText() + "[gam]");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() );
                            break;
                        case 7:
                            textField1.setText(textField1.getText() + "+");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() );
                            break;
                        case 8:
                            textField1.setText(textField1.getText() + "-");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() );
                            break;
                        case 9:
                            textField1.setText(textField1.getText() + "*");
                            textField1.requestFocus();
                            textField1.setCaretPosition(textField1.getCaretPosition() );
                            break;
                        case 10:
                            textField1.setText(textField1.getText() + result);
                            break;

                        default:
                            break;
                    }
                } else {                }
            }

        };
        list1.addMouseListener(mouse);


        /** evaluateButton & to textField & error message **/
        evaluateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == textField1 || e.getSource() == evaluateButton) {
                    String text = textField1.getText();
                    lastText = text;
                    Expression expression = new Expression(text);
                    if (expression.checkSyntax()) {
                        result = expression.calculate();
                        MessageFormat form = new MessageFormat(text + " = " + result + "\n");
                        textArea1.append(form.toPattern());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid value", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    textField1.setText(null);
                } else {
                }
            }
        });


        /**  Pressing enter or up arrow**/
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getSource() == textArea1) {
                    String text = textField1.getText();
                    lastText = text;
                    Expression expression = new Expression(text);
                    if (expression.checkSyntax()) {
                        result = expression.calculate();
                        MessageFormat form = new MessageFormat(text + " = " + result + "\n");
                        textArea1.append(form.toPattern());
                        textField1.setText(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid value", "ERROR", JOptionPane.ERROR_MESSAGE);
                        textField1.setText(null);

                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    textField1.setText(lastText);
                } else {
                }
            }
        });

    }


    // action for reset & error button
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            textArea1.setText(null);
            textField1.setText(null);
        }
        if (e.getSource() == exit) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(false);
            dispose();
        }
    }


    /*** Adding Functions to DefaulListMode **/
    javax.swing.DefaultListModel createListofFunction() {
        Functions sin = new Functions("Sinus", "sin()");
        Functions cos = new Functions("Cosinus", "cos()");
        Functions tan = new Functions("Tangens", "tg()");
        Functions cotan = new Functions("Cotangens", "ctg()");
        Functions pi = new Functions("Pi", "pi");
        Functions euler = new Functions("Napier's constant", "e");
        Functions gam = new Functions("Euler-Mascheroni constant", "[gam]");
        Functions plus = new Functions("Addition", "+");
        Functions minus = new Functions("Subtraction", "-");
        Functions multi = new Functions("Multiplication", "*");
        function = new DefaultListModel();
        function.addElement(sin);
        function.addElement(cos);
        function.addElement(tan);
        function.addElement(cotan);
        function.addElement(pi);
        function.addElement(euler);
        function.addElement(gam);
        function.addElement(plus);
        function.addElement(minus);
        function.addElement(multi);
        return function;
    }
}


