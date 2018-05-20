package user.controller;

import gathering.model.GatheringInfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.model.SliderInfoModel;

@Controller
public class IndexController {
	
	// 인덱스 페이지
	@RequestMapping(value={"/","/index"})
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView();
	
		SliderInfoModel sliderInfoModel = new SliderInfoModel();
		// 현재 진행중인 엘루비 프로젝트 불러오기
		List<GatheringInfoModel> nowGatheringList = new ArrayList<GatheringInfoModel>();
		// 지난 엘루비 프로젝트 불러오기
		List<GatheringInfoModel> pastGatheringList = new ArrayList<GatheringInfoModel>();
		
//		// 상점 불러오기
//		// sql setting
//		String storeMapper = "client.mapper.StoreMapper.";
//		List<StoreInfoModel> storeList = new ArrayList<StoreInfoModel>();
		
		// db setting
		String sliderMapper = "service.mapper.SliderMapper.";
		String gatheringMapper = "gathering.mapper.AdminGatheringMapper.";
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
//			storeList = session.selectList(storeMapper + "select_storeInfoSearch");
			nowGatheringList = session.selectList(gatheringMapper + "select_now_gatheringInfo_search");
			pastGatheringList = session.selectList(gatheringMapper + "select_past_gatheringInfo_search");
			sliderInfoModel = session.selectOne(sliderMapper + "select_sliderInfo");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 슬라이드 분기
		if(sliderInfoModel != null){
			
			String[] imageList = sliderInfoModel.getSlider_image().split("<br>",-1);
			String[] linkList = sliderInfoModel.getSlider_link().split("<br>",-1);
			String[] commentList = sliderInfoModel.getSlider_comment().split("<br>",-1);

			List<SliderInfoModel> sliderList = new ArrayList<SliderInfoModel>();
			for(int i = 0; i < imageList.length ; i++){
				
				SliderInfoModel tmp = new SliderInfoModel();
				tmp.setSlider_image(imageList[i]);
				tmp.setSlider_link(linkList[i]);
				tmp.setSlider_comment(commentList[i]);
				sliderList.add(tmp);
				
			}
		mav.addObject("sliderList", sliderList);
		}
		
		mav.setViewName("/user/index");
//		mav.addObject("storeList", storeList);
		mav.addObject("nowGatheringList", nowGatheringList);
		mav.addObject("pastGatheringList", pastGatheringList);
	
		return mav;
	}

	
	
	// 메모리확인
	@RequestMapping("/memory")
	public ModelAndView memory(){
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/vm_memory");
		
		return mav;
	}
	
	// 회사소개
	@RequestMapping("/introduce")
	public ModelAndView introduce(){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/introduce");
		
		return mav;
	}
		
}
