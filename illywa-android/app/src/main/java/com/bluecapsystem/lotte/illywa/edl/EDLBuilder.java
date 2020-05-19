package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.common.utils.IDGenerator;
import com.bluecapsystem.lotte.illywa.common.utils.TimeUtils;

import java.util.Optional;

/**
 * EDL 을 생성 로드 하고
 * Layer 와 Clip 을 생성 한다
 */
public class EDLBuilder {

	/** EDL 아이디 앞에 붙는 prefix 문자 */
	private final static String PREFIX_EDL_ID = "EDL";

	/** edl 아이쳄 생성 이벤트를 처리할 리스너 */
	private static EDLBuildListener preCreatedListener;

	/**
	 * 생성 이벤트를 처리할 리스터를 등록 한다
	 *
	 * @param pre 이벤트 처리 리스너
	 */
	public static void setPreCreatedListener(final EDLBuildListener pre) {
		preCreatedListener = pre;
	}

	/**
	 * 새로우 EDL 을 생성 한다
	 *
	 * @return 새로 생성된 edl
	 */
	public EDL newEDL() {
		final EDL newEdl = new EDL(IDGenerator.createID(EDLBuilder.PREFIX_EDL_ID));

		// 기본 layer 목록을 생성 한다

		final Layer basisLayer = newLayer(LayerTypes.Video);
		newEdl.getLayers().add(basisLayer);
		newEdl.getLayers().add(newLayer(LayerTypes.Audio));
		newEdl.getLayers().add(newLayer(LayerTypes.Subscript));
		newEdl.getLayers().add(newLayer(LayerTypes.Image));

		// 기초가 기반이 되는 Layer 를 설정 한다
		newEdl.getLayers().setBasisLayer(basisLayer);

		return Optional.ofNullable(preCreatedListener).map(event -> event.preEdlCreated(newEdl)).orElse(newEdl);
	}

	/**
	 * {@link VideoClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link VideoClip} 을 반환 한다
	 */
	public VideoClip newVideoClip(final String filePath) {
		final VideoClip clip = new VideoClip(filePath);
		return Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preClipCreated(clip)) //
				.orElse(clip);

	}

	/**
	 * {@link AudioClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link AudioClip} 을 반환 한다
	 */
	public AudioClip newAudioClip(final String filePath) {
		final AudioClip clip = new AudioClip(filePath);
		return Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preClipCreated(clip)) //
				.orElse(clip);
	}

	/**
	 * {@link VideoImageClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link VideoImageClip} 을 반환 한다
	 */
	public VideoImageClip newVideoImageClip(final String filePath) {
		final VideoImageClip clip = new VideoImageClip(filePath);
		return Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preClipCreated(clip)) //
				.orElse(clip);

	}

	/**
	 * {@link SubscriptClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link SubscriptClip} 을 반환 한다
	 */
	private SubscriptClip newSubscriptClip(final String filePath) {
		final SubscriptClip clip = new SubscriptClip(filePath);
		return Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preClipCreated(clip)) //
				.orElse(clip);
	}

	/**
	 * {@link ImageClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link ImageClip} 을 반환 한다
	 */
	private ImageClip createImageClip(final String filePath) {
		final ImageClip clip = new ImageClip(filePath);
		return Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preClipCreated(clip)) //
				.orElse(clip);

	}

	/**
	 * {@link Mashup} 을 생성 한다
	 *
	 * @param sourceClip 원본 Clip
	 * @param type       매쉬업 유형
	 * @param start      원본의 시작 시간
	 * @param end        원본의 끝시간
	 * @param <T>        생성된 Mashup 유형
	 * @return 생성된 Mashup
	 */
	public <T extends Mashup> T newMashup( //
			final Clip sourceClip, final MashupTypes type, //
			final Long start, final Long end) {

		final Mashup newMashup = new Mashup(sourceClip, type);

		return (T) Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preMashupCreated(newMashup)) //
				.orElse(newMashup);
	}


	public VideoMashup newVideoMashup( //
			final VideoClip clip, final String start, final String end) {


		final VideoMashup newMashup = new VideoMashup(clip);

		newMashup.setClipStartTC(start);
		newMashup.setClipEndTC(end);
		newMashup.setStartTC("00:00:00.000");
		newMashup.setEndTC(TimeUtils.getDuration(start, end));

		return Optional.ofNullable(preCreatedListener) //
				.map(event -> (VideoMashup) event.preMashupCreated(newMashup)) //
				.orElse(newMashup);
	}

	/**
	 * 새로운 Layer 를 생성 한다
	 *
	 * @param type 생헝 하려는 layer 유형
	 * @param <T>  return Type
	 * @return layer
	 */
	public <T extends Layer> T newLayer(final LayerTypes type) {
		final Layer newLayer = new Layer(type);

		return (T) Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preLayerCreated(newLayer)) //
				.orElse(newLayer);
	}

}
