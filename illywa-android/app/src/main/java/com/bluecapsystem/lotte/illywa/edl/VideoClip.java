package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;
import com.google.gson.annotations.Expose;

import java.util.Map;

public class VideoClip extends Clip {


	@Expose
	private String startTC = "00:00:00";


	@Expose
	private String endTC = "00:00:00";


	public VideoClip(final String filePath) {
		super(IDGenerator.createID(Clip.PREFIX_CLIP_ID));
		this.setType(ClipTypes.Video)
				.setFilePath(filePath);
	}

	@Override
	public Map<String, Object> getDetail() {

		return null;
	}
}
