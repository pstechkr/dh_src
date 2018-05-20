<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="clientTemplate">
<tiles:putAttribute name="title">엘루비 :: 매장 관리</tiles:putAttribute>
<tiles:putAttribute name="body">
<!--  map -->
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=14dfba4851808e2a583c78f8ee1c708b860030c9"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/routemap_main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/client/store.js"></script>
<script type="text/javascript">$("header nav#gnb ul li:nth-child(2)").addClass("selected");</script>

<div id="contents">
	<section id="store_management">
		<section class="contents">
			<h2>매장정보</h2>
			<div class="edit">
				<dl class="store_name">
					<dt>매장명</dt>
					<dd>
						<div class="setting_list"><span id="view_store_name">${storeInfoModel.store_name}</span></div>
						<div class="edit_contents">
							<input type="text" id="store_name" value="">
							<div class="btn_box">
								<button class="btn_save" onclick="modify('store_name')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="store_address">
					<dt>주소지</dt>
					<dd>
						<div class="setting_list"><span id="view_store_address">${storeInfoModel.store_localtitle} ${storeInfoModel.store_localname4}</span></div>
						<div class="edit_contents">
							<input type="text" id="q" value="번지 수까지 입력 가능합니다." onfocus="this.value='';"><button class="btn_find" onclick="searchPosition('q')">검색</button>
							<input type="hidden" id="store_longitude" name="store_longitude" value="${storeInfoModel.store_longitude}">
							<input type="hidden" id="store_latitude" name="store_latitude" value="${storeInfoModel.store_latitude}">
							<!-- <input type="hidden" id="store_localtitle" name="store_localtitle" value=""> -->
							<input type="hidden" id="store_localname1" name="store_localname1" value="${storeInfoModel.store_localname1}">
							<input type="hidden" id="store_localname2" name="store_localname2" value="${storeInfoModel.store_localname2}">
							<input type="hidden" id="store_localname3" name="store_localname3" value="${storeInfoModel.store_localname3}">
							
							<div class="map_wrap">
								<div class="map_left">
									<div class="search_title">
										<span>검색결과</span>
									</div>
									<div id="searchResultB">
										<div class="search_default">
											<span>검색 결과가 없습니다.
											</span>
										</div>
									</div>
									<div class="search_last_title">
										<span>주소입력</span>
									</div>
									<div class="search_last">
										<span>기본주소</span><input type="text" id="store_localtitle" name="store_localtitle" value="${storeInfoModel.store_localtitle}" readonly="readonly">
										<span>상세주소</span><input type="text" id="store_localname4" name="store_localname4" value="${storeInfoModel.store_localname4}">
									</div>
									
								</div>
								<div class="map_right">
									<div class="searchResultA">
										<div id="map"></div>
									</div>
								</div>
							</div>
							<div class="btn_box">
								<button class="btn_save"  onclick="modify('store_address')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="store_tel">
					<dt>연락처</dt>
					<dd>
						<div class="setting_list"><span id="view_store_contactnumber">${storeInfoModel.store_contactnumber}</span></div>
						<div class="edit_contents">
							<div>
								<input type="text" id="store_contactnumber1" maxlength="3" value=""> - <input type="text" id="store_contactnumber2" maxlength="4" value=""> - <input type="text" id="store_contactnumber3" maxlength="4" value="">
							</div>
							<div class="btn_box">
								<button class="btn_save" onclick="modify('store_contactnumber')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="store_hour">
					<dt>영업시간</dt>
					<dd>
						<div class="setting_list"><span id="view_store_openinghours1">${storeInfoModel.store_openinghours1}</span> ~ <span id="view_store_openinghours2">${storeInfoModel.store_openinghours2}</span></div>
						<div class="edit_contents">
							<div class="select_time">
								<div class="start_time">
									<select name="store_openinghours1" id="openinghours1">
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
									<select name="store_openinghours1" id="openinghours2">
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
								</div>
								<div>
									<span>~</span>
								</div>
								<div class="end_time">
									<select name="store_openinghours2" id="openinghours3">
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
									<select name="store_openinghours2" id="openinghours4">
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
								</div>
							</div>
							<div class="btn_box">
								<button class="btn_save" onclick="modify('store_openinghours')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="store_breaktime">
					<dt>브레이크 타임</dt>
					<dd>
						<div class="setting_list"><span id="view_store_breaktime1">${storeInfoModel.store_breaktime1}</span> ~ <span id="view_store_breaktime2">${storeInfoModel.store_breaktime2}</span></div>
						<div class="edit_contents">
							<div class="input_radio">
								<input type="radio" name="radio" checked><span>사용</span>
								<input type="radio" name="radio"><span>사용안함</span>
							</div>
							<div class="select_time">
								<div class="start_time">
									<select name="store_breaktime1" id="breaktime1">
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
									<select name="store_breaktime1" id="breaktime2">
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
								</div>
								<div>
									<span>~</span>
								</div>
								<div class="end_time">
									<select name="store_breaktime2" id="breaktime3">
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
									<select name="store_breaktime2" id="breaktime4">
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
								</div>
							</div>
							<div class="btn_box">
								<button class="btn_save" onclick="modify('store_breaktime')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="store_close">
					<dt>정기 휴무일</dt>
					<dd>
						<div class="setting_list">
							<span id="view_store_dayoff"></span>
							<input type="hidden" id="view_store_dayoff_hidden" value="${storeInfoModel.store_dayoff}">
						</div>
						<div class="edit_contents">
							<div class="checkbox">
								<input type="checkbox" name="store_dayoff" value="일요일"><span>일요일</span>
								<input type="checkbox" name="store_dayoff" value="월요일"><span>월요일</span>
								<input type="checkbox" name="store_dayoff" value="화요일"><span>화요일</span>
								<input type="checkbox" name="store_dayoff" value="수요일"><span>수요일</span>
								<input type="checkbox" name="store_dayoff" value="목요일"><span>목요일</span>
								<input type="checkbox" name="store_dayoff" value="금요일"><span>금요일</span>
								<input type="checkbox" name="store_dayoff" value="토요일"><span>토요일</span>
								<input type="checkbox" name="store_dayoff" value="공휴일"><span>공휴일</span>
							</div>
							<div class="btn_box">
								<button class="btn_save" onclick="modify('store_dayoff')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="store_park">
					<dt>주차 정보</dt>
					<dd>
						<div class="setting_list"><span id="view_store_parkinginfo">${storeInfoModel.store_parkinginfo}</span></div>
						<div class="edit_contents">
							<textarea id="store_parkinginfo" rows="5" cols="50"></textarea>
							<div class="btn_box">
								<button class="btn_save" onclick="modify('store_parkinginfo')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
			</div>
		</section>
		<section class="contents decoration">
			<h2>매장 꾸미기</h2>
			<div class="edit">
				<dl class="store_intromessage">
					<dt>소개말</dt>
					<dd>
						<div class="setting_list"><span id="view_store_intromessage">${storeInfoModel.store_intromessage}</span></div>
						<div class="edit_contents">
							<textarea id="store_intromessage" rows="5" cols="50"></textarea>
							<div class="btn_box">
								<button class="btn_save" onclick="modify('store_intromessage')">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
				<dl class="store_introimage">
					<dt>이미지</dt>
					<dd>
						<div class="setting_list">
							<img src="${pageContext.request.contextPath}/resources/img/main_img.png" style="max-width:574px;">
						</div>
						<div class="edit_contents">
							
							<div class="btn_box">
								<button class="btn_save" onclick="">저장</button>
								<button class="btn_cancel">취소</button>
							</div>
						</div>
					</dd>
				</dl>
			</div>
		</section>
	</section>
</div>

</tiles:putAttribute>
</tiles:insertDefinition>