import javax.sound.midi.*;

public class MiniMusicApp {

    public static void main(String[] args) {
        MiniMusicApp mini = new MiniMusicApp ();
        mini.play();

    }

    public void play() {
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            Sequence seq = new Sequence(Sequence.PPQ,4);


            Track track = seq.createTrack();

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,42,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);


            ShortMessage b = new ShortMessage();
            b.setMessage(144,1,35,127);
            MidiEvent noteOff = new MidiEvent(b,4);
            track.add(noteOff);

            player.setSequence(seq);

            player.start();
            Thread.sleep(5000);
            player.stop();
            player.close();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

