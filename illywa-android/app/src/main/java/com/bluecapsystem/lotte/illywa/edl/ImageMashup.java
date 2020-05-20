package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ImageMashup extends Mashup<ImageClip> {


	/**
	 * @param clip 원본 clip
	 */
	public ImageMashup(final ImageClip clip) {
		super(clip, MashupTypes.Image);
	}


	@NonNull
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
