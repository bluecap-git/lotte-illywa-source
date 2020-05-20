package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.common.utils.IDGenerator;

import java.util.Map;

public class AudioClip extends Clip {


	public AudioClip(final String filePath) {
		super(IDGenerator.createID(Clip.PREFIX_CLIP_ID));
		this.setType(ClipTypes.Audio)
				.setFilePath(filePath);
	}


	@Override
	public Map<String, Object> getDetail() {

		return null;
	}
}
