package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.edl.utils.IDGenerator;

import java.util.Map;

public class SubscriptClip extends Clip {


	public SubscriptClip(final String filePath) {
		super(IDGenerator.createID(Clip.PREFIX_CLIP_ID));
		this.setType(ClipTypes.Subscript)
				.setFilePath(filePath);
	}


	@Override
	public Map<String, Object> getDetail() {

		return null;
	}
}
