import CommonBeans.Stone;
import Initialization.GenerateObject;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import jbotsim.Topology;
import jbotsim.ui.JTopology;
import jbotsim.ui.JViewer;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class run extends JFrame {
    static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public run(JTopology jTopology) throws MalformedURLException, FileNotFoundException, JavaLayerException {

        JFrame f = new JFrame();
        f.setSize(900, 600);
        f.setVisible(true);

        //------------------------ 2. 设置ContentPane透明 --------------------
        //把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        JPanel imagePanel = (JPanel) f.getContentPane();
        imagePanel.setOpaque(false);

        //------------------------ 3. 图片框/背景图 -------------------------
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("/home/zdh/Documents/Repository/QLearning/src/main/resources/imgs/space.jpg");

        //------------------------ 4. 常规背景:图片原始大小 ------------------
        label.setIcon(icon);
        label.setBounds(0, 0, f.getWidth(), f.getHeight());

        //------------------------ 5. 拉伸背景:适应窗体大小 ------------------
        int w = f.getLayeredPane().getWidth();
        int h = f.getLayeredPane().getHeight();
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(img));
        label.setBounds(0, 0, w,h);

        //------- 6. 把背景图片添加到分层窗格的最底层作为背景 ----------------
        f.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        f.add(jTopology);
    }


    public static void main(String[] orgs) throws InterruptedException, MalformedURLException, FileNotFoundException, JavaLayerException {
        final Topology tp = new Topology(900, 600);
        tp.setDefaultNodeModel(Stone.class);
        tp.setClockSpeed(15);

        final Runnable beeper = new Runnable() {
            int i = 1;
            public void run() {
                GenerateObject.generateOneStone(tp, i++, 900, 600);
            }
        };

        final Runnable beeper2 = new Runnable() {
            public void run() {
                File file = new File("/home/zdh/Documents/Repository/QLearning/src/main/resources/imgs/music.mp3");
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    Player player = new Player(bis);
                    player.play();
                    player.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        };

        final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 0, 2, SECONDS);
        final ScheduledFuture beeperHandle2 = scheduler.scheduleAtFixedRate(beeper2, 0, 29, SECONDS);
        GenerateObject.generateOneBird(tp, 0, 900, 600);

        JTopology jTopology = new JTopology(tp);
        jTopology.setOpaque(false);

        new run(jTopology);

        scheduler.schedule(new Runnable() {
            public void run() {
                beeperHandle.cancel(true);
//                scheduler.shutdown();
            }
        }, 60, SECONDS);
    }
}
