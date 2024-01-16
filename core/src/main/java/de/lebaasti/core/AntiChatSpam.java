package de.lebaasti.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.component.Component;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.event.client.network.server.SubServerSwitchEvent;
import net.labymod.api.models.addon.annotation.AddonMain;
import java.util.ArrayList;
import java.util.HashMap;

@AddonMain
public class AntiChatSpam extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.logger().info("Enabled AntiChatSpam Addon");
  }

  @Override
  protected Class<Configuration> configurationClass() {
    return Configuration.class;
  }

  private final HashMap<String, ArrayList<ChatMessage>> duplicateMessages = new HashMap<>();
  private final HashMap<String, Integer> duplicateMessageCount = new HashMap<>();

  @Subscribe
  public void onSubServerSwitch(SubServerSwitchEvent event) {
    duplicateMessages.clear();
    duplicateMessageCount.clear();
  }

  @Subscribe
  public void onServerJoin(ServerJoinEvent event) {
    duplicateMessages.clear();
    duplicateMessageCount.clear();
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    String originalPlainText = event.chatMessage().getOriginalPlainText();
    ArrayList<ChatMessage> duplicates = duplicateMessages.computeIfAbsent(originalPlainText, key -> new ArrayList<>());
    int count = duplicateMessageCount.compute(originalPlainText, (key, value) -> value == null ? 1 : value + 1);

    if (count >= configuration().amount().get()) {
      duplicates.forEach(ChatMessage::delete);
      duplicates.clear();
      event.chatMessage().component().append(Component.text(" [x" + count + "]"));
    }

    duplicates.add(event.chatMessage());
  }
}
