package gathering.controller;

import gathering.model.GatheringInfoModel;
import gathering.model.GatheringMemberInfoModel;

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

import service.model.TagInfoModel;

@Controller
@RequestMapping("/admin/gathering")
public class AdminGatheringController {
	
	@RequestMapping("/list")
	public ModelAndView gathering(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";	// db setting
		List<GatheringInfoModel> list = new ArrayList<GatheringInfoModel>();
		
		// 페이징 처리
		String row = "5"; // row
		String page = request.getParameter("p");if(page == null){page = "1";} // 현재 page
		String sqlPage = Integer.toString(((Integer.parseInt(page)-1) * Integer.parseInt(row))); // sql에 들어가는 page
		
		// 페이지 번호 매기기
		Map<String, String> selectPagination = new HashMap<String, String>();
		int pagination_row = 2; // 하단에 보여질 페이지 번호 매기기의 갯수
		
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			Map<String, String> selectListMap = new HashMap<String, String>();
			selectListMap.put("page", sqlPage);
			selectListMap.put("row", row);
			list = session.selectList(gatheringMapper + "select_gatheringInfo", selectListMap);
			
			// 전체 페이지 수 (pageList)
			Map<String, Object> selectPageMap = new HashMap<String, Object>();
			selectPageMap.put("row", row);
			selectPageMap.put("pagination_row", pagination_row);
			selectPagination = session.selectOne(gatheringMapper + "select_gathering_pagination", selectPageMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
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
		
		mav.setViewName("/admin/gathering/gathering_list");
		mav.addObject("list", list);
		mav.addObject("pagination", pagination);
		mav.addObject("last_page", total_pagination);
		mav.addObject("this_page", page);
		
		return mav;
	}
	
	@RequestMapping("/write")
	public ModelAndView gathering_write(){
		
		ModelAndView mav = new ModelAndView();
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";	// db setting
		List<TagInfoModel> tag_list = new ArrayList<TagInfoModel>();
		
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			tag_list = session.selectList(gatheringMapper + "select_elever_tag");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		mav.setViewName("/admin/gathering/gathering_write");
		mav.addObject("tag_list", tag_list);
		
		return mav;
	}
	
	@RequestMapping("/modify")
	public ModelAndView gathering_modify(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";
		List<TagInfoModel> tag_list = new ArrayList<TagInfoModel>();
		GatheringInfoModel gatheringInfoModel = new GatheringInfoModel();
		
		String gathering_seq = request.getParameter("seq");

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
			
		try
		{
			tag_list = session.selectList(gatheringMapper + "select_elever_tag");
			gatheringInfoModel = session.selectOne(gatheringMapper + "select_gatheringInfo_modify", gathering_seq);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		mav.setViewName("/admin/gathering/gathering_write");
		mav.addObject("tag_list", tag_list);
		mav.addObject("gatheringInfoModel", gatheringInfoModel);
		return mav;
		
	}
	
	@RequestMapping("/member")
	public ModelAndView gathering_member(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";
		List<GatheringMemberInfoModel> gatheringMemberList = new ArrayList<GatheringMemberInfoModel>();
		
		String gathering_seq = request.getParameter("seq");
		String gathering_name = "";
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
			
		try
		{
			gathering_name = session.selectOne(gatheringMapper + "select_gathering_name", gathering_seq);
			gatheringMemberList = session.selectList(gatheringMapper + "select_gathering_member", gathering_seq);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		mav.setViewName("/admin/gathering/gathering_member");
		mav.addObject("gatheringMemberList", gatheringMemberList);
		mav.addObject("gathering_name", gathering_name);
		
		return mav;
		
	}
}
