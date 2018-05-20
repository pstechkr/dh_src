package service.model;



public class SliderInfoModel {

//	private MultipartFile slider_image_file;
	private String slider_image;
	private String slider_link;
	private String slider_comment;
	
	
	public String getSlider_image() {
		return slider_image;
	}
	public void setSlider_image(String slider_image) {
		this.slider_image = slider_image;
	}
	public String getSlider_link() {
		return slider_link;
	}
	public void setSlider_link(String slider_link) {
		this.slider_link = slider_link;
	}
	public String getSlider_comment() {
		return slider_comment;
	}
	public void setSlider_comment(String slider_comment) {
		this.slider_comment = slider_comment;
	}
}
