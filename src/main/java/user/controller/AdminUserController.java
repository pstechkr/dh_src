package user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import user.model.UserInfoModel;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	@RequestMapping("/list")
	public ModelAndView gathering(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		String userMapper = "user.mapper.UserMapper.";	// db setting
		List<UserInfoModel> list = new ArrayList<UserInfoModel>();
		
		// 페이징 처리
		String row = "10"; // row
		String page = request.getParameter("p");if(page == null){page = "1";} // 현재 page
		String sqlPage = Integer.toString(((Integer.parseInt(page)-1) * Integer.parseInt(row))); // sql에 들어가는 page
		// 페이지 번호 매기기
		Map<String, String> selectPagination = new HashMap<String, String>();
		int pagination_row = 10; // 하단에 보여질 페이지 번호 매기기의 갯수
		
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			Map<String, String> selectMonthMap = new HashMap<String, String>();
			selectMonthMap.put("page", sqlPage);
			selectMonthMap.put("row", row);
			list = session.selectList(userMapper + "select_userInfoList", selectMonthMap);
			
			// 전체 페이지 수 (pageList)
			Map<String, Object> selectPageMap = new HashMap<String, Object>();
			selectPageMap.put("row", row);
			selectPageMap.put("pagination_row", pagination_row);
			selectPagination = session.selectOne(userMapper + "select_user_pagination", selectPageMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		//facebook id
		for(int i=0; i < list.size(); i++){
			
			if(list.get(i).getFacebook_id() == null){
				list.get(i).setFacebook_id("X");
			}else{
				list.get(i).setFacebook_id("O");
			}
		}
		
		
		// 페이지 번호 매기기
		// 전체 페이지 수, 현재 페이지, 하단에 보여질 페이지 번호 매기기의 갯수를 이용.
		// 형변환.
		String tmp = selectPagination.get("total_pagination");
		int total_pagination = Integer.parseInt(tmp);  // 전체 페이지 수
		tmp = selectPagination.get("divide_pagination");
		int divide_pagination = Integer.parseInt(tmp); // 하단에 보여질 페이지 번호로 전체 페이지 수 나눔.
		int last_pagination = 1; // 해당 divide_pagination에서의 마지막 페이지 번호 
		int first_pagination = 1; // 해당 divide_pagination에서의 첫번째 페이지 번호
		int _page = Integer.parseInt(page); // 현재 page
		
		// ex divide_pagination 2, pagination_row 2이라면,
		for(int j=1; j <= divide_pagination; j++){
		
			//ex j=1 ;  first 1 last 2 	//ex j=2 ;  first 3 last 4
			int last = j * pagination_row;
			int first = last - pagination_row + 1;
			// 그런데 마지막 루프인 경우에는 last = j * pagination_row 가 아닐 수 있음
			if(j == divide_pagination){last = total_pagination;}
			
			// 구분된 수 사이에 _page가 있다면.
			if(first <= _page && _page <= last){
				first_pagination = first;
				last_pagination = last;
			}
		}
		
		// mav에 담을 때, pagination : 번호 전체.
		// this_page : 현재 페이지 
		// last_page : 마지막 페이지
		List<Integer> pagination = new ArrayList<Integer>();
		while(first_pagination <= last_pagination){
			pagination.add(first_pagination);
			first_pagination ++;
		}
		
		mav.setViewName("/admin/user/user_list");
		mav.addObject("list", list);
		mav.addObject("pagination", pagination);
		mav.addObject("last_page", total_pagination);
		mav.addObject("this_page", page);
		
		return mav;
	}
	
}
