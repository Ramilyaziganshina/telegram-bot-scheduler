package pro.sky.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.entity.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class Scheduler {
    private final TelegramBot telegramBot;
    private final NotificationRepository notificationRepository;

    public Scheduler(TelegramBot telegramBot, NotificationRepository notificationRepository) {
        this.telegramBot = telegramBot;
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    private void sendNotifications() {
        List<Notification> notifications = notificationRepository.findAllByDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        notifications.forEach(n -> {
                SendMessage msg = new SendMessage(n.getChatID(), "Напоминание: " + n.getTask());
                telegramBot.execute(msg);
        });
    }
}
