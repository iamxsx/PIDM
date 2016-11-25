/**
 * Created by clouder on 16-10-27.
 */
$(function () {

    $.ajax({
        url:'showStatisResponse?id='+id+'',
        success:function (data) {
            var topic = data.survey.topic;
            $("#surveyTitle").text("问卷标题："+data.survey.questionnaireSurvey.title);
            var num = 1;
            /**遍历题目*/
            $.each(topic,function (i,item) {
                if(item.nature == 0 || item.nature == 2){

                    $("#questionList").append(
                        '<div  style="background-color: #eee;min-height: 18px;padding: 5px;font-size:18px"><span>'+num+++'、'+item.topicContent+'</span></div>'+
                        '<div class="row">'+
                        '<div class="col-sm-6">'+
                        '<div id="pieChart'+item.tid+'" style="width: 100%;height: 350px">0.0</div>'+
                        '</div>'+
                        '<div class="col-sm-6">'+
                        '<div id="categoryChart'+item.tid+'" style="width: 100%;height: 350px">0.0</div>'+
                        '</div>'+
                        '</div>'
                    );

                    // 基于准备好的dom，初始化echarts实例
                    var myChart1 = echarts.init(document.getElementById('pieChart'+item.tid+''));
                    var myChart2 = echarts.init(document.getElementById('categoryChart'+item.tid+''));
                    var optionContents = new Array();
                    var optionCounts = new Array();
                    /**遍历这道选择题 的 选项*/
                    $.each(item.option,function (i,option) {
                        optionContents.push(option.optionContent);
                        optionCounts.push(option.count)
                    })
                    // ** 圆饼图  **
                    option1 = {
                        // color:['#87A8DF','#6EC3DF','#44DFD0','#D2A2FF'],
                        tooltip: {
                            trigger: 'item',
                            formatter: "选项：{b} <br/>选择人数： {c} <br/>所占比例： {d}%"
                        },
                        legend: {
                            orient : 'vertical',
                            x : 'left',
                            data:optionContents
                        },
                        series : [
                            {
                                type: 'pie',
                                radius : '50%',
                                center: ['50%', '60%'],
                                data:[
                                    {value:optionCounts[0], name:optionContents[0]},
                                    {value:optionCounts[1], name:optionContents[1]},
                                    {value:optionCounts[2], name:optionContents[2]},
                                    {value:optionCounts[3], name:optionContents[3]}
                                ],
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]};
                    // **  柱状图  **
                    option2 = {
                        color: ['#3398DB'],
                        tooltip : {
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '10%',
                            containLabel: true
                        },
                        xAxis : [
                            {
                                axisLabel: {
                                    rotate: 0,     // 转换x轴的角度
                                    formatter:function(val){
                                        // return val.split("").join("\n");
                                        return getEchartBarXAxisTitle(val,optionContents,12,600,80,80,"\n")
                                    }

                                },
                                type : 'category',
                                data : optionContents,
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                            }
                        ],
                        series : [
                            {
                                name:'选择人数',
                                type:'bar',
                                barWidth: '60%',
                                data:optionCounts
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart1.setOption(option1);
                    myChart2.setOption(option2);

                }
            })
        }
    })

})


function getEchartBarXAxisTitle(title, datas, fontSize, barContainerWidth, xWidth, x2Width, insertContent){

    if(!title || title.length == 0) {
        alert("截取拼接的参数值不能为空！");return false;
    }
    if(!datas || datas.length == 0) {
        alert("用于计算柱状图柱子个数的参数datas不合法！"); return false;
    }
    if(isNaN(barContainerWidth)) {
        alert("柱状图初始化所在的容器的宽度不是一个数字");return false;
    }
    if(!fontSize){
        fontSize = 12;
    }
    if(isNaN(xWidth)) {
        xWidth = 80;//默认与echarts的默认值一致
    }
    if(isNaN(x2Width)) {
        xWidth = 80;//默认与echarts的默认值一致
    }
    if(!insertContent) {
        insertContent = "\n";
    }

    var xAxisWidth =  parseInt(barContainerWidth) - (parseInt(xWidth) + parseInt(x2Width));//柱状图x轴宽度=统计页面宽度-柱状图x轴的空白间隙(x + x2)
    var barCount = datas.length;                                //x轴单元格的个数（即为获取x轴的数据的条数）
    var preBarWidth = Math.floor(xAxisWidth / barCount);        //统计x轴每个单元格的间隔
    var preBarFontCount = Math.floor(preBarWidth / fontSize) ;  //柱状图每个柱所在x轴间隔能容纳的字数 = 每个柱子 x 轴间隔宽度 / 每个字的宽度（12px）
    if(preBarFontCount > 3) {    //为了x轴标题显示美观，每个标题显示留两个字的间隙，如：原本一个格能一样显示5个字，处理后一行就只显示3个字
        preBarFontCount -= 2;
    } else if(preBarFontCount <= 3 && preBarFontCount >= 2) {//若每个间隔距离刚好能放两个或者字符时，则让其只放一个字符
        preBarFontCount -= 1;
    }

    var newTitle = "";      //拼接每次截取的内容，直到最后为完整的值
    var titleSuf = "";      //用于存放每次截取后剩下的部分
    var rowCount = Math.ceil(title.length / preBarFontCount);   //标题显示需要换行的次数
    if(rowCount > 1) {       //标题字数大于柱状图每个柱子x轴间隔所能容纳的字数，则将标题换行
        for(var j = 1; j <= rowCount; j++) {
            if(j == 1) {

                newTitle += title.substring(0, preBarFontCount) + insertContent;
                titleSuf = title.substring(preBarFontCount);    //存放将截取后剩下的部分，便于下次循环从这剩下的部分中又从头截取固定长度
            } else {

                var startIndex = 0;
                var endIndex = preBarFontCount;
                if(titleSuf.length > preBarFontCount) {  //检查截取后剩下的部分的长度是否大于柱状图单个柱子间隔所容纳的字数

                    newTitle += titleSuf.substring(startIndex, endIndex) + insertContent;
                    titleSuf = titleSuf.substring(endIndex);    //更新截取后剩下的部分，便于下次继续从这剩下的部分中截取固定长度
                } else if(titleSuf.length > 0){
                    newTitle += titleSuf.substring(startIndex);
                }
            }
        }
    } else {
        newTitle = title;
    }
    return newTitle;
}