package createBatch;
/* Alexander Fields http://www.perilousgames.com 
 * my github: https://github.com/roku674
 */
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File; //how u get stuff in
import java.io.FileInputStream; //from user
import java.io.FileNotFoundException; //error handiling
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors; //Commander shepard destroyed those
import javax.swing.JOptionPane;

public class CreateBatch {
	
	static String username, crypto;
	static int cpu, gpuAmount;
	static boolean gpu;
		
	public static void main(String[] args) throws IOException {
		
		SetUsername();
		SetCrypto();
		SetCPU();
		SetGPU();
		
		File file = new File("runMinergate-cli.bat");
		 
		//Create the file
		if (file.createNewFile()){
		System.out.println("File is created!");
		}
		else{
		file.delete();
		System.out.println("File appended.");
		}

		//Write Content
		FileWriter writer = new FileWriter(file);
		
		writer.write("minergate-cli --user " + username + " --" + crypto + " " + (cpu+1) + " " + GetGPU());
		
		writer.close();
	}
	
	private static void SetUsername(){
		
		Component frame = null;
		
		 username = JOptionPane.showInputDialog(
                frame,
                "Please type in your username",
                "Username",
                JOptionPane.PLAIN_MESSAGE);				
		 
		 if(username.equals(null)) {
			 SetUsername();
		 }
		 else if (username.equals("")) {
			 SetUsername();
		 }		                
	}
	

	private static void SetCrypto() {
		
		Component frame = null;
		
		 crypto = JOptionPane.showInputDialog(
               frame,
               "Please type in the cryptocurrency's acronym such as zec or xmr",
               "Cryptocurrency type",
               JOptionPane.PLAIN_MESSAGE);				
		 
		 if(username.equals(null)) {
			 SetCrypto();
		 }
		 else if (username.equals("")) {
			 SetCrypto();
		 }
	}
	
	private static void SetCPU() {
		
		Component frame = null;
		
		Object[] options = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
		 cpu = JOptionPane.showOptionDialog(frame, 
				 "Please select amount of CPU Cores you wish to allocate", "CPU Cores", 
				 JOptionPane.YES_NO_CANCEL_OPTION, 
				 JOptionPane.QUESTION_MESSAGE, 
				 null, 
				 options, 
				 options[15]);		
	}
	
	private static void SetGPU() {
		
		int dialogButton =JOptionPane.showConfirmDialog (null, 
				"Do you want to use your GPU?",
				"GPU mining",
				
				JOptionPane.YES_NO_OPTION);
		if (dialogButton == JOptionPane.YES_OPTION) {
			gpu = true;
			
			Component frame = null;

			Object[] options = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
			 gpuAmount = JOptionPane.showOptionDialog(frame, 
					 "Please select amount of GPUs you wish to allocate", "GPU amount", 
					 JOptionPane.YES_NO_CANCEL_OPTION, 
					 JOptionPane.QUESTION_MESSAGE, 
					 null, 
					 options, 
					 options[15]);	
		}
		else {
			gpu = false;
		}		
		
	}
	
	private static String GetGPU(){
		String temp = "";
		
		for(int i = 0; i <= gpuAmount; i++) {
			temp += "--gpu" + " " + i + " ";
		}
			
		return temp;
	}
}
