package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;

/**
 * EDL 을 생성 로드 하고
 * Layer 와 Clip 을 생성 한다
 */
public class EDLBuilder {

	/** EDL 아이디 앞에 붙는 prefix 문자 */
	private final static String PREFIX_EDL_ID = "EDL";

	/** clip 아이디 앞에 붙는 prefix 문자 */
	private final static String PREFIX_CLIP_ID = "CLP";

	/**
	 * 새로우 EDL 을 생성 한다
	 *
	 * @return 새로 생성된 edl
	 */
	public EDL createEDL() {
		return new EDL(IDGenerator.createID(EDLBuilder.PREFIX_EDL_ID));
	}

	/**
	 * create a new clip
	 *
	 * @param type     clip type
	 * @param filePath clip target file path
	 * @return created a new clip
	 */
	public Clip createClip(final ClipTypes type, final String filePath) {
		Clip newClip = null;
		switch (type) {
			case Video:
				newClip = createVideoClip(filePath);
				break;
			default:
				throw new RuntimeException("Not supported clip type");
		}

		return newClip;
	}

	/**
	 * video clip 을 생성 한다
	 *
	 * @param filePath 클립 대상 파일 경로
	 * @return video clip 을 반환 한다
	 */
	private VideoClip createVideoClip(final String filePath) {
		return new VideoClip(IDGenerator.createID(EDLBuilder.PREFIX_CLIP_ID), filePath);
	}

	
}
