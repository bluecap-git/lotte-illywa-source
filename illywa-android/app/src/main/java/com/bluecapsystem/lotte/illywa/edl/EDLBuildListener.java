package com.bluecapsystem.lotte.illywa.edl;

public interface EDLBuildListener {

	/**
	 * EDL 생성 완료 전 호출 되는 이벤트
	 *
	 * @param edl 생성될 EDL 정보
	 * @return 생성된 EDL
	 */
	EDL preEdlCreated(EDL edl);

	/**
	 * Clip 생성 전 호출되는 이벤트
	 *
	 * @param clip 생성될 클립
	 * @return 클립
	 */
	<T extends Clip> T preClipCreated(T clip);

	/**
	 * 레이어 생선전 호출되는 이벤트
	 *
	 * @param layer 생성될 layer
	 * @return Layer
	 */
	Layer preLayerCreated(Layer layer);

	/**
	 * Mashup 생성 전 호출 되는 이벤트
	 *
	 * @param mashup 생성될 Mashup
	 * @return Mashup
	 */
	Mashup preMashupCreated(Mashup mashup);
}
