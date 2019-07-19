package com.graduation.entity;

public class Video {

	private int video_id;
	private String video_name;
	private String video_introduce;
	private int video_playtimes;
	private String video_video;
	private String video_image;
	private int video_commentnum;
	private String video_sort;
	private String video_region;
	private String video_type;
	private String video_danmu;

	public String getVideo_danmu() {
		return video_danmu;
	}

	public void setVideo_danmu(String video_danmu) {
		this.video_danmu = video_danmu;
	}

	public Video() {

	}

	public Video(String video_sort, String video_region, String video_type) {
		this.video_sort = video_sort;
		this.video_region = video_region;
		this.video_type = video_type;
	}

	public Video(String video_name) {
		this.video_name = video_name;
	}

	public String getVideo_sort() {
		return video_sort;
	}

	public void setVideo_sort(String video_sort) {
		this.video_sort = video_sort;
	}

	public String getVideo_region() {
		return video_region;
	}

	public void setVideo_region(String video_region) {
		this.video_region = video_region;
	}

	public String getVideo_type() {
		return video_type;
	}

	public void setVideo_type(String video_type) {
		this.video_type = video_type;
	}

	public int getVideo_commentnum() {
		return video_commentnum;
	}

	public void setVideo_commentnum(int video_commentnum) {
		this.video_commentnum = video_commentnum;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	public String getVideo_name() {
		return video_name;
	}

	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}

	public String getVideo_introduce() {
		return video_introduce;
	}

	public void setVideo_introduce(String video_introduce) {
		this.video_introduce = video_introduce;
	}

	public int getVideo_playtimes() {
		return video_playtimes;
	}

	public void setVideo_playtimes(int video_playtimes) {
		this.video_playtimes = video_playtimes;
	}

	public String getVideo_video() {
		return video_video;
	}

	public void setVideo_video(String video_video) {
		this.video_video = video_video;
	}

	public String getVideo_image() {
		return video_image;
	}

	public void setVideo_image(String video_image) {
		this.video_image = video_image;
	}

}
