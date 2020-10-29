package Project.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Flashy extends JPanel implements ActionListener {
    private Timer timer;
    private int count=0;
    private boolean up=true;
    private int curr=1;
    Color bgColor;
    public Flashy(){
        setBackground(Color.black);
        bgColor= getBackground();
        timer=new Timer(20,this);
//        pasdha();
        timer.start();

    }

//    private void pasdha() {
//        ;
//
//        for(int alpha = bgColor.getAlpha(); alpha >= 0; alpha--)
//        {
//            setBackground(new Color(
//                    bgColor.getRed(),
//                    bgColor.getGreen(),
//                    bgColor.getBlue(),
//                    alpha));
//
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }            repaint();
//        }
//    }

    @Override
    protected void paintComponent(Graphics g) {
try{
    System.out.println(curr);
    super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.SrcOver.derive((float) (curr/100.0)));
        g2.setColor(Color.GREEN);
        g2.fillRect( 0,0,getWidth(),getHeight());
        g2.dispose();

}
catch (Exception e){

}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        curr += up?3:-3;
        if (curr <= 0) {
            curr=0;
            up=!up;

        }
        else if (curr >= 100) {
            curr=100;
            up=!up;
            count++;
            timer.restart();
        }
        repaint();
    }
}
