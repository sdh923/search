<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주제어 클라우드 SAMPLE</title>
<!-- JIT Library File -->
<script type="text/javascript" src="/scripts/visual/jit.js"></script>

<link type="text/css" href="/styles/topic.cloud.css" rel="stylesheet" />

<script type="text/javascript" src="/scripts/jquery-plugins/jquery.js"></script>
<script type="text/javascript" src="/scripts/jquery-plugins/jquery.shuffle.js"></script>
<script type="text/javascript" src="/scripts/jquery-plugins/jquery.tagcanvas.js"></script>
<script type="text/javascript" src="/scripts/jquery-plugins/jquery.flot.js"></script>
<script type="text/javascript" src="/scripts/jquery-plugins/jquery.xml2json.js"></script>
<script type="text/javascript">


var gColor = {
    "relation" : {
        "circle1" : "#4A89D0",
        "circle2" : "#DF9832",
        "circle3" : "#886ACA",
        "circle4" : "#67D888",
        "line" : "#565656"
    },
    "cloud" : {
        "font0" : "#E28633",
        "font1" : "#4C88D0",
        "font2" : "#856CC8",
        "font3" : "#55C2D9",
        "font4" : "#85D49A",
        "font5" : "#949494"
    },
    "trend" : {
        "colors" : ["#E03B41", "#EC9220", "#8DBF20", "#81B2EA", "#8A4AE7"],
        "grid": {
            color: "#545454",
            backgroundColor: null,
            borderColor: null,
            tickColor: null
        },
        "legend": {
            labelBoxBorderColor: "#000"
        }
    }
};

var gColorTheme1 = {
    "relation" : {
        "circle1" : "#EBB056",
        "circle2" : "#416D9C",
        "circle3" : "#70A35E",
        "circle4" : "#83548B",
        "line" : "#557EAA"
    },
    "cloud" : {
        "font0" : "#FF0000",
        "font1" : "#FF6600",
        "font2" : "#DDE200",
        "font3" : "#008000",
        "font4" : "#009E4E",
        "font5" : "#333399"
    },
    "trend" : {
        "colors" : ["#edc240", "#afd8f8", "#cb4b4b", "#4da74d", "#9440ed"],
        "grid": {
            color: "#545454",
            backgroundColor: null,
            borderColor: null,
            tickColor: null
        },
        "legend": {
            labelBoxBorderColor: "#ccc"
        }
    }
};

$(document).ready(function() {
	drawTopicCloud();
});

var GRAPH_TIMEOUT = 15000;
/**
 * 주제 클라우드 그래프 생성
 */
function drawTopicCloud() {

    $.ajax({
        url: "/search/topicCloud.do",
        dataType: 'json',
        data: { coll_id : "board"},
        type: 'GET',
        cache: false,
        timeout: GRAPH_TIMEOUT,
        success: function(data) {
           
        	try {
                var status = data.Response[0].Status[0];
                console.log(status);
                if (status.Message == 'success') {
                
                    var topics = reformatTopicCloud(data);
             	  
                    var weightTags = '<div class="wrapperCenter"><div class="center"><ul class="weighted" id="weightTags"></ul></div></div>';
                    $("#cloud").html('');
                    $("#cloud").append(weightTags);

                    for (var i=0; i< topics.length; i++) {
                        var id = topics[i].id;
                        var fontSize = topics[i].data.$size;
                        var fontColor = topics[i].data.$color;
                        var word = topics[i].name;
                        $.browser.chrome = $.browser.webkit && !!window.chrome;
                        if ($.browser.chrome) {
                            fontSize = fontSize/1.5;    // 크롬 브라우져용 포트 보정.
                        }
                        var tag = '<a class=\"top\" onclick=\"clickTag(\'' + id + '\',\''+word + '\')\" data-weight=\"\" style=\"font-size:'
                                + fontSize + 'ex;color:' + fontColor + ';\">' + word + '</a>';
                        if (i < topics.length-1) {
                            tag += "<span style='padding: -1px -1px -1px 20px;font-size: 2em;'>&middot;</span>";
                        }
                        $("#weightTags").append($("<li></li>").append(tag));
                    }
                } else {
                    alert("연동 실패");
                }
            } catch (e) {
                alert("연동 실패");
            }
        },
        error: function(data) {
            alert("연동 실패");
        }
    });

}

function reformatTopicCloud(teaJson) {
    var jitJson = [];

    var topics = teaJson.Response[0].Topics[0].Topic;
	
    var maxFreq = MAX_FREQ(topics);
    var minFreq = MIN_FREQ(topics);
    var maxFontSize = 10;
    var minFontSize = 4;
    var maxDisplay = 20;

    for (var i=0; i<topics.length && i<maxDisplay ;i++) {
        var id= topics[i].Id;
        var label = topics[i].Label;
        var docCount = topics[i].DocCount;
        docCount = parseInt(docCount);
        
        var item = {
            "data": {
                "$color": "#83548B",
                "$size": "11",
                "$count": "count"
            },
            "id": "id",
            "name": "label"
        };
        item.id=id;
        item.name = label;

        var fontSize;
      
        if (docCount > minFreq) {
            fontSize = (((docCount - minFreq) * (maxFontSize - minFontSize)) / maxFreq - minFreq) + minFontSize;
        } else {
            fontSize = minFontSize;
        }
        fontSize = parseFloat(fontSize).toFixed(2);
        item.data.$size = fontSize;
        item.data.$count = docCount;
       
        var colorCount = 0;
        for( var x in gColor.cloud ) if(gColor.cloud.hasOwnProperty(x)) colorCount++;
        var color;
        var range = (maxFontSize - minFontSize) / colorCount;
        if (fontSize > maxFontSize - range * 1) {
            color = gColor.cloud.font0;
        } else if (fontSize > maxFontSize - range * 2) {
            color = gColor.cloud.font1;
        } else if (fontSize > maxFontSize - range * 3) {
            color = gColor.cloud.font2;
        } else if (fontSize > maxFontSize - range * 4) {
            color = gColor.cloud.font3;
        } else if (fontSize > maxFontSize - range * 5) {
            color = gColor.cloud.font4;
        } else {
            color = gColor.cloud.font5;
        }
        item.data.$color = color;
       
        jitJson.push(item);
    }
    jitJson = $.shuffle(jitJson);
    return jitJson;
}


function MAX_FREQ(topics) {
    var max = 0.0;
    for (var i=0; i<topics.length; i++) {
        var count = topics[i].DocCount;
        count = parseInt(count);
        if (count >= max) {
            max = count;
        }
    }
    return max;
}

function MIN_FREQ(topics) {
    var min = 0.0;
    for (var i=0; i<topics.length; i++) {
        var count = topics[i].DocCount;
        count = parseInt(count);
        if (count <= min) {
            min = count;
        }
    }
    return min;
}
</script>
</head>
<body>

<div class="wtcenter">
	<div id="cloud" style="background-color: #fff;width:500px;height: 300px;text-align: left;"></div>
</div>
</body>
</html>