package ffmpeg.timecut;

import java.io.IOException;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

public class VideoTimeCut {
	/**
	 * 입력 영상의 구간을 잘라서 새로운 영상을 만드는 FFMPEG 명령어를 자바로 
	 * 구현 한 소스
	 * @param args
	 */
	private static final String inputPath = "/Users/birdhead/Desktop/videoTest/pitching.mp4"; 
	private static final String outputPath = "/Users/birdhead/Desktop/videoTest/";
	
	public static void main(String[] args) throws IOException {
		FFmpeg ffmpeg = new FFmpeg("/usr/local/bin/ffmpeg");
		FFprobe ffprobe = new FFprobe("/usr/local/bin/ffprobe");
		
		//다음번 영상 merge를 위해서 3개의 영상 생성
		for(int i=0; i<3; i++) {
			FFmpegBuilder builder = new FFmpegBuilder()
					.overrideOutputFiles(true)
					.addInput(inputPath)	 //입력 영상 경로의
					.addExtraArgs("-ss", String.valueOf(i)) //영상의 i초 위치 부
					.addExtraArgs("-t", "3") //3초 동안 재생한 영상
					.addOutput(outputPath + "pitching_out" + i + ".mp4") //outputpath 위치에
					.addExtraArgs("-an") //영상의 소리를 제거하고
					.done();	//저장
			
			FFmpegExecutor excutor = new FFmpegExecutor(ffmpeg, ffprobe);
			excutor.createJob(builder).run();
			System.out.println("main end");
		}
		
	}
}
