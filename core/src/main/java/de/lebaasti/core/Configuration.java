package de.lebaasti.core;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
public class Configuration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SliderSetting(min = 2, max = 50)
  private final ConfigProperty<Integer> amount = new ConfigProperty<>(2);

  @TextFieldSetting
  private final ConfigProperty<String> text = new ConfigProperty<>(" &r[x%amount%]");

  @SwitchSetting
  private final ConfigProperty<Boolean> serverSwitchReset = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> subServerSwitchReset = new ConfigProperty<>(true);

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<Integer> amount() { return this.amount; }

  public ConfigProperty<String> text() { return this.text; }

  public ConfigProperty<Boolean> serverSwitchReset() { return this.serverSwitchReset; }

  public ConfigProperty<Boolean> subServerSwitchReset() { return this.subServerSwitchReset; }

}
