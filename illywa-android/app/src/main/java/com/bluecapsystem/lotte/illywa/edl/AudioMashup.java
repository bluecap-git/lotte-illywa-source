package com.bluecapsystem.lotte.illywa.edl;

import androidx.annotation.NonNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AudioMashup extends Mashup<AudioClip> {


	/**
	 * @param clip 원본 clip
	 */
	public AudioMashup(final AudioClip clip) {
		super(clip, MashupTypes.Audio);
	}

	@NonNull
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
