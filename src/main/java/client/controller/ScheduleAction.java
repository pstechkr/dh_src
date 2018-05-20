package client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import client.model.ScheduleInfoModel;
import client.model.TimeTableModel;
import client.method.EleverSchedule;
@Controller
@RequestMapping("/client")
public class ScheduleAction {

	// 스케줄 :: 타임테이블
	@RequestMapping(value = "/timetableRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> TimeTableRequest(@RequestBody ScheduleInfoModel scheduleInfoModel, HttpSession httpsession){
		
		//seq받기
		scheduleInfoModel.setStore_seq((String)httpsession.getAttribute("store_seq"));
		
		String ScheduleMapper = "client.mapper.ScheduleMapper.";
		EleverSchedule eleverSchedule = new EleverSchedule();
		
		List<ScheduleInfoModel> scheduleList = new ArrayList<ScheduleInfoModel>();
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{
			scheduleList = session.selectList(ScheduleMapper + "select_scheduleTimeTable", scheduleInfoModel);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		// 시간, 방문예정, 예약진행을 리스트로 정리하는 로직
		int divideMin = 30;
		Map<String, TimeTableModel> table = new TreeMap<String, TimeTableModel>();
		table = eleverSchedule.getTimetable(scheduleList, divideMin);
		
		// LIST로 변경
		List<TimeTableModel> tableList = new ArrayList<TimeTableModel>();
		int index = 0;
		for(Map.Entry<String, TimeTableModel> entry: table.entrySet()){
			tableList.add(index, entry.getValue());
			index++;
		}
		
		// JSON 정렬
		Map<String, Object> rtnList = new HashMap<String, Object>();
		
		// 리턴
		rtnList.put("tableList", tableList);
		return rtnList;
	}
	
	// 스케줄 :: 방문 진행 명단 및 예약 진행 명단
	@RequestMapping(value = "/memberRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> MemberRequest(@RequestBody TimeTableModel timeTableModel, HttpSession httpsession){
		
		String[] ready = null;
		String[] doing = null;
		if(timeTableModel.getReady() != null){
			ready = timeTableModel.getReady().split("/");
		}
		if(timeTableModel.getDoing() != null){
			doing = timeTableModel.getDoing().split("/");
		}
		
		List<ScheduleInfoModel> readyMemberList = new ArrayList<ScheduleInfoModel>();
		List<ScheduleInfoModel> doingMemberList = new ArrayList<ScheduleInfoModel>();
		
		String ScheduleMapper = "client.mapper.ScheduleMapper.";
		
		// JSON 정렬
		Map<String, Object> rtnList = new HashMap<String, Object>();
				
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{
			if(ready != null){
				readyMemberList = session.selectList(ScheduleMapper + "select_scheduleMember", ready);
				rtnList.put("readyMemberList", readyMemberList);
			}
			if(doing != null){
				doingMemberList = session.selectList(ScheduleMapper + "select_scheduleMember", doing);
				rtnList.put("doingMemberList", doingMemberList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return rtnList;
	}
	
}
