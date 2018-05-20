package gathering.controller;

import gathering.model.GatheringInfoModel;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import tiein.method.FileUpload;
import tiein.method.UploadModel;

@Controller
@RequestMapping("/admin/gathering")
public class AdminGatheringAction {
	
	@RequestMapping("/writeRequest")
	ModelAndView writeRequest(@ModelAttribute GatheringInfoModel gatheringInfoModel, HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";	// db setting 
		boolean isCheck = false;
		
		// file 경로 설정
//		String categoryPath = "gathering";

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			// 기본사항 insert
			session.insert(gatheringMapper + "insert_gatheringInfo", gatheringInfoModel);
			// 내용 insert
//			session.update(gatheringMapper + "update_gatheringInfo_instruction", gatheringInfoModel);
			
			
			
			
			
			
			
//			// MAIN IMAGE UPLOAD
//			if(!gatheringInfoModel.getGathering_main_image_file().isEmpty())
//			{
//				// upload model setting
//				UploadModel uploadModel = new UploadModel();
//				uploadModel.setFile(gatheringInfoModel.getGathering_main_image_file()); // file
//				uploadModel.setContextRoot(request.getSession().getServletContext().getRealPath("/"));
//				uploadModel.setCategoryPath(categoryPath);
//				uploadModel.setSequencePath(gatheringInfoModel.getGathering_seq());
//				
//				// FileUpload method
//				FileUpload fileUpload = new FileUpload("main");
//				uploadModel = fileUpload.writeFile(uploadModel);
//				
//				// isSuccess?
//				if(uploadModel.isSuccess())
//				{
//					try{
//						// 경로 넣기.
//						gatheringInfoModel.setGathering_main_image(uploadModel.getPic_url());
//						// 사진 정보 update
//						session.update(gatheringMapper + "update_gatheringInfo_main_image", gatheringInfoModel);
//					}catch(Exception e){
//						// 사진 정보 update 오류시에는 미리 업로드된 사진도 삭제해야 하므로, deletePath 경로 설정
//						String DeletePath =  request.getSession().getServletContext().getRealPath("/") + uploadModel.getPic_url();
//
//						// deleteFile 성공? 실패?
//						if(fileUpload.deleteFile(DeletePath))
//						{
//							// 삭제 성공
//						}else{
//							// 삭제 실패
//						}
//					}
//				}// isSuccess end
//			}// gathering_main_image_fileUpload end
//			
//			// SUB IMAGE UPLOAD
//			if(!gatheringInfoModel.getGathering_sub_image_file().isEmpty())
//			{
//				// upload model setting
//				UploadModel uploadModel = new UploadModel();
//				uploadModel.setFile(gatheringInfoModel.getGathering_sub_image_file()); // file
//				uploadModel.setContextRoot(request.getSession().getServletContext().getRealPath("/"));
//				uploadModel.setCategoryPath(categoryPath);
//				uploadModel.setSequencePath(gatheringInfoModel.getGathering_seq());
//				
//				// FileUpload method
//				FileUpload fileUpload = new FileUpload("sub");
//				uploadModel = fileUpload.writeFile(uploadModel);
//			
//				// isSuccess?
//				if(uploadModel.isSuccess())
//				{
//					try{
//						// 경로 넣기.
//						gatheringInfoModel.setGathering_sub_image(uploadModel.getPic_url());
//						// 사진 정보 update
//						session.update(gatheringMapper + "update_gatheringInfo_sub_image", gatheringInfoModel);
//					}catch(Exception e){
//						// 사진 정보 update 오류시에는 미리 업로드된 사진도 삭제해야 하므로, deletePath 경로 설정
//						String DeletePath =  request.getSession().getServletContext().getRealPath("/") + uploadModel.getPic_url();
//
//						// deleteFile 성공? 실패?
//						if(fileUpload.deleteFile(DeletePath))
//						{
//							// 삭제 성공
//						}else{
//							// 삭제 실패
//						}
//					}
//				}// isSuccess end
//			}// gathering_main_image_fileUpload end
			
			
			
			
			isCheck = true;
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 성공시 list로 리다이렉트
		if(isCheck){
			System.out.println("success");
			String redirectURL = request.getSession().getServletContext().getContextPath();
			redirectURL += "/admin/gathering/list";
			return new ModelAndView(new RedirectView(redirectURL));
		}
		
		return mav;
	}
	
	@RequestMapping("/modifyRequest")
	ModelAndView modifyRequest(@ModelAttribute GatheringInfoModel gatheringInfoModel, HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		
		boolean isCheck = false;

//		// file 경로 설정
//		String categoryPath = "gathering";
		
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";	// db setting 
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			// 기본사항 insert
			session.update(gatheringMapper + "update_gatheringInfo_modify", gatheringInfoModel);
			
//			// MAIN IMAGE UPLOAD
//			if(!gatheringInfoModel.getGathering_main_image_file().isEmpty())
//			{
//				// upload model setting
//				UploadModel uploadModel = new UploadModel();
//				uploadModel.setFile(gatheringInfoModel.getGathering_main_image_file()); // file
//				uploadModel.setContextRoot(request.getSession().getServletContext().getRealPath("/"));
//				uploadModel.setCategoryPath(categoryPath);
//				uploadModel.setSequencePath(gatheringInfoModel.getGathering_seq());
//				
//				System.out.println("CONTEXT ROOT :  " + uploadModel.getContextRoot());
//				System.out.println("CATEGORY PATH :  " + uploadModel.getCategoryPath());
//				
//				// FileUpload method
//				FileUpload fileUpload = new FileUpload("main");
//				uploadModel = fileUpload.writeFile(uploadModel);
//				
//				// isSuccess?
//				if(uploadModel.isSuccess())
//				{
//					try{
//						// 경로 넣기.
//						gatheringInfoModel.setGathering_main_image(uploadModel.getPic_url());
//						// 사진 정보 update
//						session.update(gatheringMapper + "update_gatheringInfo_main_image", gatheringInfoModel);
//					}catch(Exception e){
//						// 사진 정보 update 오류시에는 미리 업로드된 사진도 삭제해야 하므로, deletePath 경로 설정
//						String DeletePath =  request.getSession().getServletContext().getRealPath("/") + uploadModel.getPic_url();
//
//						// deleteFile 성공? 실패?
//						if(fileUpload.deleteFile(DeletePath))
//						{
//							// 삭제 성공
//						}else{
//							// 삭제 실패
//						}
//					}
//				}// isSuccess end
//			}// gathering_main_image_fileUpload end
//			
//			// SUB IMAGE UPLOAD
//			if(!gatheringInfoModel.getGathering_sub_image_file().isEmpty())
//			{
//				// upload model setting
//				UploadModel uploadModel = new UploadModel();
//				uploadModel.setFile(gatheringInfoModel.getGathering_sub_image_file()); // file
//				uploadModel.setContextRoot(request.getSession().getServletContext().getRealPath("/"));
//				uploadModel.setCategoryPath(categoryPath);
//				uploadModel.setSequencePath(gatheringInfoModel.getGathering_seq());
//				
//				// FileUpload method
//				FileUpload fileUpload = new FileUpload("sub");
//				uploadModel = fileUpload.writeFile(uploadModel);
//			
//				// isSuccess?
//				if(uploadModel.isSuccess())
//				{
//					try{
//						// 경로 넣기.
//						gatheringInfoModel.setGathering_sub_image(uploadModel.getPic_url());
//						// 사진 정보 update
//						session.update(gatheringMapper + "update_gatheringInfo_sub_image", gatheringInfoModel);
//					}catch(Exception e){
//						// 사진 정보 update 오류시에는 미리 업로드된 사진도 삭제해야 하므로, deletePath 경로 설정
//						String DeletePath =  request.getSession().getServletContext().getRealPath("/") + uploadModel.getPic_url();
//
//						// deleteFile 성공? 실패?
//						if(fileUpload.deleteFile(DeletePath))
//						{
//							// 삭제 성공
//						}else{
//							// 삭제 실패
//						}
//					}
//				}// isSuccess end
//			}// gathering_main_image_fileUpload end
			
			
			
			
			isCheck = true;
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 성공시 list로 리다이렉트
		if(isCheck){
			String redirectURL = request.getSession().getServletContext().getContextPath();
			redirectURL += "/admin/gathering/list";
			return new ModelAndView(new RedirectView(redirectURL));
		}
		return mav;
		
	}
	
	
	// 관리자가 회원의 상태를 직접 수정 (무통장 입금 확인 등의 경우)
	@RequestMapping(value = "/statusRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> statusRequest(@RequestBody Map<String, String> INPUT){
		
		// db setting
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		boolean isCheck = false;
		
		
		String key = INPUT.get("status");
		
		String changed = "";
		switch(key){
		
		case "1" : changed = "결제대기"; break;
		case "2" : changed = "결제완료"; break;
		case "3" : changed = "사용완료"; break;
		case "4" : changed = "취소"; INPUT.put("cancel_user_seq","0"); break; // 취소인 경우 input 데이터가 더 있음.
			
		}
		
		try
		{
			session.update(gatheringMapper + "update_gathering_status", INPUT);
			session.commit();
			isCheck = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		
		Map<String, Object> OUTPUT = new HashMap<String, Object>();
		OUTPUT.put("isCheck", isCheck);
		OUTPUT.put("changed", changed);
		return OUTPUT;
		
	}
	
	@RequestMapping("/deleteListRequest")
	ModelAndView deleteListRequest(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		boolean isCheck = false;
		
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";	// db setting 
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		String tmp = request.getParameter("gathering_seq");
		String[] gathering_seq = tmp.split("/");
		
		try
		{
			session.update(gatheringMapper + "update_gathering_list_delete", gathering_seq);
			session.commit();
			isCheck = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 성공시 list로 리다이렉트
		if(isCheck){
			String redirectURL = request.getSession().getServletContext().getContextPath();
			redirectURL += "/admin/gathering/list";
			return new ModelAndView(new RedirectView(redirectURL));
		}
		return mav;
		
		
	}
	
	
}
