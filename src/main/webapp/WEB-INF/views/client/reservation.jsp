<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="clientTemplate">
<tiles:putAttribute name="title">엘루비 :: 예약관리</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/client/reservation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/client/menu.js"></script>
<script type="text/javascript">$("header nav#gnb ul li:nth-child(3)").addClass("selected");</script>

<div id="contents">
	<section id="rsv_management">
		<section class="contents">
			<h2>예약 관리</h2>
			<div id="sw_control">
				<dl class="rsv_switch">
					<dt>예약 기능 ON / OFF</dt>
					<dd>
						<div class="btn_box">
							<button class="btn_active" onClick="rsvSwitch(1);">ON</button>
							<button onClick="rsvSwitch(2);">OFF</button>
						</div>
					</dd>
				</dl>
			</div>
			<div id="sw_contents" class="edit active">
				<dl class="rsv_common">
					<dt>예약 기본 정보</dt>
					<dd>
						<div class="setting_list">
							<dl>
								<dt>예약 가능 시간</dt>
								<dd><span id="view_rsv_openinghours1">${storeInfoModel.reservation_openinghours1}</span>~<span id="view_rsv_openinghours2">${storeInfoModel.reservation_openinghours2}</span></dd>
							</dl>
							<dl>
								<dt>시간당 최대 예약인원</dt>
								<dd><span id="view_rsv_max">${storeInfoModel.reservation_max}</span><span>명</span></dd>
							</dl>
						</div>
						<div class="edit_contents">
							<dl>
								<dt>예약 가능 시간</dt>
								<dd>
									<select id="rsv_openinghours1">
										<option value="00">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
									<span>:</span>
									<select id="rsv_openinghours2">
										<option value="00">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>
										<option value="32">32</option>
										<option value="33">33</option>
										<option value="34">34</option>
										<option value="35">35</option>
										<option value="36">36</option>
										<option value="37">37</option>
										<option value="38">38</option>
										<option value="39">39</option>
										<option value="40">40</option>
										<option value="41">41</option>
										<option value="42">42</option>
										<option value="43">43</option>
										<option value="44">44</option>
										<option value="45">45</option>
										<option value="46">46</option>
										<option value="47">47</option>
										<option value="48">48</option>
										<option value="49">49</option>
										<option value="50">50</option>
										<option value="51">51</option>
										<option value="52">52</option>
										<option value="53">53</option>
										<option value="54">54</option>
										<option value="55">55</option>
										<option value="56">56</option>
										<option value="57">57</option>
										<option value="58">58</option>
										<option value="59">59</option>
									</select>
									~
									<select id="rsv_openinghours3">
										<option value="00">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
									</select>
									<span>:</span>
									<select id="rsv_openinghours4">
										<option value="00">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>
										<option value="32">32</option>
										<option value="33">33</option>
										<option value="34">34</option>
										<option value="35">35</option>
										<option value="36">36</option>
										<option value="37">37</option>
										<option value="38">38</option>
										<option value="39">39</option>
										<option value="40">40</option>
										<option value="41">41</option>
										<option value="42">42</option>
										<option value="43">43</option>
										<option value="44">44</option>
										<option value="45">45</option>
										<option value="46">46</option>
										<option value="47">47</option>
										<option value="48">48</option>
										<option value="49">49</option>
										<option value="50">50</option>
										<option value="51">51</option>
										<option value="52">52</option>
										<option value="53">53</option>
										<option value="54">54</option>
										<option value="55">55</option>
										<option value="56">56</option>
										<option value="57">57</option>
										<option value="58">58</option>
										<option value="59">59</option>
									</select>
								</dd>
							</dl>
							<dl>
								<dt>시간당 최대 예약인원</dt>
								<dd>
									<select id="rsv_max">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">27</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>
										<option value="32">32</option>
										<option value="33">33</option>
										<option value="34">34</option>
										<option value="35">35</option>
										<option value="36">36</option>
										<option value="37">37</option>
										<option value="38">38</option>
										<option value="39">39</option>
										<option value="40">40</option>
										<option value="41">41</option>
										<option value="42">42</option>
										<option value="43">43</option>
										<option value="44">44</option>
										<option value="45">45</option>
										<option value="46">46</option>
										<option value="47">47</option>
										<option value="48">48</option>
										<option value="49">49</option>
										<option value="50">50</option>
										<option value="51">51</option>
										<option value="52">52</option>
										<option value="53">53</option>
										<option value="54">54</option>
										<option value="55">55</option>
										<option value="56">56</option>
										<option value="57">57</option>
										<option value="58">58</option>
										<option value="59">59</option>
										<option value="60">60</option>
										<option value="61">61</option>
										<option value="62">62</option>
										<option value="63">63</option>
										<option value="64">64</option>
										<option value="65">65</option>
										<option value="66">66</option>
										<option value="67">67</option>
										<option value="68">68</option>
										<option value="69">69</option>
										<option value="70">70</option>
										<option value="71">71</option>
										<option value="72">72</option>
										<option value="73">73</option>
										<option value="74">74</option>
										<option value="75">75</option>
										<option value="76">76</option>
										<option value="77">77</option>
										<option value="78">78</option>
										<option value="79">79</option>
										<option value="80">80</option>
										<option value="81">81</option>
										<option value="82">82</option>
										<option value="83">83</option>
										<option value="84">84</option>
										<option value="85">85</option>
										<option value="86">86</option>
										<option value="87">87</option>
										<option value="88">88</option>
										<option value="89">89</option>
										<option value="90">90</option>
										<option value="91">91</option>
										<option value="92">92</option>
										<option value="93">93</option>
										<option value="94">94</option>
										<option value="95">95</option>
										<option value="96">96</option>
										<option value="97">97</option>
										<option value="98">98</option>
										<option value="99">99</option>
									</select>
									<span>명</span>
								</dd>
							</dl>
							
							<div class="btn_box">
								<button class="btn_save" onclick="modify('rsv_common')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="rsv_date">
					<dt>예약 날짜 관리</dt>
					<dd>
						<div class="table_wrap">
							<div class="table_layout">
								<div class="cal_header">
									<button class="prev_mon" onclick="javascript:calendarRequest('prev');">이전 달</button>
									<div class="date_area">
										<span id="calYear">0000</span>.<span id="calMonth">00</span>
									</div>
									<button class="next_mon" onclick="javascript:calendarRequest('next');">다음 달</button>
								</div>
								<table summary="예약 현황을 확인할 수 있습니다.">
									<caption>예약 현황 테이블</caption>
									<thead>
										<tr>
											<th scope="col">일</th>
											<th scope="col">월</th>
											<th scope="col">화</th>
											<th scope="col">수</th>
											<th scope="col">목</th>
											<th scope="col">금</th>
											<th scope="col">토</th>
										</tr>
									</thead>
									<tbody class="calendar_load">
									</tbody>
								</table>
							</div>
							<div class="edit_status">
								<div class="setting">
									<span>0000. 00. 00</span>
									<div class="setting_box">
										<div>
											<input type="radio" name="rsv_setting" value="1"> 예약 가능
										</div>
										<div>
											<input type="radio" name="rsv_setting" value="0"> 예약 불가능
										</div>
									</div>
									<div class="btn_box">
										<button class="btn_save" onclick="modifyDate()">저장</button>
										<button class="btn_cancel">취소</button>
									</div>
								</div>
							</div>
						</div>
					</dd>
				</dl>
			</div>
		</section>
		
		<section id="card_area" class="contents">
			<h2>예약 상품 관리</h2>
			
			<c:forEach var="menulist" items="${menuList}" varStatus="status">
			<div class="card cnt${status.count}">
				<input type="hidden" id="menu_seq_cnt${status.count}" value="${menulist.menu_seq}">
				<div class="contents">
					<div class="two_column">
						<dl class="subject">
							<dt>상품명</dt>
							<dd>
								<div class="list"><span id="view_menu_name_cnt${status.count}">${menulist.menu_name}</span></div>
								<div class="edit"><input type="text" id="menu_name_cnt${status.count}"></div>
							</dd>
						</dl>
						<dl class="price">
							<dt>가격</dt>
							<dd>
								<div class="list"><span id="view_menu_price_cnt${status.count}">${menulist.menu_price}</span> 원</div>
								<div class="edit"><input type="text" id="menu_price_cnt${status.count}"> 원</div>
							</dd>
						</dl>
						<dl class="time">
							<dt>소요시간</dt>
							<dd>
								<div class="list"><span id="view_menu_time_cnt${status.count}"></span> 시간
								<input type="hidden" id="view_menu_time_hidden_cnt${status.count}" value="${menulist.menu_time}"></div>
								
								<div class="edit">
									<select name="menu_time_cnt${status.count}" id="id_menu_time_cnt${status.count}">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
									</select>
									 시간
								</div>
							</dd>
						</dl>
						<dl class="day">
							<dt>예약 가능 요일</dt>
							<dd>
								<div class="list">
									<span id="view_menu_day_cnt${status.count}"></span>
									<input type="hidden" id="view_menu_day_hidden_cnt${status.count}" value="${menulist.menu_day}"> 
								<!-- 
									<ul class="select_week">
										<li><span></span></li>
									</ul>
								 -->
								</div>
								<div class="edit">
									<input type="checkbox" name="menu_day_cnt${status.count}" value="일">일
									<input type="checkbox" name="menu_day_cnt${status.count}" value="월">월
									<input type="checkbox" name="menu_day_cnt${status.count}" value="화">화
									<input type="checkbox" name="menu_day_cnt${status.count}" value="수">수
									<input type="checkbox" name="menu_day_cnt${status.count}" value="목">목
									<input type="checkbox" name="menu_day_cnt${status.count}" value="금">금
									<input type="checkbox" name="menu_day_cnt${status.count}" value="토">토
								</div>
							</dd>
						</dl>
					</div>
					<div class="one_column">
						<dl class="description">
							<dt>상품 설명</dt>
							<dd>
								<div class="list">
									<span id="view_menu_instruction_cnt${status.count}">${menulist.menu_instruction}</span>
								</div>
								<div class="edit">
									<textarea id="menu_instruction_cnt${status.count}" cols="85" rows="5"></textarea>
								</div>
							</dd>
						</dl>
					</div>
				</div>
				<div class="btn_box">
					<button class="btn_save" onclick="modifyMenu(${status.count})">저장</button>
					<button class="rsv_edit_cancel">취소</button>
					<button class="btn_delete">삭제</button>
				</div>
			</div>
			</c:forEach>
			
			<button class="new_menu" onclick="new_menu()">새로운 상품 등록하기</button>
		</section>
	</section>
</div>

</tiles:putAttribute>
</tiles:insertDefinition>

