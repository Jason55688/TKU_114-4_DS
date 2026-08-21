public class MessageSenderSystem {

    public static void main(String[] args) {

        MessageSender[] senders = {

                new EmailSender(),
                new SmsSender(),
                new ConsoleSender()
        };

        notify(
                senders,
                "Amy",
                "系統通知：會議將於下午 2 點開始");

        System.out.println();

        // 測試空白 receiver
        notify(
                senders,
                "",
                "測試訊息");

        System.out.println();

        // 測試空白 message
        notify(
                senders,
                "John",
                "");
    }

    // 主程式只依賴 MessageSender
    public static void notify(
            MessageSender[] senders,
            String receiver,
            String message) {

        for (MessageSender sender : senders) {
            sender.send(receiver, message);
        }
    }
}

// Interface
interface MessageSender {

    void send(String receiver, String message);
}

// Email Sender
class EmailSender implements MessageSender {

    @Override
    public void send(
            String receiver,
            String message) {

        if (isInvalid(receiver, message)) {
            System.out.println(
                    "[Email] 收件人或訊息不可空白");
            return;
        }

        System.out.println(
                "[Email] 發送給 "
                        + receiver
                        + "："
                        + message);
    }

    private boolean isInvalid(
            String receiver,
            String message) {

        return receiver == null
                || receiver.trim().isEmpty()
                || message == null
                || message.trim().isEmpty();
    }
}

// SMS Sender
class SmsSender implements MessageSender {

    @Override
    public void send(
            String receiver,
            String message) {

        if (isInvalid(receiver, message)) {
            System.out.println(
                    "[SMS] 收件人或訊息不可空白");
            return;
        }

        System.out.println(
                "[SMS] 發送給 "
                        + receiver
                        + "："
                        + message);
    }

    private boolean isInvalid(
            String receiver,
            String message) {

        return receiver == null
                || receiver.trim().isEmpty()
                || message == null
                || message.trim().isEmpty();
    }
}

// Console Sender
class ConsoleSender implements MessageSender {

    @Override
    public void send(
            String receiver,
            String message) {

        if (isInvalid(receiver, message)) {
            System.out.println(
                    "[Console] 收件人或訊息不可空白");
            return;
        }

        System.out.println(
                "[Console] 發送給 "
                        + receiver
                        + "："
                        + message);
    }

    private boolean isInvalid(
            String receiver,
            String message) {

        return receiver == null
                || receiver.trim().isEmpty()
                || message == null
                || message.trim().isEmpty();
    }
}