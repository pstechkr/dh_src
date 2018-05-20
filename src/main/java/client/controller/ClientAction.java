package client.controller;

// model
import client.model.ClientInfoModel;
import client.model.ClientLoginModel;

// file
import tiein.method.FileUpload;
import tiein.method.UploadModel;

// sql
import mysql.conn.MySqlConn;
import org.apache.ibatis.session.SqlSession;




//spring
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/client")
public class ClientAction {

	@RequestMapping("/register")
	public ModelAndView register(@ModelAttribute ClientInfoModel clientInfoModel, HttpServletRequest request)
	{
		// view setting
		ModelAndView mav = new ModelAndView();
		
		// db setting 
		String clientMapper = "client.mapper.ClientMapper.";	
		String storeMapper = "client.mapper.StoreMapper.";
		
		// file 경로 설정
		String categoryPath = "license";	
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
	
		try
		{
			// 기본 정보 insert
			session.insert(clientMapper + "insert_clientInfoRegister", clientInfoModel);
			// 상점 정보 insert
			session.insert(storeMapper + "insert_storeInfoRegister", clientInfoModel);

			
			// license_pic_file이 비어있는가? 그렇지 않다면..
			if(!clientInfoModel.getClient_license_pic_file().isEmpty())
			{
				// upload model setting
				UploadModel uploadModel = new UploadModel();
				uploadModel.setFile(clientInfoModel.getClient_license_pic_file());
				uploadModel.setContextRoot(request.getSession().getServletContext().getRealPath("/"));
				uploadModel.setCategoryPath(categoryPath);
				uploadModel.setSequencePath(clientInfoModel.getClient_seq());
				
				// FileUpload method
				FileUpload fileUpload = new FileUpload();
				uploadModel = fileUpload.writeFile(uploadModel);
			
				// isSuccess?
				if(uploadModel.isSuccess())
				{
					try{
						clientInfoModel.setClient_license_pic(uploadModel.getPic_url());
						// 사진 정보 update
						session.update(clientMapper + "update_clientInfo_license_pic", clientInfoModel);
					}catch(Exception e){
						// 사진 정보 update 오류시에는 미리 업로드된 사진도 삭제해야 하므로, deletePath 경로 설정
						String DeletePath =  request.getSession().getServletContext().getRealPath("/") + uploadModel.getPic_url();

						// deleteFile 성공? 실패?
						if(fileUpload.deleteFile(DeletePath))
						{
							// 삭제 성공
						}else{
							// 삭제 실패
						}
					}
				}// isSuccess end
			}// license_pic_fileUpload end
		
			// db commit
			session.commit();
		
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			session.close();
		}	
		
		
		mav.setViewName("/client/join");
		return mav;
		
	}
	
	
	@RequestMapping("/login")
	public String login(@ModelAttribute ClientLoginModel clientLoginModel, HttpServletRequest request, HttpSession httpsession){
		
		// sql setting
		String clientMapper = "client.mapper.ClientMapper.";	// db setting 
		
		// return setting
		String returnUrl = "/client/index"; 
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			clientLoginModel = session.selectOne(clientMapper + "select_clientInfoLogin", clientLoginModel);
			
			System.out.println(clientLoginModel.isSuccess());
			
			// email, password 일치 판단
			if(clientLoginModel.isSuccess())
			{
				// session setting
				httpsession.setAttribute("client_name", clientLoginModel.getClient_name());
				httpsession.setAttribute("client_seq", clientLoginModel.getClient_seq());
				httpsession.setAttribute("store_seq", clientLoginModel.getStore_seq());
				returnUrl = "/client/main";
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return "redirect:" + returnUrl;
	}
	
	@RequestMapping("/logout")
	public String login(HttpSession httpsession){
		
		httpsession.removeAttribute("client_name");
		httpsession.removeAttribute("client_seq");
		httpsession.removeAttribute("store_seq");
		
		return "redirect:/client/index";
	}
}
