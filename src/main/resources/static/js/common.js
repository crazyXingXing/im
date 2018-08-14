$('#send').click(function () {
    var toUser = $('#toUser').val();
    var message = $('#message').val();
    sendSpittle(toUser, message);
});

//链接endpoint名称为 "/endpointChat" 的endpoint。
var sock = new SockJS("/endpointChat");
var stomp = Stomp.over(sock);
stomp.connect('guest', 'guest', function (frame) {

    /**  订阅了/user/queue/notifications 发送的消息,这里雨在控制器的 convertAndSendToUser 定义的地址保持一致, 
     *  这里多用了一个/user,并且这个user 是必须的,使用user 才会发送消息到指定的用户。 
     *  */
    stomp.subscribe("/user/queue/notifications", handleNotification);
    /**
     * 初始化订阅
     */
    stomp.send("/online", {}, '');
});

/**
 * 0: 通用
 * 1: 提示
 * 10：历史信息（首次加载）
 * 11: 聊天记录变动（异步推送）
 * 20: 好友列表（首次加载）
 * 21: 好友列表变动（异步推送）
 */
function handleNotification(message) {
    var obj = JSON.parse(message.body);
    var type = obj.type;
    var body = obj.body;
    $('#output').append("<b>" + body + "</b><br/>")
}

function sendSpittle(toUser, message) {
    stomp.send("/send", {}, JSON.stringify({'toUser': toUser, 'message': message}));//3
}

$('#stop').click(function () {
    sock.close()
});