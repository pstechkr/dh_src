package tiein.method;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	// 생성자
	String div_name ="";
	public FileUpload(){
	}
	public FileUpload(String _name){
		div_name = _name;
	}
		
	public UploadModel writeFile(UploadModel uploadModel)
	{
		//input
		MultipartFile file = uploadModel.getFile();
		String contextRoot = uploadModel.getContextRoot();
		String categoryPath = uploadModel.getCategoryPath();
		String sequencePath = uploadModel.getSequencePath();

		/* output
		 * boolean success  
		 * string pic_url
		 */
		UploadModel output = new UploadModel();
		boolean _success = false;
		String _pic_url = "";
		
		// 파일 이름 및 경로 설정
		String originalFileName = file.getOriginalFilename().trim(); // 원본 파일 이름 trim
		String changedFileName = "";
		if(!div_name.equals("")){
			changedFileName = fileName(originalFileName, categoryPath, sequencePath, div_name); // 변경된 파일명 (년월일초_카테고리Path_시컨스Path.확장자)
		}else{
			changedFileName = fileName(originalFileName, categoryPath, sequencePath); // 변경된 파일명 (년월일초_카테고리Path_시컨스Path.확장자)
		}
		String originalLocation = contextRoot + File.separator + "resources" + File.separator  + "file" + File.separator 
								+ categoryPath + File.separator + sequencePath + File.separator + changedFileName; // 원본 파일 경로
		String dbLocation = "resources" + File.separator + "file" + File.separator
								+ categoryPath + File.separator + sequencePath + File.separator + changedFileName;// DB에 들어갈 원본 파일 경로

		// 파일 경로 없는 경우 새로 생성
		File dir = new File(originalLocation + "/../");
    	if(!dir.exists()) dir.mkdirs();
		
		
		try{
    		file.transferTo(new File(originalLocation));
    		_success = true;
			_pic_url= dbLocation;
    	}
    	catch(Exception e){
	    	e.printStackTrace();
	    	// 오류인 경우 업로드 된 파일을 삭제한다.
    		File fd = new File(originalLocation);
	    	fd.delete();
	    	_success = false;
			_pic_url= "";
    	}
		
		output.setSuccess(_success);
		output.setPic_url(_pic_url);
		
		return output;

	}
	
	public boolean deleteFile(String deletePath){
		
		boolean _success = false;
		
		try{
			File fd = new File(deletePath);
	    	fd.delete();
    		_success = true;
    	}
    	catch(Exception e){
	    	e.printStackTrace();
	    	// 오류인 경우 업로드 된 파일을 삭제한다.
	    	_success = false;
    	}
		
		return _success;
	}
	
		  
	// 파일 이름 변경  
	private String fileName(String fileName, String categoryPath, String sequencePath){
		
		String reFileName = "";
		
		int pathPoint = fileName.lastIndexOf(".");
		String filePoint = fileName.substring(pathPoint + 1, fileName.length()); // . 찾기
		
		String fileType = filePoint.toLowerCase(); // 확장자
		
		String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());  
		reFileName = now + "_" + categoryPath + "_" + sequencePath + "." +fileType;

		return reFileName; 
	}
	
	private String fileName(String fileName, String categoryPath, String sequencePath, String div_name){
		
		String reFileName = "";
		
		int pathPoint = fileName.lastIndexOf(".");
		String filePoint = fileName.substring(pathPoint + 1, fileName.length()); // . 찾기
		
		String fileType = filePoint.toLowerCase(); // 확장자
		
		String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());  
		reFileName = now + "_" + categoryPath + "_" + sequencePath + "_" + div_name + "." + fileType;

		return reFileName; 
	}
}
