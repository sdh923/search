<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>연관검색어 SAMPLE</title>
    <script type="text/javascript" src="/scripts/jquery-plugins/jquery.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script type="text/javascript">
	// 엔터 체크	
	function pressCheck() {   
		if (event.keyCode == 13) {
			return doRecommend();
		}else{
			return false;
		}
	}

	//연관 검색
	function doRecommend() {
		var query = $("#query").val();
	    $.ajax({
	        url: "/search/recommend.do",
	        dataType: 'json',
	        data: { query : query},
	        type: 'GET',
	        success: function(data) {
	            var recommendUl = document.getElementById('recommend');
	            recommendUl.innerHTML = '';
	           	if(data.Response[0].RelationKeyword){
		            var relationKeywords = data.Response[0].RelationKeyword.split('^');
		            relationKeywords.forEach(function(item) {
		              var recommendli = document.createElement('li');
		              
		              var key = document.createElement('a');
		              key.href = '#';
		              key.textContent = item;
		              key.onclick = function() {
		            	  $("#query").val(item);
		            	  doRecommend();
		              };
		              recommendli.appendChild(key);
		              recommendUl.appendChild(recommendli);
		            });
				}	
	        },
	        error: function(data) {
	            alert("연동 실패");
	        }
	    });

	}
	</script>
  </head>
  <body>
	<div class="input-group mb-3">
		<input type="text" class="form-control" id="query" placeholder="검색어를 입력하세요(ex:학교,감사,슬로건)"   onKeypress="javascript:pressCheck((event),this);"/>
		<button class="btn btn-outline-secondary" type="button" id="button-addon2" onClick="doRecommend();" >검색</button>
	</div>
	<div>
		<ul id="recommend">
		</ul>
	</div>
</body>
</html>