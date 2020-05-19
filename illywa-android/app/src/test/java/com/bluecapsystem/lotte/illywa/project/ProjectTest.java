package com.bluecapsystem.lotte.illywa.project;

import com.bluecapsystem.lotte.illywa.common.collectors.Sets;
import com.bluecapsystem.lotte.illywa.common.utils.TimeUtils;
import com.bluecapsystem.lotte.illywa.edl.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ProjectTest {


	Project project;

	EDLBuilder edlBuilder;

	@Before
	public void init() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);

		edlBuilder = new EDLBuilder();
		project = new Project("TEST_PROJECT");
	}


	@After
	public void printEdl() {
		final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.out.println("===> PROEJCT JSON");
		System.out.println(this.project.toJson());
	}


	@Test
	public void project_test() {

		// 새로운 Tag 를 생성 한다
		final Tag newTag = new Tag("1111", "상품 상단");
		newTag.getVideoFiles().add("/fake_path/test_a.mp4");
		newTag.getVideoFiles().add("/fake_path/test_b.mp4");

		// project 에 Tag 정보를 추가 한다
		this.project.getTags().add(newTag);

		newTag.getVideoFiles().forEach(video -> {

			final VideoClip newClip = edlBuilder.newVideoClip(video);

			// edl event 를 설정 안해서 임시로 넣어 줌
			newClip.setStartTC(TimeUtils.DEFAULT_TC);
			newClip.setEndTC("00:10:00.000");

			newTag.getEdl().addClip(newClip);

			final VideoMashup mashup = edlBuilder.newVideoMashup(newClip, TimeUtils.DEFAULT_TC, "00:02:00.000");

			// video layer 를 찾는다.
			final Layer layer = newTag.getEdl().getLayers()//
					.findList(new LayerFindCondition().setTypes(Sets.newSet(LayerTypes.Video)))
					.stream().findFirst().get();

			// mashup 을 추가 한다
			layer.getMashups().add(mashup);
		});
	}


}