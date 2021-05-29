package me.happybavarian07.ConfirmMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.happybavarian07.apis.StartUpLogger;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ConfirmMessage extends JavaPlugin implements Listener {
	
	StartUpLogger logger;
	static ConfirmMessage plugin;
	
	
	@Override
	public void onEnable() {
		logger = new StartUpLogger();
		setPlugin(this);
		Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		getCommand("confirmmessage").setExecutor(new MainCommand());
		getCommand("confirmmessage").setTabCompleter(new CommandCompleter());
		logger.coloredSpacer(ChatColor.GREEN).message("§2Enabled ConfirmMessage Plugin!§r").coloredSpacer(ChatColor.GREEN);
	}
	
	@Override
	public void onDisable() {
		logger = new StartUpLogger();
		logger.coloredSpacer(ChatColor.RED).message("§cDisabled ConfirmMessage Plugin!§r").coloredSpacer(ChatColor.RED);
	}
	
	public ConfirmMessage getPlugin() {
		return plugin;
	}

	@SuppressWarnings("static-access")
	public void setPlugin(ConfirmMessage plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		String command = e.getMessage().toLowerCase().replace("/", "");
		if(getConfig().getStringList("Commandlist").contains(command) && !e.getPlayer().getScoreboardTags().contains("CommandExecuter" + command)) {
			e.setCancelled(true);
			TextComponent insgesamt = new TextComponent();
			TextComponent confirm = new TextComponent();
			TextComponent cancel = new TextComponent();
			confirm.setText("      " + getConfig().getString("ConfirmMessage").replace('&', '§'));
			confirm.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/" + command));
			confirm.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(getConfig().getString("ConfirmMessageHoverText").replace('&', '§'))));
			cancel.setText("      " + getConfig().getString("CancelMessage").replace('&', '§'));
			cancel.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tag " + e.getPlayer().getName() + " remove CommandExecuter" + command));
			cancel.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(getConfig().getString("CancelMessageHoverText").replace('&', '§'))));
			insgesamt.addExtra(confirm);
			insgesamt.addExtra(cancel);
			for(int i = 0; i < 10; i++) {
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
				e.getPlayer().sendMessage("");
			}
			e.getPlayer().addScoreboardTag("CommandExecuter" + command);
			e.getPlayer().sendMessage(getConfig().getString("Commandsettings." + command + ".message").replace('&', '§'));
			e.getPlayer().spigot().sendMessage(insgesamt);
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("§cThese messages become unusable after 5 seconds!");
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				
				@Override
				public void run() {
					confirm.setClickEvent(null);
					cancel.setClickEvent(null);
					confirm.setHoverEvent(null);
					cancel.setHoverEvent(null);
					e.getPlayer().removeScoreboardTag("CommandExecuter" + command);
				}
			}, 100L);
		} else {
			return;
		}
	}
}
