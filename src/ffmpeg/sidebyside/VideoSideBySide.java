package ffmpeg.sidebyside;

import java.io.IOException;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

public class VideoSideBySide {
	private static final String inputPath = "/Users/birdhead/Desktop/videoTest/mergeVideo.mp4";
	private static final String outputPath = "/Users/birdhead/Desktop/videoTest/sideBySide.mp4";;
	public static void main(String[] args) throws IOException {
		FFmpeg ffmpeg = new FFmpeg("/usr/local/bin/ffmpeg");
		FFprobe ffprobe = new FFprobe("/usr/local/bin/ffprobe");
		
		FFmpegBuilder builder = new FFmpegBuilder()
				.overrideOutputFiles(true)
				.addInput(inputPath)
				.addInput(inputPath)
				.addOutput(outputPath)
				.addExtraArgs("-preset", "ultrafast")
				.addExtraArgs("-filter_complex", "[0:v]setpts=PTS-STARTPTS, pad=iw*2+5:ih[bg]; [1:v]setpts=PTS-STARTPTS[fg]; [bg][fg]overlay=w+10")
				.done();
		
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
		executor.createJob(builder).run();
	}
}
