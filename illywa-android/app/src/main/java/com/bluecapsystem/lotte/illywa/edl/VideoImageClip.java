package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;
import com.bluecapsystem.lotte.illywa.edl.utils.TimeUtils;

import java.util.Map;

public class VideoImageClip extends Clip {

	private String startTC;

	private String endTC;

	public VideoImageClip(final String filePath) {
		super(IDGenerator.createID(Clip.PREFIX_CLIP_ID));
		this.setType(ClipTypes.VideoImage)
				.setFilePath(filePath);
	}

	@Override
	public Map<String, Object> getDetail() {
		return null;
	}

	public String getDuration() {
		return TimeUtils.getDuration(this.startTC, this.endTC);
	}
}
