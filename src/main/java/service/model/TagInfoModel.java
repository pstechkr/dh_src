package service.model;

public class TagInfoModel {
	
	private String tag_seq;
	private String tag_name;
	private String tag_image_default;
	private String tag_image_hover;
	private String tag_image_active;
	
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public String getTag_image_default() {
		return tag_image_default;
	}
	public void setTag_image_default(String tag_image_default) {
		this.tag_image_default = tag_image_default;
	}
	public String getTag_image_hover() {
		return tag_image_hover;
	}
	public void setTag_image_hover(String tag_image_hover) {
		this.tag_image_hover = tag_image_hover;
	}
	public String getTag_image_active() {
		return tag_image_active;
	}
	public void setTag_image_active(String tag_image_active) {
		this.tag_image_active = tag_image_active;
	}
	public String getTag_seq() {
		return tag_seq;
	}
	public void setTag_seq(String tag_seq) {
		this.tag_seq = tag_seq;
	}

}
