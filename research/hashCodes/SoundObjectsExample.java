package hashCodes;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class SoundObjectsExample {

	public static void main(String[] args) {
		int channel = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments

	    int volume = 80; // between 0 et 127
	    int duration = 1000; // in milliseconds

	    try {
	        Synthesizer synth = MidiSystem.getSynthesizer();
	        synth.open();
	        MidiChannel[] channels = synth.getChannels();

	        // --------------------------------------
	        // Play a few notes.
	        // The two arguments to the noteOn() method are:
	        // "MIDI note number" (pitch of the note),
	        // and "velocity" (i.e., volume, or intensity).
	        // Each of these arguments is between 0 and 127.
	        channels[channel].noteOn( 60, volume ); // C note
	        Thread.sleep( duration );

	        Thread.sleep( 500 );

	        // --------------------------------------
	        // Play a C major chord.
	        channels[channel].noteOn( 60, volume ); // C
	        channels[channel].noteOn( 64, volume ); // E
	        channels[channel].noteOn( 67, volume ); // G
	        Thread.sleep( 3000 );
	        channels[channel].allNotesOff();
	        Thread.sleep( 500 );



	        synth.close();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
