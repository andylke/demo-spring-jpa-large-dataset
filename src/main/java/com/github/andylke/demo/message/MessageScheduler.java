package com.github.andylke.demo.message;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageScheduler.class);

  @Autowired private MessageRepository messageRepository;

  @Scheduled(initialDelay = 1000, fixedDelay = 15000)
  public void runDemo() {
    findAll();
    findAllBy5000();
    findAllBy10000();
  }

  private void findAll() {
    final long beginTime = System.nanoTime();

    final List<Message> messages = messageRepository.findAll();

    LOGGER.info(
        "findAll elapsed [{}] to fetch [{}] messages",
        Duration.ofNanos(System.nanoTime() - beginTime),
        messages.size());
  }

  private List<Message> findAllBy5000() {
    final long beginTime = System.nanoTime();

    final List<Message> messages = new ArrayList<Message>();
    Page<Message> page = messageRepository.findAll(PageRequest.of(0, 5000));
    if (page.hasContent()) {
      messages.addAll(page.getContent());
      while (page.hasNext()) {
        page = messageRepository.findAll(page.nextPageable());
        messages.addAll(page.getContent());
      }
    }
    LOGGER.info(
        "findAllBy5000 elapsed [{}] to fetch [{}] messages",
        Duration.ofNanos(System.nanoTime() - beginTime),
        messages.size());
    return messages;
  }

  private List<Message> findAllBy10000() {
    final long beginTime = System.nanoTime();

    final List<Message> messages = new ArrayList<Message>();
    Page<Message> page = messageRepository.findAll(PageRequest.of(0, 10000));
    if (page.hasContent()) {
      messages.addAll(page.getContent());
      while (page.hasNext()) {
        page = messageRepository.findAll(page.nextPageable());
        messages.addAll(page.getContent());
      }
    }
    LOGGER.info(
        "findAllBy10000 elapsed [{}] to fetch [{}] messages",
        Duration.ofNanos(System.nanoTime() - beginTime),
        messages.size());
    return messages;
  }
}
