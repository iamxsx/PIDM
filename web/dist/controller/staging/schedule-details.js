/**
 * Created by clouder on 16-9-15.
 */
$(function () {
    var url = document.location.toString();
    var taskId = url.substring(url.lastIndexOf('=')+1,url.length);


    $('#agree-button').click(function () {
        var message = $('input[name ="message"]:checked').val();
        var comment = $('#comment').val();
        $.ajax({
            url:'complete?taskId='+taskId,
            type:'post',
            data:{
                comment:comment,
                valiable:message
            },
            success:function () {
                window.location.href='to-do-list';
            }
        });
    });

    $('#reject-button').click(
        function () {
            $.ajax({
                url:'rollBack?taskId='+taskId,
                type:'get',
                success:function () {
                    window.location.href='to-do-list';
                }
            })
        }
    );

    $.ajax({
        url:'comment?taskId='+taskId,
        type:'get',
        success:function (data) {
            var module = $('#comment-table');
            module.html('');
            module.append(
                '<thead><tr><th>办理人</th> <th>意见</th> <th>开始时间</th> <th>结束时间</th> <th>流程环节</th></tr> </thead>'
            );
            $.each(data,function (i,item) {
                var username = item.username;
                var message = item.message;
                var startTime = format(item.startTime,'yyyy-MM-dd HH:mm:ss');
                var endTime = format(item.endTime,'yyyy-MM-dd HH:mm:ss');
                var taskName = item.taskName;
                module.append(
                '<tr> <td>'+ username +
                '</td> <td>'+ message +
                '</td> <td>'+ startTime +
                '</td> <td>'+ endTime +
                '</td> <td>'+ taskName +
                '</td> </tr>'
                );
            })
        }
    });
})


