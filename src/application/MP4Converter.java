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

    public int convert(String filenameIn, String filenameOut, int quality)
            throws IOException, InterruptedException {
	   	this.getMediaresolution(filenameIn);
	   	ProcessBuilder processBuilder;
	    if (quality > -1) {
	        processBuilder = new ProcessBuilder(ffmpegApp, "-i", filenameIn, "-ar", "44100",
	        "-s", this.vidwidth + "*" + this.vidheight, "-qscale", quality + "", filenameOut);
	    } else {
	        processBuilder = new ProcessBuilder(ffmpegApp, "-i", filenameIn, "-ar", "44100",
	        "-s", this.vidwidth + "*" + this.vidheight, filenameOut);	
	    }

	    Process process = processBuilder.start();

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
	  	ProcessBuilder processBuilder;
	   	processBuilder = new ProcessBuilder(ffprobeApp, "-v", "error", filenameIn, "-of", "flat=s=_", "-select_streams", "v:0", "-show_entries", "stream=width,height");
	    	
	   	Process process = processBuilder.start();
	    	
	   	InputStream str = process.getInputStream();
	   	InputStreamReader isr = new InputStreamReader(str);
	   	BufferedReader br = new BufferedReader(isr);
	    	
	   	String widthline = br.readLine();
	   	String heightline = br.readLine();
	    	
	   	widthline=widthline.replaceAll("streams_stream_0_width=", "");
	   	heightline=heightline.replaceAll("streams_stream_0_height=", "");
	    	
	   	this.vidwidth = Integer.valueOf(widthline);
	   	this.vidheight = Integer.valueOf(heightline);

	}    
}



