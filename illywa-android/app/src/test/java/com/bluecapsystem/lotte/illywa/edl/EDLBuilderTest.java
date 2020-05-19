package com.bluecapsystem.lotte.illywa.edl;

import com.bluecapsystem.lotte.illywa.common.collectors.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.*;

import java.util.List;


public class EDLBuilderTest {


	EDLBuilder builder;
	EDL edl;

	@Before
	public void init() {
		EDLBuilder.setPreCreatedListener(new BuilderEvents());
		ToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);
		this.builder = new EDLBuilder();
		this.edl = this.builder.newEDL();

	}


	@After
	public void printEdl() {
		final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.out.println("===> EDL JSON");
		System.out.println(gson.toJson(this.edl));
	}


	@Ignore
	@Test
	public void clipCreateTest() {
		this.edl
				.addClip(builder.newVideoClip(""))
				.addClip(builder.newVideoImageClip(""));

		List<Clip> clips;
		final ClipFindCondition condition = new ClipFindCondition();
		clips = this.edl.getClips().findList(condition);
		Assert.assertEquals("clip count not same", 2, clips.size());

		// 유형 검색 조건 추가
		condition.setTypes(Sets.newSet(ClipTypes.Video));
		clips = this.edl.getClips().findList(condition);
		Assert.assertEquals("video clip count not same", 1, clips.size());

		final Clip clip = this.edl.getClips().get(0);
		final Clip foundClip = this.edl.getClips().findOne(clip.getClipId());
		Assert.assertEquals("found clip id is not same", clip.getClipId(), foundClip.getClipId());
	}


	@Test
	public void mashupCreateTest() {
		this.clipCreateTest();

		final Layer videoLayer = this.edl.getLayers() //
				.findList(new LayerFindCondition().setTypes(Sets.newSet(LayerTypes.Video))) //
				.stream().findFirst().orElseGet(null);
		Assert.assertNotNull("video layer is null", videoLayer);


		// mashup 추가
		this.edl.getClips() //
				.findList(new ClipFindCondition().setTypes(Sets.newSet(ClipTypes.Video, ClipTypes.VideoImage))) //
				.stream().forEach(clip -> {
			final VideoClip video = (VideoClip) clip;
			final VideoMashup mashup = this.builder.newVideoMashup(video, video.getStartTC(), video.getEndTC());
			videoLayer.getMashups().add(mashup);
		});


		// mashup 삽입
		this.edl.getClips() //
				.findList(new ClipFindCondition().setTypes(Sets.newSet(ClipTypes.Video, ClipTypes.VideoImage))) //
				.stream().forEach(clip -> {
			final VideoClip video = (VideoClip) clip;
			final VideoMashup mashup = this.builder.newVideoMashup(video, video.getStartTC(), video.getEndTC());
			videoLayer.getMashups().add(0, mashup);
		});

	}


	@Ignore
	@Test
	public void newEDLTest() {
		Assert.assertNull("edl id is null", edl.getEdlId());
	}


	private static class BuilderEvents implements EDLBuildListener {
		@Override
		public EDL preEdlCreated(final EDL edl) {
			edl.setTag("TEST");
			System.out.println("=======!!!!! preEdlCreated ");
			return edl;
		}

		@Override
		public <T extends Clip> T preClipCreated(final T clip) {

			System.out.println("=======!!!!! preClipCreated ");


			switch (clip.getType()) {
				case Video:
				case VideoImage:
					final VideoClip videoClip = (VideoClip) clip;
					videoClip.setStartTC("00:00:00.000");
					videoClip.setEndTC("00:10:00.000");
					break;
				default:
					break;
			}
			return clip;
		}

		@Override
		public Layer preLayerCreated(final Layer layer) {
			System.out.println("=======!!!!! preLayerCreated ");
			return null;
		}

		@Override
		public Mashup preMashupCreated(final Mashup mashup) {
			System.out.println("=======!!!!! preMashupCreated ");
			return mashup;
		}
	}
}