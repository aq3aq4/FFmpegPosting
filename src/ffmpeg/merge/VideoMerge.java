package ffmpeg.merge;

import java.io.IOException;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

public class VideoMerge {
	private static final String inputPath = "/Users/birdhead/Desktop/videoTest/mergeInfo.txt"; 
	private static final String outputPath = "/Users/birdhead/Desktop/videoTest/";
	
	public static void main(String[] args) throws IOException {
		FFmpeg ffmpeg = new FFmpeg("/usr/local/bin/ffmpeg");
		FFprobe ffprobe = new FFprobe("/usr/local/bin/ffprobe");
		
		FFmpegBuilder builder = new FFmpegBuilder()
				.overrideOutputFiles(true)
				.addInput(inputPath)
				.addExtraArgs("-f", "concat")
				.addExtraArgs("-safe", "0")
				.addOutput(outputPath + "mergeVideo.mp4")
				.done();
		
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
		executor.createJob(builder).run();
	}
}
