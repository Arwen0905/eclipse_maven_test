<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lotto649</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<style type="text/css">
*{
font-family: "微軟正黑體";
font-family: Arial, Helvetica, sans-serif,'微軟正黑體';
font-size: 24px;
position: relative;
}
#wrapper{
	display: flex;
}
.lotto649{
width: 400px;
border: 5px solid #ff2244;
padding: 20px;
background: #eee;
}
#resetPage{
height: 40px;
color: #ff2244;
font-size: 28px;
user-select: none;
}
#resetPage:hover{
	color: #ffaacc;
	transition: 0.5s;
}
.btn{
color:#ffc408;
font-size: 24px;
background: #2d3a3a;
margin: 5px;
}
#pred_btn,.predict_Go{
	display: none;
	background: #aaeecc;
}
#pred_btn,.predict_Go:hover{
	background: #2d3a3a;
	color: #aaeecc;
}
.predict_Num{
	width: 210px;
	height: 40px;
	text-align: center;
	line-height: 20px;
}
.ansText{
	margin: 5px 0;
	padding: 10px;
	/* background: #ffaacc; */
}
#viewRight{
	width: 400px;
	border: 5px solid #2244ff;
	margin: 0px;
	padding: 20px;
	background: #eee;
}
.other{
	width: 400px;
	font-size: 18px;
	margin: 5px;
}
</style>
</head>
<body>
<div id="wrapper">
	<div class="lotto649">
		<h1 id="resetPage" onclick="javascript:window.location.reload()" title="重新整理">Hello World</h1>
		<input class="btn" value="官方網站" type="button" onclick="window.open('https://www.taiwanlottery.com.tw/lotto/lotto649/history.aspx','_blank')" />
		<a id="url_lotto" href="https://www.taiwanlottery.com.tw/lotto649/index.asp" target="_blank">遊戲規則</a>

		<h2>準備好了嗎 ?</h2>
		
	<button class="lucky" onmousedown="luckyDow()" onmouseup="luckyUp()">電腦選號</button>
<!-- 	<button class="lucky">電腦選號</button> -->
	
	<button id="pred_btn">幸運轉蛋</button>
	
	<p class="up">祝您好運</p>
		<form id="formNum_main">
				<select class="selectYear" id="year" name="year"></select>年份
				<br><br>
				<select onchange="handler_form(this)" name="n1" class="selectNum1"> </select>
				<select onchange="handler_form(this)" name="n2" class="selectNum2"> </select>
				<select onchange="handler_form(this)" name="n3" class="selectNum3"> </select>
				<select onchange="handler_form(this)" name="n4" class="selectNum4"> </select>
				<select onchange="handler_form(this)" name="n5" class="selectNum5"> </select>
				<select onchange="handler_form(this)" name="n6" class="selectNum6"> </select>
				 獎號
				<br><br>
				<!-- 開獎欄位 -->
				<select onchange="handler_option(this)" name="random_seed" class="random_seed"> </select>
				<!-- 送出資料 -->
				<input class="btn" id="submit_btn" onclick="submitLotto()" name="submit" type="button" value="開獎">
		</form>
		<br>
		<!-- 預測碼功能: 無限期延期 -->
		<button name="predict_Num" class="predict_Num">專屬預測號碼!</button>
		<button name="predict_Go" class="predict_Go">貼上</button>

		<div class="ansText"></div>
		<% Object QQ = session.getAttribute("QQ"); %>
	</div>

	<div id="viewRight">
		<pre id="viewAjax" style="word-break:break-all"></pre>
	</div>
	<div class="other"></div>
	<div class="other2"></div>

</div>

	<script type="text/javascript">
	let resetPage = document.querySelector('#resetPage')
	let selectNum1 = document.querySelector('.selectNum1')
	let selectNum2 = document.querySelector('.selectNum2')
	let selectNum3 = document.querySelector('.selectNum3')
	let selectNum4 = document.querySelector('.selectNum4')
	let selectNum5 = document.querySelector('.selectNum5')
	let selectNum6 = document.querySelector('.selectNum6')
	
	let selectYear = document.querySelector('.selectYear')
	
	year = Math.round(Math.random()*5)+2014 //隨機年份
	console.log(year)
	for(i=2014; i<=2019; i++){
			 if(i==year){ //預設年份
			 	selectYear.innerHTML += "<option selected>"+ i +"</option>"
			 }else{
				 selectYear.innerHTML += "<option>"+ i +"</option>"
			 }
		 }
	
	let random_seed = document.querySelector('.random_seed')
	let pred_btn = document.querySelector('#pred_btn')
	let formNum_main = document.querySelector('#formNum_main')
	let submit_btn = document.querySelector('#submit_btn')
	let ansText = document.querySelector('.ansText')
	let viewRight = document.querySelector('#viewRight')
	let viewAjax = document.querySelector('#viewAjax')
	let other = document.querySelector('.other')
	
	// 預測碼功能(更新中) todo.. ======================================================
	
	let lucky = document.querySelector('.lucky')
	let predict_Num = document.querySelector('.predict_Num')
	let predict_Go = document.querySelector('.predict_Go')
	let up = document.querySelector('.up')
	let count = 0
	
	// 取10期獎號作比對樣本
	let ans1 = ["19","45","44","9","6","40","1"]
	let ans2 = ["31","1","11","33","5","19","6"]
	let ans3 = ["45","19","18","16","33","17","25"]
	let ans4 = ["35","8","23","22","29","10","2"]
	let ans5 = ["37","21","35","20","2","24","9"]
	let ans6 = ["25","36","3","33","16","31","14"]
	let ans7 = ["30","25","15","5","41","6","29"]
	let ans8 = ["49","29","32","10","4","24","43"]
	let ans9 = ["5","4","22","49","27","46","41"]
	let ans10 = ["46","4","44","34","22","17","13"]
	let ansAll = [ans1,ans2,ans3,ans4,ans5,ans6,ans7,ans8,ans9,ans10]
	
	// 本地 - 開獎號碼
	s = Math.round(Math.random()*9)+1
	ansLotto = ansAll[s]
	ansLotto.pop() // 移除特別號
	
	ansLotto = ansAll["46","4","44","34","22","17"] //測試用

	// 預測號碼
	push_bool = false
	predict_Num.onclick = function(){
// 		lucky.onclick() //特殊功能，大量投注堆疊出最多中獎的獎號
		count += 1
		if(push_bool == true){ //當預測碼 出現時..
			if(confirm("貼上 預測碼")){ //詢問是否要貼上
				predict_Go.onclick() //貼上
				this.innerHTML=(this.innerHTML=="戳我戳我!"?"戳壞了":"戳我戳我!") //完成並關閉預測碼顯示
			}else{ //取消即關閉號碼顯示
				this.innerHTML=(this.innerHTML=="戳我戳我!"?"戳壞了":"戳我戳我!") //完成並關閉預測碼顯示
				}
		}else{
			this.innerHTML=(this.innerHTML=="戳我戳我!"?"戳壞了":"戳我戳我!")		
		}
		
		if(count%5==0){ // 當符合N次點擊 喚醒預測碼時..
			predict_Go.style.display = "inline" //貼上按鈕 顯示
// 			this.innerHTML = ansLotto //顯示號碼
			this.innerHTML = bigLotto //獲獎最多號碼
			push_bool = true //比對門票
		}else{
			predict_Go.style.display = "none" //貼上按鈕 不顯示
			push_bool = false
		}
	}
	// 將預測號碼 設定為 開獎號碼
	predict_Go.onclick = function(){
		random_seed.innerHTML += '<option selected>'+ bigLotto +'</option>'
		arFun(bigLotto)
	}

// Ajax ======================================================================================================================
	jsonObj = {}
	let big_win = [];
	let bigWin_old = 0
	function lottoAjax(checkNum){
		$('#viewAjax').text("投注獎號：" + checkNum)
// 		console.log($('#formNum_main').serialize()); //送到後端的表單資料
		$.ajax({
			type:"post",
			url:"lottoAction",
			data:$('#formNum_main').serialize(),
			datatype:"text",
			success:function(message){
// 				console.log(JSON.stringify(message.lotto)) //後端發回來的全部資料(取出lotto)
// 				console.log(Object.keys(message.lotto) + " <<lotto所有的key值")
				jsonStr = JSON.stringify(message.lotto) //運用json方法將lotto(集合)轉成json字串
				console.log(jsonStr + " <<取出lotto(集合)並轉成字串")
				jsonObj = JSON.parse(jsonStr) //再將轉型後的字串，解析為JSON格式(鍵值對)
// 				console.log(jsonObj + " <<解析為JSON字串了")
				current_win = jsonL(jsonObj) //使用迭代方法
				$('#viewAjax').append("<br>中獎總數："+ current_win +"<br>")
				if(current_win > bigWin_old){
					bigWin_old = current_win
					bigLotto = checkNum
					$('.other2').text(bigLotto) //取出最容易中獎的獎號
				}
			}
		}) 
	}
	
// ===========================================================================================================================
		// JSON內容輸出
	function jsonL(jsonEl){
		$('.other').text("")//清空內容
		jsonLength = 0 //計算長度，意旨：中獎總數
		for(i in jsonEl){
			jsonLength++ //獎數長度++
			$('#viewAjax').append("<br>"+ jsonEl[i])//顯示每組的中獎號碼 * 已移除 [ ] 外框
			tem_el = jsonEl[i].split(',') // 移除 " , " 逗號，純數值顯示
			count = 0
			red = false
			
			for(i=0; i<6; i++){				
				for(j=0; j<tem_el.length; j++){		
					if(checkNum[i]==tem_el[j]){
						count++
						red = true
					}
				}
				if(red){
					$('.other').append('<spen style="color:#ff2244;">'+checkNum[i]+" </spen>")
				}else{
					$('.other').append('<spen style="color:#000000;">'+checkNum[i]+' </spen>')
				}
				red = false
			}
			
			if(count==3){
			$('.other').append("<br>普獎！ "+ count +" 個符合獎號 <br><br> ")
			}else if(count==4){
			$('.other').append("<br><spen style='color:#ff4444;font-size:40px';>恭喜三獎！</spen>"+ count +" 個符合獎號 <br><br> ")				
			}else if(count==5){
			$('.other').append("<br><spen style='color:#ff2222;font-size:50px';>恭喜二獎！</spen>"+ count +" 個符合獎號 <br><br> ")								
			}else if(count==6){
			$('.other').append("<br><spen style='color:#ffffff;font-size:60px';>發大財 中頭獎！！！</spen>"+ count +" 個符合獎號 <br><br> ")								
			}else{
			$('.other').append("<br>可惜！沒有中獎，下去領500<br><br> ")				
			}
			
		}//以上迴圈已輸出內容
		return jsonLength //只返回總數即可
	}
// ===========================================================================================================================
	// 開獎按鈕: 檢測重複號碼
	function submitLotto(){
		c = currentNum() // 取得當前值(通用函式)
		checkNum = c.toString().split(',')
		ticket = true // 對獎門票
		for(i=0; i<6; i++){
			for(j=0; j<i; j++){
				if(checkNum[i]==checkNum[j]){
					resetPage.innerHTML = "號碼重複，請重新設定"
					other.innerHTML += "<br>" + "請檢查重複值 " +"<span style='color:#ff2244'>"+ checkNum[i] +"</span>" + "<br>"
					ticket = false // 有重複值就沒收門票
					break // 終止對獎
				}
			}
		}

		if(ticket){ // 門票為 true 即符合對獎資格
			resetPage.innerHTML = "經費不足，無限期請求金援"
			lottoAns(checkNum) // 呼叫本地對獎函式
			lottoAjax(checkNum) // Ajax資料庫對獎
		}

	}

	// 本地取樣的對獎函式: 	
	function lottoAns(checkNum){
		ansCurrent = ansAll[s] // 完整獎號
		let checkCount = 0
		for(i=0; i<7; i++){
			for(j=0; j<6; j++){
				if(checkNum[j]==ansCurrent[i]){
					checkCount+=1
					//other.innerHTML += checkNum[j] + " 號碼符合! 目前累積 " + checkCount + " 個號碼獎號" + "<br>"
				}
			}
		}
		if(checkCount>=3){
			//other.innerHTML += "恭喜中獎!本組[ " + checkNum + " ]獎號共符合" + checkCount + "個號碼" + "<br><br>"
		}else{
			//other.innerHTML += "可惜！沒有中獎，下去領500" + "<br>"
		}

	}

	// 取得當前值(通用函式)
	function currentNum(){
		let lst = []
		lst += formNum_main.n1.value +","+ formNum_main.n2.value +","+
				formNum_main.n3.value +","+ formNum_main.n4.value +","+
				formNum_main.n5.value +","+ formNum_main.n6.value
		return lst
	}
	
	// 顯示按鈕(隨機選號功能)
	lucky.onclick = function(){
//		pred_btn.style.display = "inline"
//		lucky.style.display = "none"
		up.innerHTML = "發大財！"
	// 電腦選號 直接送出比對
		ar = [] //清空
		roll() //取得新的ar數值
		random_seed.innerHTML += '<option selected>'+ ar +'</option>'
		arFun(ar) //設定單選框
		submitLotto() //開獎送出
	}
//=====================================================================================
	function timeNow(){ //獲取此刻時間
        now = new Date()
        return now.getTime()}
	//長按
	   function luckyDow()
      {
          startTime = timeNow()//滑鼠按下時間
       	Time = setInterval(function(){ //每100毫秒執行一次
          endTime = timeNow()//所以每100毫秒抓取一次時間

          if(endTime-startTime>1000)//此刻時間與按下時間差有1000毫秒觸發
          {
        	  lucky.onclick()
          }
        },50)//設定每 * 毫秒
      }
     //長按結束
     function luckyUp(){
     	clearInterval(Time)
     }
//=====================================================================================
	// 亂數不重複(通用函式)
	let ar = []
	function roll(){
		for(i=0; i<6; i++){
			let k = Math.round(Math.random()*48)+1
			//if(k<10){k = "0"+k}
			ar.push(k)
			for(j=0; j<i; j++){
				if(ar[j]==k){
					i--
					ar.pop()
				}
			}
		}
	}
	// 遊戲開始 Load
	roll() // 先取得一組亂數
	constructor() // 建構六組單選欄位
	random_seed.innerHTML += '<option selected>'+ ar +'</option>' // 第一次取得的亂數 當作預設號碼
	
	// 幸運轉蛋:亂數產生樂透號碼(不重複)
	pred_btn.onclick = function(){
		ar = []
		roll()
		random_seed.innerHTML += '<option selected>'+ ar +'</option>'
		arFun(ar)
	}
	// ================================================================================	
	 // 事件: java環境的 js設定 疑似不允許即時更新選框值，故使用重寫方式作設計功能
	 function roll_col(arc,v){ // ( 被修改 , 欲修改 )
		 arc.innerHTML = ''
		 for(i=1; i<50; i++){
		    	//if(i<10){i = "0"+i}
		    	if(v == i){arc.innerHTML += "<option selected>"+i+"</option>"
		    		}else{arc.innerHTML += "<option>"+i+"</option>"}
		    	}
	 }
	// 例外: 原先用該值作修改即可，因上述例外使用重寫的方式
	function arFun(ar){
		roll_col(selectNum1,ar[0])
		roll_col(selectNum2,ar[1])
		roll_col(selectNum3,ar[2])
		roll_col(selectNum4,ar[3])
		roll_col(selectNum5,ar[4])
		roll_col(selectNum6,ar[5])
	}
	// ================================================================================

	// 單一選框: 將當前值(下選單) 更新為 開獎號碼
	function handler_form(fs){
		let lst = [] // 空陣列
		let arf = fs.options[fs.selectedIndex].innerText // 取得單個(下選單)修改過的值
		this.value = arf // fs為該 option欄位(下選單)，將其值替換為 修改過的值

		// 上述修改完成，進行 取得當前值(通用函式)
		n = currentNum()
		// 以當前值 n 建立開獎號碼欄位
		random_seed.innerHTML += '<option selected>'+ n +'</option>'
	}
	
	// 開獎號碼欄位: 紀錄每組號碼並將選取的組合 設定為 當前值(下選單)
	function handler_option(ar){
		let ars = ar.options[ar.selectedIndex].innerText;
		ars = ars.split(',')
		arFun(ars)
	}

	// 建構:建立 6組下選單欄位(49個號碼)
	function constructor(){
		for(var i=1; i<=49; i++){
			//if(i<10){i = "0"+i}
			if(ar[0] == i){
				selectNum1.innerHTML += '<option selected>'+ i +'</option>'
			}else{selectNum1.innerHTML += '<option>'+ i +'</option>'}
			
			if(ar[1] == i){
				selectNum2.innerHTML += '<option selected>'+ i +'</option>'
			}else{selectNum2.innerHTML += '<option>'+ i +'</option>'}
			
			if(ar[2] == i){
				selectNum3.innerHTML += '<option selected>'+ i +'</option>'
			}else{selectNum3.innerHTML += '<option>'+ i +'</option>'}
			
			if(ar[3] == i){
				selectNum4.innerHTML += '<option selected>'+ i +'</option>'
			}else{selectNum4.innerHTML += '<option>'+ i +'</option>'}
			
			if(ar[4] == i){
				selectNum5.innerHTML += '<option selected>'+ i +'</option>'
			}else{selectNum5.innerHTML += '<option>'+ i +'</option>'}
			
			if(ar[5] == i){
				selectNum6.innerHTML += '<option selected>'+ i +'</option>'
			}else{selectNum6.innerHTML += '<option>'+ i +'</option>'}		
		 };
	 
	}
	 
    </script>
	


</body>
</html>

