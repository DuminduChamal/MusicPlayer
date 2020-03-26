import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer
{
    static JFrame f = new JFrame("Mt First Music Video");
    static MyDrawPanel ml;

    public static void main(String[] args)
    {
        MiniMusicPlayer mini = new MiniMusicPlayer();
        mini.go();
    }

    public void setUpGui()
    {
        ml = new MyDrawPanel();
        f.setContentPane(ml);
        f.setBounds(30,30,300,300);
        f.setVisible(true);
    }

    public void go()
    {
        setUpGui();
        try
        {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(ml, new int[] {127});
            
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r =0;
            for(int i=0; i<60; i+=4)
            {
                r=(int)((Math.random()*50)+1);
                track.add(makeEvent(144,1,r,100,i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,r,100,i+2));
            }
            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}