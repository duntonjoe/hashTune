package hashTune;

import java.io.File;
import java.io.FileNotFoundException;
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
public class chromaticHashTune {
	static String input;
	static int volume = 60;
	static int duration = 500;
	/**
	 * Writing music:
	 * 0: C 
	 * 1: C# 
	 * 2: D 
	 * 3: D# 
	 * 4: E 
	 * 5: F 
	 * 6: F# 
	 * 7: G 
	 * 8: G# 
	 * 9: A
	 * A: A# 
	 * B: B 
	 * C: C 
	 * U: Up octave 
	 * D: Down Octave 
	 * L: Louder 
	 * Q: Quieter 
	 * F: Faster
	 * S: Slower
	 * 
	 * starts on 5th octave
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws MidiUnavailableException, InterruptedException, FileNotFoundException {
		System.out.println("enter song:");
		File file = new File("song.txt");
		Scanner console = new Scanner(file);
		input = console.next();
		console.close();
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
		// notes[] array of midi codes in C major Pentatonic {0, C5, D5, E5, G5,
		// A5, C6, D6, E6}
		int[] notes = { 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72 };
		String[] names = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
		for (char x : input.toCharArray()) {
			int note = Character.getNumericValue(x);
			// Shift pitch up one octave if U is read in
			if (note == 30) {
				for (int i = 0; i <= names.length; i++) {
					notes[i] += 12;
				}
			}
			// Shift pitch down 1 octave if D is read in
			else if (note == 13) {
				for (int i = 0; i <= names.length; i++) {
					notes[i] -= 12;
				}
			}
			// Shift volume up 10 if F is read in
			else if (note == 21) {
				volume += 20;
			}
			// Shift volume down 10 if P read in
			else if (note == 26) {
				volume -= 20;
			}
			// shorten notes by 100ms
			else if (note == 15) {
				duration -= 100;
			}
			// lengthen notes by 100ms
			else if (note == 28) {
				duration += 100;
			}
			// Play pure tone if 0<Note<=8
			else if (note > -1 && note < 13) {
				int octave = ((notes[note] / 12));
				System.out.print(names[(note % 12)] + octave + " ");
				// if octave is too low, make it mid.
				if (octave <= 0) {
					for (int i = 1; i < 8; i++) {
						notes[i] += 36;
					}
				}
				channels[0].noteOn(notes[note], volume);
				Thread.sleep(duration);
				channels[0].noteOff(notes[note]);
			}
		}
	}
}
