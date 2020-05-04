package com.bluecapsystem.lotte.illywa.edl;

import java.util.Map;

public class VideoClip extends Clip {


	public VideoClip(final String clipId, final String filePath) {
		super();
		this.setType(ClipTypes.Video)
				.setFilePath(filePath);
	}


	@Override
	public Map<String, Object> getDetail() {

		return null;
	}
}
