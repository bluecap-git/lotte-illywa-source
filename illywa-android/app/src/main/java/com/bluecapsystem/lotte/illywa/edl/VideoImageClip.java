package com.bluecapsystem.lotte.illywa.edl;

import java.util.Map;

public class VideoImageClip extends VideoClip {

	public VideoImageClip(final String filePath) {
		super(filePath);
		this.setType(ClipTypes.VideoImage);
		
	}

	@Override
	public Map<String, Object> getDetail() {
		return null;
	}

}
