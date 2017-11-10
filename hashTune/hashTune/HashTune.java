package hashTune;

import java.util.Random;
import java.util.Scanner;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * HashTune
 * 
 * @author jmdunton Music is the song generated my Mozart to be "Played", must
 *         be a string Volume is an integer between 0 and 127 Duration is the
 *         length of a given note
 */
public class HashTune {
	static String music;
	static int volume = 60;
	static int duration = 500;

	public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
		System.out.println("Type some string of words/characters, then press enter");
		Scanner console = new Scanner(System.in);
		String input = console.nextLine();
		console.close();
		Mozart diddy = new Mozart(input);
		music = diddy.writeMusic();
		System.out.println(music);
		playSong();
	}

	/**
	 * This method takes care of playing corresponding tones
	 * 
	 * @throws MidiUnavailableException
	 *             if no midi object or support
	 * @throws InterruptedException
	 *             if Thread.sleep is interrupted
	 */
	public static void playSong() throws MidiUnavailableException, InterruptedException {
		// made a synth
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		MidiChannel[] channels = synth.getChannels();
		Random rand = new Random();
		// notes[] array of midi codes in C major Pentatonic {0, C5, D5, E5, G5, A5, C6, D6, E6}
		int[] notes = { 0, 60, 62, 64, 67, 69, 72, 74, 76};
		String[] names = {"x", "C", "D", "E", "G", "A", "C", "D", "E"};
		for (char x : music.toCharArray()) {
			int note = Character.getNumericValue(x);
			// Shift pitch up one octave if a 9 is read in
			if (note == 9) {
				for (int i = 1; i < 8; i++) {
					notes[i] += 12;
				}
			}
			// Play pure tone if 0<Note<=8
			else if (note > 0 && note <= 8) {
				int octave = ((notes[note] / 12));
				System.out.print(names[(note % 12)] + octave + " " );
				//if octave is too low, make it mid.
				if(octave <= 0 ){
					 for (int i = 1; i < 8; i++) {
							notes[i] += 36;
					   }
				}
				channels[0].noteOn(notes[note], volume);
				Thread.sleep(duration);
				channels[0].noteOff(notes[note]);
			}
			// shift pitch down 1 octave if a zero is read in
			else if (note == 0) {
			   for (int i = 1; i < 8; i++) {
					notes[i] -= 12;
			   }
			}
		}
	}
}


