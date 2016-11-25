/**
 * Created by clouder on 16-9-15.
 */
$(function () {
    var url = document.location.toString();
    var processInstanceId = url.substring(url.lastIndexOf('=')+1,url.length);

    $.ajax({
        url:'historicComment?processInstanceId='+processInstanceId,
        type:'get',
        success:function (data) {
            var module = $('#pending-comment-table');
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