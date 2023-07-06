const xmlHttpRequest=new XMLRuquest();
const method="GET"
const url="my.jsp"
/*
	XMLHttpRequest : 브라우저 내장 객체
	-------------- 전송 (요청), 결과값을 받는다
	
	1) 연결 : open
	2) 데이터 전송 : send()
	3) 결과값을 받아서 처리하는 과정
		readyState, status : 상태 => 200 (정상 상태)
		---------- 준비과정
		0,1,2,3,4 => send() 완료
		if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200) {
			데이터를 읽어서 출력
		}
		
		success:function()
		
*/


function ajaxConfig(method,url,callback) {
	
    xmlHttpRequest.onreadystatechange = callback;
    xmlHttpRequest.open(method, url, true);
    xmlHttpRequest.send();
    
}



/*
	=> 이렇게 바뀜
	$.ajax({
		type:
		url:
		data:{}
		success:function(e) 
		
	})
*/


