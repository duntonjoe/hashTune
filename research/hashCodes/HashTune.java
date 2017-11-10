package hashCodes;

import java.util.Scanner;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class HashTune {
	static String music;
	static int volume = 80;
	static int duration = 300;
	
	public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
		System.out.println("Type some string of words/characters, then press enter");
		Scanner console = new Scanner(System.in);
		String input = console.nextLine();
		console.close();
		HashMusic diddy = new HashMusic(input);
		music = diddy.writeMusic();
		System.out.println(music);
		playSong();
	}
	
	public static void playSong() throws MidiUnavailableException, InterruptedException{
		Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        MidiChannel[] channels = synth.getChannels();
		int[] notes = {0, 48, 50, 52, 54, 56, 58, 60, 62};
		for(char x : music.toCharArray()){
			int note = Character.getNumericValue(x);
			if(note == 9){
				channels[9].noteOn( 38, volume );
		        Thread.sleep( duration );
		        channels[9].noteOff( 38 );
			}
			else if (note > 0 && note <= 8 ){
				channels[0].noteOn( notes[note], volume );
		        Thread.sleep( duration );
		        channels[0].noteOff( notes[note] );
			}
			else if (note == 0){
				duration *= 1.1;
			}
			System.out.print(x);
			
		}
	}
	

}
