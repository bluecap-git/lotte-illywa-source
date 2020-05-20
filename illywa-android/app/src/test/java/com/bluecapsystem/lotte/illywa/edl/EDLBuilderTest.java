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

		ToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);

		// builder 이벤트 추가
		EDLBuilder.setPreCreatedListener(new BuilderEvents());

		this.builder = new EDLBuilder();
		this.edl = this.builder.newEDL();

		// edl post event 처리 방식
		this.edl.getClips().addEventPostAdded((e, clip) -> {
			System.out.println(" EDL (" + e.getEdlId() + ") 에 클립이 추가 !! => " + clip.getClipId());
		});

		this.edl.getClips().addEventPostRemoved((e, clip) -> {
			System.out.println(" EDL (" + e.getEdlId() + ") 에 클립이 삭제 !! => " + clip.getClipId());
		});

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
		// 클립을 생성한다
		this.clipCreateTest();

		// edl 을 생성하면 기본 VideoLayer 를 추가해 준다
		final Layer videoLayer = this.edl.getLayers().getBasisLayer();
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
		// video mashup 의 경우 삽입하는 mashup 뒤의 모든 항목을 추가되는 mashup 시간 만큼 뒤로 미룬다
		this.edl.getClips() //
				.findList(new ClipFindCondition().setTypes(Sets.newSet(ClipTypes.Video, ClipTypes.VideoImage))) //
				.stream().forEach(clip -> {
			final VideoClip video = (VideoClip) clip;
			final VideoMashup mashup = this.builder.newVideoMashup(video, video.getStartTC(), video.getEndTC());
			videoLayer.getMashups().add(0, mashup);
		});


		// mashup 을 삭제 한다
		this.edl.getLayers().getBasisLayer().getMashups().remove(0);
		Assert.assertEquals("Mashup size is too many", //
				3, this.edl.getLayers().getBasisLayer().getMashups().size());

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
			// clip 생성 후 해당 클립의 meta 정보를 셋팅해 줘야 한다
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