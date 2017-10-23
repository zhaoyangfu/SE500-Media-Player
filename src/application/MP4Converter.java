package application;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class MP4Converter {
	private String ffmpegApp;
	private String ffprobeApp;
	private int vidwidth;
	private int vidheight;

	public MP4Converter(String ffmpegApp, String ffprobeApp) {
		this.ffmpegApp = ffmpegApp;
	    this.ffprobeApp = ffprobeApp;
	}

 	public void convert(String filenameIn, String filenameOut) throws IOException, InterruptedException {
        convert(filenameIn, filenameOut, -1);
    }
 	
 	//Some unused code left over in here from when I was playing with differing conversion qualities. Quality is always defaulted to -1 (high as possible) within the program
    public int convert(String filenameIn, String filenameOut, int quality)
            throws IOException, InterruptedException {
	   	//Obtain the resolution of the video and output to the global variables
    	this.getMediaresolution(filenameIn);
    	//Build the command line process for the ffmpegApp - (ffmpeg -i <filenamein> -ar 44100 -s <video width> * <video height> -qscale <quality (lower is better)> <filenameout>)
	   	ProcessBuilder processBuilder;
	    if (quality > -1) {
	        processBuilder = new ProcessBuilder(ffmpegApp, "-i", filenameIn, "-ar", "44100",
	        "-s", this.vidwidth + "*" + this.vidheight, "-qscale", quality + "", filenameOut);
	    } else {
	        processBuilder = new ProcessBuilder(ffmpegApp, "-i", filenameIn, "-ar", "44100",
	        "-s", this.vidwidth + "*" + this.vidheight, filenameOut);	
	    }
	    
	    //Run the command line process
	    Process process = processBuilder.start();
	    
	    //loop the streamed converted data
	    InputStream stderr = process.getErrorStream();
	    InputStreamReader isr = new InputStreamReader(stderr);
	    BufferedReader br = new BufferedReader(isr);
	    String line;
	    while ((line = br.readLine()) != null) ;
	    {
	    }

	    return process.waitFor();
	    }
	    
    public void getMediaresolution(String filenameIn) throws IOException, InterruptedException {
	  	
    	//Build the command line process for ffprobe - (ffprobe -v error <file name in> -of flat=s=_ -select_streams v:0 -show_entries stream=width,height)
    	ProcessBuilder processBuilder;
	   	processBuilder = new ProcessBuilder(ffprobeApp, "-v", "error", filenameIn, "-of", "flat=s=_", "-select_streams", "v:0", "-show_entries", "stream=width,height");
	    
	   	//Run the command line process
	   	Process process = processBuilder.start();
	    
	   	//buffer the streamed data
	   	InputStream str = process.getInputStream();
	   	InputStreamReader isr = new InputStreamReader(str);
	   	BufferedReader br = new BufferedReader(isr);
	    
	   	//Read the two lines with width and height
	   	String widthline = br.readLine();
	   	String heightline = br.readLine();
	    
	   	//Process the strings to isolate the actual width and height info
	   	widthline=widthline.replaceAll("streams_stream_0_width=", "");
	   	heightline=heightline.replaceAll("streams_stream_0_height=", "");
	    
	   	//Convert the strings to the integer width and height values
	   	this.vidwidth = Integer.valueOf(widthline);
	   	this.vidheight = Integer.valueOf(heightline);

	}    
}



