package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;

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
	 * create a new clip
	 *
	 * @param type     clip type
	 * @param filePath clip target file path
	 * @param <T>      return clip type
	 * @return created a new clip
	 */
	public <T extends Clip> T newClip(final ClipTypes type, final String filePath) {
		T newClip = null;
		switch (type) {
			case Video:
				newClip = (T) newVideoClip(filePath);
				break;
			case VideoImage:
				newClip = (T) newVideoImageClip(filePath);
				break;
			case Audio:
				newClip = (T) newAudioClip(filePath);
				break;
			case Subscript:
				newClip = (T) newSubscriptClip(filePath);
				break;
			case Image:
				newClip = (T) createImageClip(filePath);
				break;
			default:
				throw new RuntimeException("Not supported clip type");
		}
		// final 객체로 바꾸기 위해서..
		final T retClip = newClip;
		return Optional.ofNullable(preCreatedListener) //
				.map(event -> event.preClipCreated(retClip)) //
				.orElse(newClip);
	}

	/**
	 * {@link VideoClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link VideoClip} 을 반환 한다
	 */
	private VideoClip newVideoClip(final String filePath) {
		return new VideoClip(filePath);
	}

	/**
	 * {@link AudioClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link AudioClip} 을 반환 한다
	 */
	private AudioClip newAudioClip(final String filePath) {
		return new AudioClip(filePath);
	}

	/**
	 * {@link VideoImageClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link VideoImageClip} 을 반환 한다
	 */
	private VideoImageClip newVideoImageClip(final String filePath) {
		return new VideoImageClip(filePath);
	}

	/**
	 * {@link SubscriptClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link SubscriptClip} 을 반환 한다
	 */
	private SubscriptClip newSubscriptClip(final String filePath) {
		return new SubscriptClip(filePath);
	}

	/**
	 * {@link ImageClip} 을 생성 한다
	 *
	 * @param filePath 클립 파일 경로
	 * @return {@link ImageClip} 을 반환 한다
	 */
	private ImageClip createImageClip(final String filePath) {
		return new ImageClip(filePath);
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
