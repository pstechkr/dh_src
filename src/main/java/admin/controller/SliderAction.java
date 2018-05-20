package admin.controller;

import javax.servlet.http.HttpServletRequest;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import service.model.SliderInfoModel;
import tiein.method.FileUpload;
import tiein.method.UploadModel;

@Controller
@RequestMapping("/admin")
public class SliderAction {
	
	@RequestMapping("/sliderRequest")
	public ModelAndView slider(HttpServletRequest request, @ModelAttribute SliderInfoModel sliderInfoModel){
		
		ModelAndView mav = new ModelAndView();
		String sliderMapper = "service.mapper.SliderMapper.";	// db setting
		boolean isCheck = false;
		
//		// file 경로 설정
//		String categoryPath = "slider";
//		String sequencePath = "index";
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
//			// IMAGE UPLOAD
//			if(!sliderInfoModel.getSlider_image_file().isEmpty())
//			{
//				// upload model setting
//				UploadModel uploadModel = new UploadModel();
//				uploadModel.setFile(sliderInfoModel.getSlider_image_file()); // file
//				uploadModel.setContextRoot(request.getSession().getServletContext().getRealPath("/"));
//				uploadModel.setCategoryPath(categoryPath);
//				uploadModel.setSequencePath(sequencePath);
//				
//				// FileUpload method
//				FileUpload fileUpload = new FileUpload();
//				uploadModel = fileUpload.writeFile(uploadModel);
//				
//				// isSuccess?
//				if(uploadModel.isSuccess())
//				{
//					try
//					{
//						// 경로 넣기.
//						sliderInfoModel.setSlider_image(uploadModel.getPic_url());
//
//						System.out.println("?");
//						// insert
//						session.insert(sliderMapper + "insert_sliderInfo", sliderInfoModel);
//						
//						System.out.println("?");
//						session.commit();
//						isCheck = true;
//						
//						
//					}catch(Exception e){
//						// 사진 정보 update 오류시에는 미리 업로드된 사진도 삭제해야 하므로, deletePath 경로 설정
//						String DeletePath =  request.getSession().getServletContext().getRealPath("/") + uploadModel.getPic_url();
//						// deleteFile 성공? 실패?
//						if(fileUpload.deleteFile(DeletePath))
//						{
//							// 삭제 성공
//						}else{
//							// 삭제 실패
//						}
//					}
//				}// isSuccess end
//			}// image_fileUpload end
			
			session.insert(sliderMapper + "insert_sliderInfo", sliderInfoModel);
			session.commit();
			isCheck = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		if(isCheck){
			String redirectURL = request.getSession().getServletContext().getContextPath();
			redirectURL += "/admin/slider";
			return new ModelAndView(new RedirectView(redirectURL));
		}
		
		return mav;
		
	}
}
