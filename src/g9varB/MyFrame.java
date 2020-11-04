package g9varB;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import javax.swing.*;
import javax.tools.Tool;

public class MyFrame extends JFrame {
private static final int WIDTH = 500;
private static final int HEIGHT = 300;

private JButton buttonApply = new JButton("Применить");
    private JButton buttonM = new JButton("M+");
    private JButton buttonMC = new JButton("MC");
    private JButton buttonCount = new JButton("Вычислить");

    private JLabel labelEquation = new JLabel("Выберите формулу");
    private JLabel labelVariable = new JLabel("Выберите переменную");
    private JLabel labelResult = new JLabel(" Результат ");

    private JTextField textApply = new JTextField("0",10);
    private JTextField textResult = new JTextField("0",10);

    private ButtonGroup radioButtonEquation = new ButtonGroup();
    private ButtonGroup radioButtonVariable = new ButtonGroup();

    private Box buttonsGroup = Box.createHorizontalBox();
    private Box hBoxFormulaType = Box.createHorizontalBox();
    private Box hBoxVariable = Box.createHorizontalBox();
    private Box textArea = Box.createHorizontalBox();
    private Box countButton = Box.createHorizontalBox();
    private Box resultBox = Box.createHorizontalBox();

    private int ActiveFormula  = 1;
    private int ActiveVariable = 1;
    private double varX = 0;
    private double varY = 0;
    private double varZ = 0;
    private double answer = 0;

    public Double calculate1(Double x, Double y, double z) {
        return (Math.sin(y)+y*y+Math.exp(Math.cos(y))*Math.pow(Math.log(z)+Math.sin(Math.PI*x*x),1/4));
        //return x+y+z;
    }

    public Double calculate2(Double x, Double y, double z) {
        return (Math.pow(y+x*x*x,1/z))/Math.log(z);
    }

    private void addRadioButtonF(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MyFrame.this.ActiveFormula = formulaId;    }      });
        radioButtonEquation.add(button);
        hBoxFormulaType.add(button);  }

    private void addRadioButtonV(String buttonName, final int variableId){
JRadioButton button = new JRadioButton(buttonName);
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        MyFrame.this.ActiveVariable = variableId;
    }
});
radioButtonVariable.add(button);
hBoxVariable.add(button);
    }

    public MyFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        JPanel container1 = new JPanel();


        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.add(labelEquation);
        addRadioButtonF("Формула 1",1);
        addRadioButtonF("Формула 2",2);
radioButtonEquation.setSelected(radioButtonEquation.getElements().nextElement().getModel(),true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxVariable.add(Box.createHorizontalGlue());
        hBoxVariable.add(labelVariable);
        addRadioButtonV("Переменная 1",1);
        addRadioButtonV("Переменная 2",2);
        addRadioButtonV("Переменная 3",3);
        radioButtonVariable.setSelected(radioButtonVariable.getElements().nextElement().getModel(),true);
        hBoxVariable.add(Box.createHorizontalGlue());
        buttonsGroup.add(buttonM);
        buttonsGroup.add(buttonMC);

        
        textApply.setSize(100,100);
        textArea.add(textApply);
        textArea.add(buttonApply);

        textArea.add(Box.createHorizontalGlue());
        countButton.add(Box.createHorizontalGlue());

        countButton.add(Box.createHorizontalGlue());


        resultBox.add(Box.createHorizontalGlue());

        resultBox.add(buttonCount);
        resultBox.add(labelResult);

        resultBox.add(textResult);


        buttonApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
try{
    switch (ActiveVariable){
        case (1):{
            varX = Double.parseDouble(textApply.getText());
            textApply.setText("0");
        }
        break;
        case (2):{
            varY = Double.parseDouble(textApply.getText());
            textApply.setText("0");
        }
        break;
        case (3):{
            varZ = Double.parseDouble(textApply.getText());
            textApply.setText("0");
        }
        break;
    }

}
catch (NumberFormatException ex){
JOptionPane.showMessageDialog(   MyFrame.this, "Ошибка в формате записи числа с плавающей точкой",
        "Ошибочный формат числа",
        JOptionPane.WARNING_MESSAGE);
}
            }
        });

        buttonCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
labelResult.setText(" Результат ");
switch (ActiveFormula){
    case (1):{
        textResult.setText(calculate1(varX,varY,varZ).toString());
        answer = calculate1(varX,varY,varZ);
    }
    break;
    case (2):{
        textResult.setText(calculate2(varX,varY,varZ).toString());
        answer = calculate2(varX,varY,varZ);
    }
    break;
}
            }
        });

buttonMC.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ActiveVariable){
            case (1):{
                varX=0;
                textResult.setText("0");
            }
            break;
            case (2):{
                varY=0;
                textResult.setText("0");
            }
            break;
            case (3):{
                varZ=0;
                textResult.setText("0");
            }
            break;
        }
    }
});

buttonM.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ActiveVariable){
            case (1):{
                varX+=answer;
                labelResult.setText(" X: ");
                textResult.setText(Double.toString(varX));
            }
            break;
            case (2):{
                varY+=answer;
                labelResult.setText(" Y: ");
                textResult.setText(Double.toString(varY));
            }
            break;
            case (3):{
                varZ+=answer;
                labelResult.setText(" Z: ");
                textResult.setText(Double.toString(varZ));
            }
            break;
        }
    }
});

        //container1.setLayout(new FlowLayout());
        container1.add(hBoxFormulaType);
        container1.add(hBoxVariable);
        container1.add(buttonsGroup);
        container1.add(textArea);
        //container1.add(buttonCount);
        //container1.add(textResult);
        container1.add(resultBox);




        // setContentPane(container1);
        add(container1);
    }
}


