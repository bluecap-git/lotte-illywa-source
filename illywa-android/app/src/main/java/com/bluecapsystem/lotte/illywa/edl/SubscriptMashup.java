package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SubscriptMashup extends Mashup<SubscriptClip> {

	/** 임시 이미지 파일 경로 */
	private String imagePath;

	/** 자막 문구 */
	private String text;


	/**
	 * @param clip video clip
	 */
	public SubscriptMashup(final SubscriptClip clip) {
		super(clip, MashupTypes.Subscript);
	}


	@NonNull
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return 자막 이미지 경로
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath 자막 이미지 경로
	 * @return this
	 */
	public SubscriptMashup setImagePath(final String imagePath) {
		this.imagePath = imagePath;
		return this;
	}

	/**
	 * @return 자막 문구
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text 자막 문구
	 * @return this
	 */
	public SubscriptMashup setText(final String text) {
		this.text = text;
		return this;
	}
}
