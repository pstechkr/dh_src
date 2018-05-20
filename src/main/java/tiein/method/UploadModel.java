package tiein.method;
import org.springframework.web.multipart.MultipartFile;

public class UploadModel {
	
	//write input
	private MultipartFile file;
	private String contextRoot;
	private String categoryPath;
	private String sequencePath;
	
	//output
	private boolean success = false;
	private String pic_url;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getContextRoot() {
		return contextRoot;
	}
	public void setContextRoot(String contextRoot) {
		this.contextRoot = contextRoot;
	} 
	public String getCategoryPath() {
		return categoryPath;
	}
	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}
	public String getSequencePath() {
		return sequencePath;
	}
	public void setSequencePath(String sequencePath) {
		this.sequencePath = sequencePath;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
}
