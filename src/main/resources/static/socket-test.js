//let stompClient = null;
//let currentRoomId = null;
//
//$(document).ready(function () {
//    // 채팅방 생성
//    $('#createRoom').click(function () {
//        const roomName = $('#roomName').val();
//        if (!roomName) {
//            alert('Room name is required');
//            return;
//        }
//
//        $.ajax({
//            url: '/chat/create',
//            method: 'POST',
//            contentType: 'application/json',
//            data: JSON.stringify({ roomName, hostName: 'HostUser' }),
//            success: function (response) {
//                alert(`Room created! Room ID: ${response.roomId}`);
//                $('#roomId').val(response.roomId);
//            },
//            error: function () {
//                alert('Failed to create room');
//            }
//        });
//    });
//
//    // 채팅방 연결
//    $('#connectRoom').click(function () {
//        const roomId = $('#roomId').val();
//        if (!roomId) {
//            alert('Room ID is required');
//            return;
//        }
//
//        currentRoomId = roomId;
//
//        const socket = new SockJS('/ws');
//        stompClient = Stomp.over(socket);
//        stompClient.connect({}, function () {
//            stompClient.subscribe(`/topic/chat/${roomId}`, function (message) {
//                const chatMessage = JSON.parse(message.body);
//                $('#chatWindow').append(`<div class="chat-message"><strong>${chatMessage.sender}:</strong> ${chatMessage.message}</div>`);
//            });
//
//            $('#chatSection').show();
//            alert(`Connected to room: ${roomId}`);
//        }, function (error) {
//            alert('Failed to connect to WebSocket');
//            console.error('WebSocket error:', error);
//        });
//    });
//
//    // 메시지 전송
//    $('#sendMessage').click(function () {
//        const userName = $('#userName').val();
//        const message = $('#message').val();
//
//        if (!userName || !message) {
//            alert('Both name and message are required');
//            return;
//        }
//
//        const chatMessage = {
//            roomId: currentRoomId,
//            sender: userName,
//            message: message
//        };
//
//        stompClient.send('/app/send-message', {}, JSON.stringify(chatMessage));
//        $('#message').val('');
//    });
//});
